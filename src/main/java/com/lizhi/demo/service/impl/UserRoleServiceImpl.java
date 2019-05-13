package com.lizhi.demo.service.impl;

import com.lizhi.demo.domain.UserRole;
import com.lizhi.demo.repository.UserRoleReposiyory;
import com.lizhi.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lenovo
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleReposiyory userRoleReposiyory;

    @Override
    public List<UserRole> queryListByUserid(Long userid) {
        List<UserRole> userRoleList = userRoleReposiyory.findByUserid(userid);
        if(Objects.equals(null,userRoleList)){
            return new ArrayList<UserRole>();
        }
        return userRoleList;
    }
}
