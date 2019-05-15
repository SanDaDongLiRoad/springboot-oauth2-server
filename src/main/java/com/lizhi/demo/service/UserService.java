package com.lizhi.demo.service;

import com.lizhi.demo.domain.User;
import com.lizhi.demo.utils.Result;
import com.lizhi.demo.vo.AuthUserInfoVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

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

    /**
     * 通过用户名查询可信用户信息
     * @param username
     * @return
     */
    AuthUserInfoVO queryAuthInfoByUsername(String username);

    /**
     * 通过用户名查询可信用户信息
     * @return
     */
    List<AuthUserInfoVO> queryAuthInfoList();

    /**
     * 保存可信用户信息
     * @param authUserInfoVO
     * @return
     */
    Result<String> saveAuthInfo(AuthUserInfoVO authUserInfoVO);
}
