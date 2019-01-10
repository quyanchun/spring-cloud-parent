package com.yanchun.common.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * @Author quyanchun
 * @Date 2018/12/11
 */
public class UserDTO  {
    private long id;
    private long passportId;
    private String nickName;
    private Integer gender;
    private String headImgUrl;
    private String address;
    private String sign;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPassportId() {
        return passportId;
    }

    public void setPassportId(long passportId) {
        this.passportId = passportId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passportId, nickName, gender, headImgUrl, address, sign);
    }
}
