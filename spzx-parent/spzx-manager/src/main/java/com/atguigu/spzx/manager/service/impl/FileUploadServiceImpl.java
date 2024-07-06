package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.properties.MinioProperties;
import com.atguigu.spzx.manager.service.FileUploadService;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;


@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private MinioProperties minioProperties;
    @Override
    public String upload(MultipartFile file) {
        try {
            // 创建 minioClient对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperties.getEndpointUrl())
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                            .build();

            // 创建bucket
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {
                // Make a new bucket called 'spzx-bucket'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                System.out.println("Bucket 'spzx-bucket' already exists.");
            }

            //获取上传文件名称
            // 1 每个上传文件名称唯一的 uuid生成id
            // 根据当前日期对上传文件进行分组
            String dateDir = DateUtil.format(new Date(), "yyyymmdd").toString();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String filename = dateDir + "/" + uuid + file.getOriginalFilename();

            //上传文件
           minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .object(filename)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build());

            //获取文件路径
            // http://127.0.0.1:9000/spzx-bucket/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20240512211407.png
            String url = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + filename;
            return url;

        }catch(Exception e){
                    e.printStackTrace();
                    throw new GuiguException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
