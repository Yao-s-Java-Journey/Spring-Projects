package com.manager.controller;

import com.aliyuncs.exceptions.ClientException;
import com.manager.entity.AliOSSProperties;
import com.manager.entity.Result;
import com.manager.utils.AliYunOSS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    // OSS 服务上 bucket 桶的名字
    // 方式一：private final String bucketName = "yao-java-dev";
    // 方式二：@Value 读取 properties 配置文件
    // @Value("${aliyun.oss.bucket}")
    // private String bucketName;

    // OSS 服务上的 bucket 桶对应的域名
    // 方式一：private final String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
    // 方式二：@Value 读取 properties 配置文件
    // @Value("${aliyun.oss.endpoint}")
    // private String endpoint;

    // bucket 所在地域
    // 方式一：private final String region = "cn-shanghai";
    // 方式二：@Value 读取 properties 配置文件
    // @Value("${aliyun.oss.region}")
    // private String region;

    // 方式三：获取整个 aliyun oss 配置对象类
    @Autowired
    private AliOSSProperties aliOSS;

    /**
     * 文件上传——本地存储
     * @param file 文件
     * @return 文件名
     */
    @PostMapping("/localPic")
    public Result uploadLocalPic(MultipartFile file) throws IOException {
        // 1. 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 2. 通过 uuid 生成随机字符串（避免重名覆盖）
        // 2.1 分割文件名和扩展名
        int lastDotIndex = originalFilename.lastIndexOf('.');
        String prefix = originalFilename.substring(0, lastDotIndex); // 前缀
        String extension = originalFilename.substring(lastDotIndex); // 扩展名
        // 2.2 在中间拼接上 uuid
        StringBuffer sb = new StringBuffer(prefix);
        sb.append("_").append(UUID.randomUUID()).append(extension);
        String fileName = sb.toString();


        // 3. 将前端上传的文件存到本地
        file.transferTo(new File("D:/" + fileName));

        return Result.success(fileName);
    }

    /**
     * 文件上传——阿里云 OSS 服务
     * @param file 文件
     * @return 文件链接
     */
    @PostMapping("/ossPic")
    public Result uploadOssPic(MultipartFile file) throws IOException, ClientException {
        // 获取原始文件名、截取扩展名
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf('.'));

        // 调用阿里云 OSS 服务
        String url = AliYunOSS.upload(
                file.getBytes(),
                extName,
                aliOSS.getEndpoint(),
                aliOSS.getBucket(),
                aliOSS.getRegion()
        );

        return Result.success(url);
    }
}
