package com.dj.springboot.controller;

import com.dj.springboot.common.base.ResponseData;
import com.dj.springboot.common.util.JWTUtils;
import com.dj.springboot.service.account.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//@RestController注解能够使项目支持Rest
@RestController
//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value = "/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    //这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping(value = "/getUserByGet", method = RequestMethod.GET)
    String getUserByGet(@RequestParam(value = "userName") String userName) {
        return "Hello Get " + userName;
    }

    @RequestMapping(value = "/getUserByPost", method = RequestMethod.POST)
    String getUserByPost(@RequestParam(value = "userName") String userName) {
        return "Hello Post " + userName;
    }

    @RequestMapping(value = "/getUserByJosn", method = RequestMethod.POST)
    String getUserByJson(@RequestBody String data) {
        return "Json2 is " + data;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @PostMapping("/login")
    public ResponseData login(String username, String password) {
        return accountService.login(username,password);
    }

    @GetMapping(value="/findAll")
    public Object findAll() {
        return accountService.findAll();
    }
}
