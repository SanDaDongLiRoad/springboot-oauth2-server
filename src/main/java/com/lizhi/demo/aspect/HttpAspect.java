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
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private RedisTemplate redisTemplate;

    @Pointcut("execution(public * com.lizhi.demo.controller.*.*(..))")
    public void log() {
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization").replace("Bearer","").intern();

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
        Set<String> userRoleSet = redisTemplate.opsForSet().members("USER_ROLE_RELATION"+customUserDetails.getUsername());
        if(Objects.equals(null,userRoleSet) || Objects.equals(userRoleSet.size(),0)){
            List<UserRole> userRoleList = userRoleService.queryListByUsername(customUserDetails.getUsername());

            for(UserRole userRole : userRoleList){
                if(Objects.equals(String.valueOf(tMethodClassification.BUSTYPE()),userRole.getRolename())){
                    return joinPoint.proceed();
                }
            }
        }else{
            for(String roleName : userRoleSet){
                if(Objects.equals(String.valueOf(tMethodClassification.BUSTYPE()),roleName)){
                    return joinPoint.proceed();
                }
            }
        }
        return ResultUtil.error(-1,"无权限");
    }
}
