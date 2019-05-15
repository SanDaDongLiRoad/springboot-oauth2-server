package com.lizhi.demo.service.impl;

import com.lizhi.demo.domain.User;
import com.lizhi.demo.dto.CustomUserDetails;
import com.lizhi.demo.repository.UserRepository;
import com.lizhi.demo.service.UserService;
import com.lizhi.demo.utils.Result;
import com.lizhi.demo.utils.ResultUtil;
import com.lizhi.demo.vo.AuthUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lenovo
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find the user '" + username + "'");
        }

        return new CustomUserDetails(user, true, true, true, true, null);
    }

    @Override
    public User queryUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User queryUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AuthUserInfoVO queryAuthInfoByUsername(String username) {
        AuthUserInfoVO authUserInfoVO = new AuthUserInfoVO();
        User user = queryUserByName(username);
        if(Objects.equals(null,user)){
            return authUserInfoVO;
        }
        authUserInfoVO.setUsername(user.getUsername());
        authUserInfoVO.setPassword(user.getPassword());
        authUserInfoVO.setClientId(user.getClientId());
        authUserInfoVO.setClientSecret(user.getClientSecret());
        authUserInfoVO.setScope(user.getScope());
        return authUserInfoVO;
    }

    @Override
    public List<AuthUserInfoVO> queryAuthInfoList() {
        List<AuthUserInfoVO> authUserInfoVOList = new ArrayList<AuthUserInfoVO>();
        List<User> userList = userRepository.findAll();
        for(User user : userList){
            AuthUserInfoVO authUserInfoVO = new AuthUserInfoVO();
            authUserInfoVO.setUsername(user.getUsername());
            authUserInfoVO.setPassword(user.getPassword());
            authUserInfoVO.setClientId(user.getClientId());
            authUserInfoVO.setClientSecret(user.getClientSecret());
            authUserInfoVO.setScope(user.getScope());
            authUserInfoVOList.add(authUserInfoVO);
        }
        return authUserInfoVOList;
    }


    @Override
    public Result<String> saveAuthInfo(AuthUserInfoVO authUserInfoVO) {

        User checkUser = queryUserByName(authUserInfoVO.getUsername());
        if(Objects.equals(null,checkUser)){
            return ResultUtil.error(2001,"该用户名已存在");
        }
        User user = new User();
        user.setUsername("liuhaihai");
        user.setPassword("1234567");
        user.setClientId("windows10");
        user.setClientSecret("hfrr448989f");
        user.setScope("read,write");
        BaseClientDetails baseClientDetails = new BaseClientDetails(user.getClientId(), null, null, null, null, null);
        jdbcClientDetailsService.addClientDetails(baseClientDetails);
        return ResultUtil.error(2000,"添加成功");
    }
}
