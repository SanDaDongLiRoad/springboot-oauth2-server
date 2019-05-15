package com.lizhi.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Xiaoyue Xiao
 */
@Data
@Entity
public class UserRole implements Serializable {
    private static final long serialVersionUID = -8406079621878577749L;

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private Long roleid;
    private String rolename;
}
