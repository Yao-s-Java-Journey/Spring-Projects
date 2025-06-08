package com.manager.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类：阿里云 OSS 服务配置
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss") // 获取 application.yml 中的 aliyun.oss 对象
@Data
public class AliOSSProperties {
    private String endpoint;
    private String bucket;
    private String region;
}
