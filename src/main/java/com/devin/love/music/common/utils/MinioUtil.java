package com.devin.love.music.common.utils;

import io.minio.*;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * 2024/11/26 20:36
 * <p>
 *     Minio工具类
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Component
public class MinioUtil {

    @Autowired
    private MinioClient minioClient;

    /**
     * 创建桶
     * @param bucketName
     * @throws Exception
     */
    public void createBucket(String bucketName) throws Exception {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 判断桶是否存在
     * @param bucketName
     * @return
     * @throws Exception
     */
    public boolean bucketExists(String bucketName) throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 判断文件是否存在
     * @param bucketName
     * @param objectName
     * @return false 不存在，true 存在
     */
    public boolean fileExists(String bucketName, String objectName) {
        boolean exist = true;
        try {
            minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }

    /**
     * 获取所有的桶对象列表
     * @return
     * @throws Exception
     */
    public List<Bucket> bucketList() throws Exception {
        return minioClient.listBuckets();
    }

    /**
     * 获取所有桶名列表
     * @return
     * @throws Exception
     */
    public List<String> bucketListName() throws Exception {
        return bucketList().stream().map(Bucket::name).toList();
    }

    /**
     * 上传文件
     * @param stream
     * @param bucketName
     * @param objectName
     * @throws Exception
     */
    public void uploadFile(InputStream stream, String bucketName, String objectName) throws Exception {
        if (!Objects.isNull(stream)) {
            minioClient.putObject(PutObjectArgs.builder()
                    .stream(stream, stream.available(), -1)
                    .object(objectName)
                    .bucket(bucketName)
                    .build());
        }
    }

    /**
     * 下载文件
     * @param bucketName
     * @param objectName
     * @return
     * @throws Exception
     */
    public InputStream downloadFile(String bucketName, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 删除文件
     * @param bucketName
     * @param objectName
     * @throws Exception
     */
    public void remoteFile(String bucketName, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }
}
