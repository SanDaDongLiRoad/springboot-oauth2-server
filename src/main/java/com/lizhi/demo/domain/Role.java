package com.lizhi.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Xiaoyue Xiao
 */
@Data
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = -7702542502260497140L;

    @Id
    private Long id;
    private String name;
}
