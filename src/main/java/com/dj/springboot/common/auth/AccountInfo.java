package com.dj.springboot.common.auth;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountInfo implements Serializable {

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;



    /**
     * 失效时间
     */
    private LocalDateTime leftTime;

    /**
     * 是否有效
     */
    private Boolean isactive;
}
