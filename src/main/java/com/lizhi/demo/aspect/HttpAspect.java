package com.lizhi.demo.aspect;

import com.lizhi.demo.annotation.MethodClassification;
import com.lizhi.demo.domain.Role;
import com.lizhi.demo.domain.User;
import com.lizhi.demo.domain.UserRole;
import com.lizhi.demo.dto.CustomUserDetails;
import com.lizhi.demo.service.RoleService;
import com.lizhi.demo.service.UserRoleService;
import com.lizhi.demo.service.UserService;
import com.lizhi.demo.utils.ResultUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * Created by xulizhi
 * 2017-01-15 12:31
 * @author lenovo
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Pointcut("execution(public * com.lizhi.demo.controller.*.*(..))")
    public void log() {
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization").replace("Bearer","").intern();

        //url
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}", joinPoint.getArgs());


        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        MethodClassification tMethodClassification = currentMethod.getAnnotation(MethodClassification.class);
        if (tMethodClassification != null) {
            logger.info("des is:" + tMethodClassification.DES() + "  type is:" + tMethodClassification.BUSTYPE());
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) tokenStore.readAuthentication(token.split(" ")[1]).getPrincipal();
        User user = userService.queryUserByName(customUserDetails.getUsername());
        List<UserRole> userRoleList = userRoleService.queryListByUserid(user.getId());
        for(UserRole userRole : userRoleList){
            Role role = roleService.queryById(userRole.getRoleid());
            if(Objects.equals(String.valueOf(tMethodClassification.BUSTYPE()),role.getName())){
                return joinPoint.proceed();
            }
        }

        return ResultUtil.error(-1,"无权限");
    }

    @After("log()")
    public void doAfter() {
        logger.info("222222222222");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }

}