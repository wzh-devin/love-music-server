package com.devin.love.music.service.v1.builder;

import com.devin.love.music.common.utils.SnowFlake;
import com.devin.love.music.domain.entity.FileInfo;
import com.devin.love.music.domain.vo.req.FileInfoReq;
import lombok.Data;

/**
 * 2024/11/29 18:28
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class FileBuilder {

    /**
     * 构建FileInfo实体
     * @return
     */
    public static FileInfo buildFileInfo(FileInfoReq fileInfoReq, String md5) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(SnowFlake.nextId());
        fileInfo.setOriginName(fileInfoReq.getOriginName());
        fileInfo.setPath(fileInfoReq.getPath());
        fileInfo.setMd5(md5);
        fileInfo.setType(fileInfoReq.getType());
        fileInfo.setBelongModuleId(fileInfoReq.getBelongModuleId());
        fileInfo.setBelongModuleName(fileInfoReq.getBelongModuleName());
        return fileInfo;
    }
}
