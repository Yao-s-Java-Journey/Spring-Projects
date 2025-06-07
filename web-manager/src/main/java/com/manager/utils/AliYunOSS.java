package com.manager.utils;

import java.io.*;
import java.util.Random;
import java.util.UUID;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AliYunOSS {
    /**
     * 上传文件
     *
     * @param content    内容字节数组
     * @param extName    文件扩展名
     * @param endpoint   endpoint 域名
     * @param bucketName 存储空间的名字
     * @param region     Bucket 所在地域
     * @return 上传后的文件路径
     */
    public static String upload(
            byte[] content,
            String extName,
            String endpoint,
            String bucketName,
            String region
    ) throws com.aliyuncs.exceptions.ClientException {
        // Endpoint以华东1（杭州）为例，填写为https://oss-cn-hangzhou.aliyuncs.com，其它Region请按实际情况填写。
        // String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        // String bucketName = generateUniqueBucketName("yao-java-dev");

        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
        // 关于OSS支持的Region与Endpoint的对应关系，请参见https://www.alibabacloud.com/help/zh/oss/user-guide/regions-and-endpoints。
        // String region = "cn-shanghai";

        // 从环境变量中获取访问凭证。运行本代码示例之前，请先配置环境变量
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();

        // 显式声明使用 V4 签名算法
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        // 给文件名添加 uuid
        String objectName = UUID.randomUUID() + extName;

        try {
            // 1. 创建存储空间（Bucket）
            ossClient.createBucket(bucketName);
            log.debug("1. Bucket " + bucketName + " 创建成功。");

            // 2. 上传文件
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
            log.info("文件 " + extName + " 上传成功。");
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message:" + oe.getErrorMessage());
            log.error("Error Code:" + oe.getErrorCode());
            log.error("Request ID:" + oe.getRequestId());
            log.error("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return endpoint.split("//")[0] + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }
}
