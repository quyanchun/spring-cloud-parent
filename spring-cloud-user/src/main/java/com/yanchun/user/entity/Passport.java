package com.yanchun.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author quyanchun
 * @Date 2018/12/6
 */
@Entity
@Getter
@Setter
@Table(name="passport")
public class Passport {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = true, length = 20)
    private String userName;

    @Column(name = "agee", nullable = true, length = 50)
    private String password;
}
