package com.dj.springboot.common.auth;

import org.springframework.stereotype.Component;

@Component
public class AccountInfoContext {

    private static ThreadLocal<AccountInfo> userInfo = new ThreadLocal<>();

    public AccountInfoContext() {
    }

    public static AccountInfo getUser() {
        return userInfo.get();
    }

    public static void setUser(AccountInfo user) {
        userInfo.set(user);
    }
}
