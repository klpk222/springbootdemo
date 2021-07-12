package com.dj.springboot.common.auth;

import com.dj.springboot.common.filter.HttpBasicAuthorizeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器配置
 *
 * @author GuoYi
 * @create 2020-05-27 14:05
 **/
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        HttpBasicAuthorizeFilter httpBasicFilter = new HttpBasicAuthorizeFilter();
        registrationBean.setFilter(httpBasicFilter);

        List<String> urlPatterns = new ArrayList<String>(1);
        //拦截这些路径，需要鉴权

        urlPatterns.add("/account/*");

        registrationBean.setUrlPatterns(urlPatterns);

        return registrationBean;
    }
}
