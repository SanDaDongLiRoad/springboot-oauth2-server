package com.lizhi.demo.repository;

import com.lizhi.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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

    /**
     * 根据clientId查询用户
     * @param clientId
     * @return
     */
    User findByClientId(String clientId);
}
