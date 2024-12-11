package com.devin.love.music.service.v1;

import com.devin.love.music.domain.vo.req.FileInfoReq;

import java.io.IOException;
import java.io.InputStream;

/**
 * 2024/11/27 22:37
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface MinioService {

    /**
     * 文件上传服务
     * @param fileInfo
     * @return md5 返回计算的MD5值
     */
    void uploadFile(FileInfoReq fileInfo);
}
