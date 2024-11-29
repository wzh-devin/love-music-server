package com.devin.love.music.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

/**
 * 2024/11/26 21:27
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
public class MinioUtilTest {

    @Autowired
    private MinioUtil minioUtil;

    @Test
    public void testCreateBucket() throws Exception {
        minioUtil.createBucket("test");
    }

    @Test
    public void testUploadFile() throws Exception {
        File file = new File(System.getProperty("user.dir") + "/upload/singer/avatars/default_header_8548609443252674560.jpg");
        InputStream is = new FileInputStream(file);
        minioUtil.uploadFile(is, "love-music", "/singer/default_header_8548609443252674560.jpg");
    }

    @Test
    public void testDownloadFil () throws Exception {
        InputStream is = minioUtil.downloadFile("love-music", "singer/default_header_3fd3e6341041b8d9dfac542218db9160.jpg");
        File file = new File(System.getProperty("user.dir") + "/upload/download/default_header_3fd3e6341041b8d9dfac542218db9160.jpg");
        OutputStream os = new FileOutputStream(file);
        // 写入文件
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = is.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
    }

}
