//package com.lizhi.demo.service.impl;
//
//import com.lizhi.demo.SpringbootOauth2ServerApplicationTests;
//import com.lizhi.demo.service.UserService;
//import com.lizhi.demo.utils.Result;
//import com.lizhi.demo.vo.AuthUserInfoVO;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.Assert.*;
//
//public class UserServiceImplTest extends SpringbootOauth2ServerApplicationTests {
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void saveAuthInfo() {
//        AuthUserInfoVO authUserInfoVO =new AuthUserInfoVO();
//        authUserInfoVO.setUsername("shawn");
//        Result rs = userService.saveAuthInfo(authUserInfoVO);
//
//        System.out.println(rs);
//    }
//}