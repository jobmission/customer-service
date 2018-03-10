package com.revengemission.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccount extends BaseDomain {
    private String username;
    private String password;
    private String role;
    private String nickName;
    private String avatarUrl;
    private String verificationCode;
    private String province;
    private String city;
    private String address;
}
