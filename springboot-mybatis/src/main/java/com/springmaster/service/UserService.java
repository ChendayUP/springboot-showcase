package com.springmaster.service;

import com.springmaster.dao.UserMapper;
import com.springmaster.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    /**
     * 根据名字查找用户
     */
    public User selectUserByName(String name) {
        return userMapper.selectByUsername(name);
    }
    /**
     * 查找所有用户
     */
    public List<User> selectAllUser() {
        return userMapper.findAllUser();
    }
    /**
     * 插入两个用户
     */
    public void insertService(String name) {
        User user1 = new User(0, name, "123456", "admin@163.com", "1234567", "question", "answer", 1);
        userMapper.insert(user1);
    }
    /**
     * 根据id 删除用户
     */
    public void deleteService(int id) {
        userMapper.deleteByPrimaryKey(id);
    }
    /**
     * 模拟事务。由于加上了 @Transactional注解，如果修改中途出了意外 username不会改变。
     */
    @Transactional
    public void changemoney() {
        User user1 = new User(1, "oldUser", "123456", "admin@163.com", "1234567", "question", "answer", 1);
        userMapper.updateByPrimaryKeySelective(user1);
        // 模拟转账过程中可能遇到的意外状况
        int temp = 1 / 0;
        user1.setUsername("newUser");
        userMapper.updateByPrimaryKeySelective(user1);
    }
}
