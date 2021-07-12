package com.dj.springboot.service.account.service;

import com.dj.springboot.common.base.ResponseData;
import com.dj.springboot.entity.Account;

import java.util.List;

public interface AccountService {

    ResponseData login(String username, String password);

    /**
     * 查找所有用户
     * @return
     */
    List<Account> findAll();
}
