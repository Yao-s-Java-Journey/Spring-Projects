package com.manager.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * JWT 令牌工具类
 */
public class JWT {
    // 定义字符串密钥，确保密钥长度足够（至少 256 位），否则会初始化失败
    private static final String SING_KEY = "ThisIsASecureSecretKeyForJWTSigning123!";

    // 方式一：专门用于 HMAC-SHA 系列算法的 Keys.hmacShaKeyFor
    // 将字符串密钥转换为合适的 Key 对象，生成 HMAC-SHA256 密钥
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SING_KEY.getBytes());
    // 生成指定 32 字节大小的随机密钥（推荐，更安全）
    // private static final Key randomKey  = Keys.hmacShaKeyFor(256 / 8);

    // 方式二：使用通用的 Keys.secretKeyFor
    // private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 令牌有效期，3天
    private static final Long expire = 3 * 24 * 3600 * 1000L;

    /**
     * 生成令牌
     *
     * @param claims 负载信息
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                // .subject("userId") // 设置主题，如用户ID
                .claims(claims)
                .signWith(SECRET_KEY)
                .expiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * 解析令牌
     *
     * @param token 令牌
     * @return 负载信息
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY) // 设置签名密钥
                .build()
                .parseSignedClaims(token) // 解析并验证
                .getPayload();
    }
}
