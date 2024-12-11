package com.devin.love.music.controller.v1;

import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.common.annotation.Log;
import com.devin.love.music.common.constant.Version;
import com.devin.love.music.common.exception.FileBusinessException;
import com.devin.love.music.common.utils.MinioUtil;
import com.devin.love.music.service.v1.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 2024/11/26 21:24
 * <p>
 *     minio控制器
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@ApiV1
@RestController
@RequestMapping
@Api(tags = "minio文件相关接口")
@RequiredArgsConstructor
public class MinioController {

    private final MinioService minioService;

    @Value("${minio.bucket-name}")
    private String bucketName;

    private final MinioUtil minioUtil;
    private final HttpServletResponse response;

    @GetMapping("/download/singer/{fileName}")
    @ApiOperation("图片文件下载")
    @Log(desc = "图片文件下载", module = "minio文件管理模块", version = Version.V1)
    public ResponseEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) {
        try(InputStream is = minioUtil.downloadFile(bucketName, "singer/" + fileName)) {
            byte[] bytes = IOUtils.toByteArray(is);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new FileBusinessException("文件下载失败：" + e.getMessage());
        }
    }
}
