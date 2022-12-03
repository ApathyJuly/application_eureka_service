package com.example.service_hi.user.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-11-30 11:29:11
 */
public class User implements Serializable {
    private static final long serialVersionUID = -54785249752877700L;

    private String userId;

    private String userName;

    private String password;

    private String userPhone;

    private String userAddr;

    private String userWage;

    private String userSex;

    private String salt;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserWage() {
        return userWage;
    }

    public void setUserWage(String userWage) {
        this.userWage = userWage;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", userWage='" + userWage + '\'' +
                ", userSex='" + userSex + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}