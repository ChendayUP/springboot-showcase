package com.springmaster.controller;

import com.springmaster.model.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(UserInfo user) {
//        主体提交请求进行验证
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
    try {
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

  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  @ResponseBody
  public String logout() {
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return "logout success";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public String delete() {
    return "delete1 success";
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
    return "admin";
  }

  @RequiresRoles("admin")
  @RequestMapping(value = "/testRole", method = RequestMethod.POST)
  @ResponseBody
  public String testRole() {
    return "testRole success";
  }

  @RequiresRoles("admin1")
  @RequestMapping(value = "/testRole1", method = RequestMethod.POST)
  @ResponseBody
  public String testRole1() {
    // 主体提交请求进行验证
    Subject subject = SecurityUtils.getSubject();

    String res = "";
    if (subject.hasRole("admin1")) {
      res = "testRole1 success";
    } else {
      res = "no admin1";
    }
    return res;
  }

  @RequestMapping(value = "/testRole2", method = RequestMethod.POST)
  @ResponseBody
  public String testRole2() {
    return "testRole2 success";
  }

  @RequestMapping(value = "/testRole3", method = RequestMethod.POST)
  @ResponseBody
  public String testRole3() {
    return "testRole3 success";
  }

  @RequestMapping(value = "/testPerms", method = RequestMethod.POST)
  @ResponseBody
  public String testPerms() {
    return "testPerms success";
  }

  @RequestMapping(value = "/testPerms1", method = RequestMethod.POST)
  @ResponseBody
  public String testPerms1() {
    return "testPerms1 success";
  }

  @RequiresPermissions("admin:update")
  @RequestMapping(value = "/testAdmin", method = RequestMethod.POST)
  @ResponseBody
  public String testAdmin() {
    return "admin:update success";
  }

  @RequiresPermissions("admin:add")
  @RequestMapping(value = "/testAdmin1", method = RequestMethod.POST)
  @ResponseBody
  public String testAdmin1() {
    return "admin:add success";
  }
}
