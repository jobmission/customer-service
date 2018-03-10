package com.revengemission.customerservice.dao.entity;

import javax.persistence.*;


@Entity
@Table(name = "user_account_entity",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username"})
)
public class UserAccountEntity extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户登录名,必须唯一，字母,邮箱或者手机号等'")
    private String username;

    private String password;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '昵称'")
    private String nickName;

    @Column(columnDefinition = "VARCHAR(200) COMMENT '上传头像地址'")
    private String avatarUrl;

    @Column(columnDefinition = "VARCHAR(50) COMMENT '邮箱，用于找回密码，接收通知等'")
    private String email;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '用户联系电话'")
    private String mobile;

    @Column(columnDefinition = "VARCHAR(30) COMMENT '省份'")
    private String province;

    @Column(columnDefinition = "VARCHAR(30) COMMENT '城市'")
    private String city;

    @Column(columnDefinition = "VARCHAR(150) COMMENT '详细地址'")
    private String address;


    private Integer creditScore;

    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
