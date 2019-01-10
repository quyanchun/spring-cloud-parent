package com.yanchun.common.dto;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author quyanchun
 * @Date 2018/12/11
 */
public class PassportDTO  {
    private long id;
    private String phone;
    private String password;
    private String email;
    private String unionid;
    private Timestamp createTime;
    private Timestamp lastTime;
    private Integer status;
    private Integer regWay;
    private Integer regType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, password, email, unionid, createTime, lastTime, status);
    }

    public Integer getRegWay() {
        return regWay;
    }

    public void setRegWay(Integer regWay) {
        this.regWay = regWay;
    }

    public Integer getRegType() {
        return regType;
    }

    public void setRegType(Integer regType) {
        this.regType = regType;
    }
}
