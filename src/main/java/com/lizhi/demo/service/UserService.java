package com.lizhi.demo.service;

import com.lizhi.demo.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lenovo
 */
public interface UserService extends UserDetailsService {

    User queryUserById(Long id);

    User saveUser(User user);

    void deleteUserById(Long id);
}
