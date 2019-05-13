//package com.lizhi.demo.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.lizhi.demo.domain.UserRole;
//import com.lizhi.demo.dto.CustomUserDetails;
//import com.lizhi.demo.service.UserRoleService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import java.util.List;
//
///**
// * @author lenovo
// */
//@Slf4j
//@Service
//public class AccessInterceptor  extends HandlerInterceptorAdapter{
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired
//    private UserRoleService userRoleService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handler) throws Exception {
//        CustomUserDetails customUserDetails = (CustomUserDetails) tokenStore.readAuthentication(auth.split(" ")[1]).getPrincipal();
//        Long userId = customUserDetails.getId();
//        List<UserRole> userRoleList = userRoleService.queryListByUserid(userId);
//
//        log.info("我就是拦截后打印一行字一下而已----------------------------------------------------------------------------------");
//        return true;
//    }
//}
//
