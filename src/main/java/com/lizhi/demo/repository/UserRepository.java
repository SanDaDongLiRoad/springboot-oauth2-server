package com.lizhi.demo.repository;

import com.lizhi.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lenovo
 */
public interface UserRepository extends JpaRepository<User,Long>{

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
