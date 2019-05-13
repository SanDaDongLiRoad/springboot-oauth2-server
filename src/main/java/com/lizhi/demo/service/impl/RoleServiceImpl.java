package com.lizhi.demo.service.impl;

import com.lizhi.demo.domain.Role;
import com.lizhi.demo.repository.RoleRepository;
import com.lizhi.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lenovo
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role queryById(Long roleId) {
        return roleRepository.findOne(roleId);
    }
}
