package com.springmaster.controller;

import com.springmaster.model.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserInfo user) {
//        主体提交请求进行验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
//            token.setRememberMe(user.isRememberMe());
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        String res = "";
        if (subject.hasRole("admin")) {
            res = "admin";
        } else {
            res = "no admin";
        }
        if (subject.isPermitted("admin:delete")) {
            res += " admin:delete";
        } else {
            res += " no admin:delete";
        }
        return res;
    }

    @RequestMapping(value = "/isAdmin", method = RequestMethod.POST)
    public String isAdmin() {
//        主体提交请求进行验证
        Subject subject = SecurityUtils.getSubject();

        String res = "";
        if (subject.hasRole("admin")) {
            res = "admin";
        } else {
            res = "no admin";
        }
        return res;
    }

}
