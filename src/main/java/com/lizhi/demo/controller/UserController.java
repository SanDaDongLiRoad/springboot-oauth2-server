package com.lizhi.demo.controller;

import com.lizhi.demo.annotation.Classification;
import com.lizhi.demo.annotation.MethodClassification;
import com.lizhi.demo.service.UserService;
import com.lizhi.demo.utils.Result;
import com.lizhi.demo.utils.ResultUtil;
import com.lizhi.demo.vo.AuthUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lenovo
 */
@Slf4j
@Classification
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名查询授信用户信息
     * @param username
     * @return
     *
     */
    @GetMapping("queryAuthInfoByUsername")
    @MethodClassification(BUSTYPE = MethodClassification.TYPE.DX)
    public Result<AuthUserInfoVO> queryAuthInfoByUsername(@RequestParam String username){
        AuthUserInfoVO authUserInfoVO  = userService.queryAuthInfoByUsername(username);
        return ResultUtil.success(authUserInfoVO);
    }

    /**
     * 查询授信用户信息列表
     * @return
     */
    @GetMapping("queryAuthInfoList")
    @MethodClassification(BUSTYPE = MethodClassification.TYPE.DX)
    public Result<List<AuthUserInfoVO>> queryAuthInfoList(){
        List<AuthUserInfoVO> authUserInfoVOList = userService.queryAuthInfoList();

        return ResultUtil.success(authUserInfoVOList);
    }

    @PostMapping("saveAuthInfo")
    @MethodClassification(BUSTYPE = MethodClassification.TYPE.DX)
    public Result<String> saveAuthInfo(@RequestBody AuthUserInfoVO authUserInfoVO){
        authUserInfoVO.setUsername("shawn");
        Result rs = userService.saveAuthInfo(authUserInfoVO);
        return rs;
    }
}
