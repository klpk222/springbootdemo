package com.dj.springboot.service.account.service.impl;

import com.dj.springboot.common.base.ResponseData;
import com.dj.springboot.common.util.JWTUtils;
import com.dj.springboot.dao.AccountMapper;
import com.dj.springboot.entity.Account;
import com.dj.springboot.service.account.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private JWTUtils jwtUtils;

    @Resource
    private AccountMapper accountMapper;

    @Override
    public ResponseData login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)){
            Account account = accountMapper.getAccountByName(username);
            if (account==null){
                return ResponseData.fail("账号不存在");
            }else {
                if (!StringUtils.equals(account.getPassword(), password)){
                    return ResponseData.fail("密码错误");
                }else {
                    JWTUtils jwt = jwtUtils.getInstance();
                    String token = jwt.getToken(username, password);
                    map.put("token", token);
                }
            }
        } else {
            return ResponseData.fail("账号或密码为空");
        }
        return ResponseData.ok(map);
    }

    /**
     * 查找所有用户
     * @return
     */
    @Override
    public List<Account> findAll() {
        return accountMapper.selectAll();
    }
}
