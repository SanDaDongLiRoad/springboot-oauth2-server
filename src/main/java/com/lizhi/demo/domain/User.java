package com.lizhi.demo.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Xiaoyue Xiao
 */
@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 7698862379923111158L;

    @Id
    private Long id;
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
    private String scope;

}
