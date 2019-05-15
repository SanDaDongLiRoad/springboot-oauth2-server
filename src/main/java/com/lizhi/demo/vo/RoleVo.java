package com.lizhi.demo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lenovo
 */
@Data
public class RoleVo implements Serializable{

    /**
     * 角色ID
     */
    private Long roleid;

    /**
     * 角色名称
     */
    private String rolename;
}
