package com.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    private Integer id;
    private String account;
    private String username;
    private String accessToken;
}
