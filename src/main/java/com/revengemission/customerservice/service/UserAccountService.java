package com.revengemission.customerservice.service;

import com.revengemission.customerservice.domain.NotImplementException;
import com.revengemission.customerservice.domain.UserAccount;

public interface UserAccountService extends CommonInterface<UserAccount> {
    default UserAccount login(String username, String password) {
        throw new NotImplementException();
    }
}
