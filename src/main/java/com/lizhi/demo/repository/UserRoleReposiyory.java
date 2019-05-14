package com.lizhi.demo.repository;

import com.lizhi.demo.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lenovo
 */
public interface UserRoleReposiyory  extends JpaRepository<UserRole,Long> {

    /**
     * 通过用户名查询用户角色列表
     * @param username
     * @return
     */
    List<UserRole> findByUsername(String username);
}
