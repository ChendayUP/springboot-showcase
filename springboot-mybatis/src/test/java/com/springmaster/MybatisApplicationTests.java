package com.springmaster;

import com.springmaster.dao.UserMapper;
import com.springmaster.pojo.User;
import com.springmaster.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void testSelectUser() {
        User user = userService.selectUserByName("admin");
        System.out.println(user);
    }
}
