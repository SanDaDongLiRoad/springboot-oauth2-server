package com.lizhi.demo.service;

import com.lizhi.demo.domain.UserRole;

import java.util.List;

/**
 * @author lenovo
 */
public interface UserRoleService {

    /**
     * 通过用户ID查询用户角色列表
     * @param userid
     * @return
     */
    List<UserRole> queryListByUserid(Long userid);
}
