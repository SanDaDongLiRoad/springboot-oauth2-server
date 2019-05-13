package com.lizhi.demo.service;

import com.lizhi.demo.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lenovo
 */
public interface UserService extends UserDetailsService {

    /**
     * 通过ID查询用户
     * @param id
     * @return
     */
    User queryUserById(Long id);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUserById(Long id);

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User queryUserByName(String username);
}
