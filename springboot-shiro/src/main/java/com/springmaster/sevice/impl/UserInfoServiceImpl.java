package com.springmaster.sevice.impl;

import com.springmaster.model.SysPermission;
import com.springmaster.model.SysRole;
import com.springmaster.model.UserInfo;
import com.springmaster.sevice.UserInfoService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.invoke.CallSite;
import java.util.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    Map<String, UserInfo> userInfoDao = new HashMap<>();
    Map<String, List<SysRole>> userRolesDao = new HashMap<>();
    Map<String, List<SysPermission>> userPermissionDao = new HashMap<>();
    {
        // 模拟用户数据保存
        userInfoDao.put("user1", new UserInfo("user1", "93b1a2fd686e7e39fc97e6336e821e41")); // 密码:123456
        userInfoDao.put("user2", new UserInfo("user2", "91769b0b5fb3d023b847f1bfdc61aa10")); // 密码:good

        // 模拟用户分配角色
        SysRole role = new SysRole("admin");
        SysRole role2 = new SysRole("user");
        List<SysRole> list = new ArrayList<>();
        list.add(role2);
        userRolesDao.put("user1", list);

        // 模拟用户分配角色
        List<SysRole> list2 = new ArrayList<>();
        list2.add(role);
        list2.add(role2);
        userRolesDao.put("user2", list2);

        // 模拟user角色分配权限
        SysPermission per1 = new SysPermission("user:add");
        SysPermission per2 = new SysPermission("user:update");
        SysPermission per3 = new SysPermission("admin:delete");
        List<SysPermission> perList = new ArrayList<>();
        perList.add(per1);
        perList.add(per2);
        userPermissionDao.put("user",perList);

        // 模拟admin角色分配权限
        List<SysPermission> perList2 = new ArrayList<>();
        perList2.add(per1);
        perList2.add(per2);
        perList2.add(per3);
        userPermissionDao.put("admin",perList2);
    }
    @Override
    public UserInfo queryByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");

        return userInfoDao.get(username);
    }

    @Override
    public List<SysRole> queryRolesByUsername(String username) {
        return userRolesDao.get(username);
    }

    @Override
    public List<SysPermission> queryPermissionByRole(String role) {
        return userPermissionDao.get(role);
    }


}