package com.lizhi.demo.service.impl;

import com.lizhi.demo.domain.User;
import com.lizhi.demo.domain.UserRole;
import com.lizhi.demo.dto.ClientDetailsDTO;
import com.lizhi.demo.dto.CustomUserDetails;
import com.lizhi.demo.repository.UserRepository;
import com.lizhi.demo.service.UserRoleService;
import com.lizhi.demo.service.UserService;
import com.lizhi.demo.utils.Result;
import com.lizhi.demo.utils.ResultUtil;
import com.lizhi.demo.vo.AuthUserInfoVO;
import com.lizhi.demo.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author lenovo
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RedisTemplate redisTemplate;

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


    @Transactional
    @Override
    public Result<String> saveAuthInfo(AuthUserInfoVO authUserInfoVO) {

        User checkUser = queryUserByName(authUserInfoVO.getUsername());
        if(!Objects.equals(null,checkUser)){
            return ResultUtil.error(2001,"该用户名已存在");
        }

        //生成clientid、clientSecret
        String clientid = getClientId();
        String clientSecret = generateString(20);
        ClientDetailsDTO clientDetailsDTO = new ClientDetailsDTO(clientid, clientSecret, null, "read,write", "password,refresh_token", null);
        //保存客户端信息
        jdbcClientDetailsService.addClientDetails(clientDetailsDTO);

        User user = new User();
        user.setUsername(authUserInfoVO.getUsername());
        user.setPassword(authUserInfoVO.getPassword());
        user.setClientId(clientid);
        user.setClientSecret(clientSecret);
        user.setScope("read,write");
        //保存客户信息
        userRepository.save(user);
        List<RoleVo> roleVoList = authUserInfoVO.getRoleVoList();
        for(RoleVo roleVo : roleVoList){
            UserRole userRole = new UserRole();
            userRole.setRoleid(roleVo.getRoleid());
            userRole.setRolename(roleVo.getRolename());
            userRole.setUsername(authUserInfoVO.getUsername());
            //保存用户角色信息
            userRoleService.saveUserRole(userRole);
            redisTemplate.opsForSet().add("USER_ROLE_RELATION"+authUserInfoVO.getUsername(),roleVo.getRolename());
        }
        return ResultUtil.error(2000,"添加成功");
    }

    //获取未被使用的clientId
    private String getClientId() {
        String clientId = null;
        //保证进入while循环，获取未被使用的clientId
        User temp = new User();
        while (temp != null) {
            clientId = this.generateString(15);
            log.info("method : getClientId ; new clientId = " + clientId);
            //查询此appid是否已被使用
            temp = userRepository.findByClientId(clientId);
            log.info("method : getClientId ; query by clientId result is " + temp);
        }
        return clientId;
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    private String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = null;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            log.error("context",e);
        }
        if (random != null) {
            for (int i = 0; i < length; i++) {
                sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
            }
        }
        return sb.toString();
    }
}
