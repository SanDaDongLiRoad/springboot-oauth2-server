package com.lizhi.demo.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lenovo
 */
@Data
public class AuthUserInfoVO implements Serializable{

    private static final long serialVersionUID = -2176218316195188490L;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 客户受限范围
     */
    private String scope;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * (可信客户端需要)客户端密钥
     */
    private String clientSecret;

    /**
     * 角色列表
     */
    private List<RoleVo> roleVoList;
}
