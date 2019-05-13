package com.lizhi.demo.repository;

import com.lizhi.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lenovo
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
}
