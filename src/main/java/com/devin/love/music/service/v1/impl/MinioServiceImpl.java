package com.devin.love.music.service.v1.impl;

import com.devin.love.music.common.exception.FileBusinessException;
import com.devin.love.music.common.utils.MinioUtil;
import com.devin.love.music.dao.v1.FileDao;
import com.devin.love.music.domain.entity.FileInfo;
import com.devin.love.music.domain.vo.req.FileInfoReq;
import com.devin.love.music.service.v1.MinioService;
import com.devin.love.music.service.v1.builder.FileBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

/**
 * 2024/11/27 22:37
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    @Value("${minio.bucket-name}")
    private String bucketName;

    private final MinioUtil minioUtil;
    private final FileDao fileDao;

    @Override
    public void uploadFile(FileInfoReq fileInfo) {
        try (InputStream is = fileInfo.getFile().getInputStream()) {
            byte[] bytes = IOUtils.toByteArray(is);
            // 初始化文件名
            String[] originName = fileInfo.getOriginName().split("\\.");
            String fileName = originName[0] + "_" + fileInfo.getMd5() + "." + originName[originName.length - 1];
            // 重新设置文件路径
            String resetPath = fileInfo.getPath().concat(fileName);
            // 检查文件是否重复
            checkDuplicate(resetPath, fileInfo.getMd5(), fileInfo.getPath(), fileInfo);
            // 删除数据库中的文件
            Optional.ofNullable(fileDao.getFileByModule(fileInfo.getBelongModuleId(), fileInfo.getBelongModuleName()))
                    .ifPresent(file -> {
                        fileDao.removeById(file.getId());
                    });
            fileInfo.setPath(resetPath);
            // 文件入库
            fileDao.save(FileBuilder.buildFileInfo(fileInfo, fileInfo.getMd5()));

            try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
                // 上传文件
                minioUtil.uploadFile(inputStream, bucketName, resetPath);
            }
        } catch (Exception e) {
            throw new FileBusinessException("文件上传失败" + e.getMessage());
        }
    }

    /**
     * 检测文件是否存在
     * @param objectName
     */
    private void checkDuplicate(String objectName, String md5, String path, FileInfoReq fileInfoReq) {
        if (minioUtil.fileExists(bucketName, objectName)) {
            // 判断文件的belongModuleId是否一致
            if (!Objects.isNull(fileDao.getFileByModuleAndMD5(fileInfoReq.getBelongModuleId(), fileInfoReq.getBelongModuleName(), md5))) {
                throw new FileBusinessException("文件已经存在");
            }
        }
        // 检查数据库中是否存在
        Optional.ofNullable(fileDao.getFileByMd5AndPath(md5, path))
                .ifPresent(fileInfo -> {
                    // 文件不存在，但是数据库中存在，数据不一致，删除数据库中的数据
                    fileDao.removeById(fileInfo.getId());
                });
    }
}
