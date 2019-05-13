package com.lizhi.demo.service;

import com.lizhi.demo.domain.Role;

/**
 * @author lenovo
 */
public interface RoleService {

    /**
     * 通过角色ID查询角色
     * @param roleId
     * @return
     */
    Role queryById(Long roleId);
}
