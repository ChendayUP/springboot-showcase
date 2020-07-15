package com.springmaster.model;

public class SysPermission {

//    private String name;//名称
    private String name; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    public SysPermission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    private Long parentId; //父编号
//    private String parentIds; //父编号列表
//    private Boolean available = Boolean.FALSE;

}