package com.devin.love.music.dao.v1;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.love.music.domain.entity.FileInfo;
import com.devin.love.music.mapper.v1.FileMapper;
import org.springframework.stereotype.Service;

/**
 * 2024/11/29 18:24
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class FileDao extends ServiceImpl<FileMapper, FileInfo> {

    /**
     * 获取文件通过MD5和文件路径
     * @param md5
     * @param path
     */
    public FileInfo getFileByMd5AndPath(String md5, String path) {
        return lambdaQuery()
                .eq(FileInfo::getPath, path)
                .eq(FileInfo::getMd5, md5)
                .one();
    }

    public FileInfo getFileByModule(Long id, String name) {
        return lambdaQuery()
                .eq(FileInfo::getBelongModuleId, id)
                .eq(FileInfo::getBelongModuleName, name)
                .one();
    }

    /**
     * 获取文件
     * @param belongModuleId
     * @param belongModuleName
     * @param md5
     * @return
     */
    public FileInfo getFileByModuleAndMD5(Long belongModuleId, String belongModuleName, String md5) {
        return lambdaQuery()
                .eq(FileInfo::getBelongModuleId, belongModuleId)
                .eq(FileInfo::getBelongModuleName, belongModuleName)
                .eq(FileInfo::getMd5, md5)
                .one();
    }
}
