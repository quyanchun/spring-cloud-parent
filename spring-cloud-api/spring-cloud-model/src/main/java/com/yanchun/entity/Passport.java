package com.yanchun.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author quyanchun
 * @Date 2018/12/10
 */
@Entity
public class Passport implements Serializable {
    private long id;
    private String phone;
    private String password;
    private String email;
    private String unionid;
    private Timestamp createTime;
    private Timestamp lastTime;
    private Integer status;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "unionid")
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_time")
    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return id == passport.id &&
                Objects.equals(phone, passport.phone) &&
                Objects.equals(password, passport.password) &&
                Objects.equals(email, passport.email) &&
                Objects.equals(unionid, passport.unionid) &&
                Objects.equals(createTime, passport.createTime) &&
                Objects.equals(lastTime, passport.lastTime) &&
                Objects.equals(status, passport.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, password, email, unionid, createTime, lastTime, status);
    }
}
