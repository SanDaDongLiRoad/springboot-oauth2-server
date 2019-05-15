package com.lizhi.demo.service;

import com.lizhi.demo.domain.UserRole;

import java.util.List;

/**
 * @author lenovo
 */
public interface UserRoleService {

    /**
     * 通过用户ID查询用户角色列表
     * @param username
     * @return
     */
    List<UserRole> queryListByUsername(String username);

    /**
     * 保存用户角色信息
     * @param userRole
     * @return
     */
    UserRole saveUserRole(UserRole userRole);
}
