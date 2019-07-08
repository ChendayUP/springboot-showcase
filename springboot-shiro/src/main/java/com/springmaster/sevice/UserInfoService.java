package com.springmaster.sevice;

import com.springmaster.model.SysPermission;
import com.springmaster.model.SysRole;
import com.springmaster.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo queryByUsername(String username);
    public List<SysRole> queryRolesByUsername(String username);
    public List<SysPermission> queryPermissionByRole(String role);
}