package com.springmaster.MockMVC;

import com.springmaster.config.MyShiroRealm;
import com.springmaster.config.ShiroConfig;
import com.springmaster.controller.LoginController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.Resource;
import org.apache.shiro.mgt.SecurityManager;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Resource
    private SecurityManager securityManager;
    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private Subject subject;
    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;

    private void login(String username, String password) {
        subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse)
                .buildWebSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        subject.login(token);
        ThreadContext.bind(subject);
    }

    @Before
    public void init() {
        mockHttpServletRequest = new MockHttpServletRequest(webApplicationContext.getServletContext());
        mockHttpServletResponse = new MockHttpServletResponse();
        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        mockHttpServletRequest.setSession(mockHttpSession);
        SecurityUtils.setSecurityManager(securityManager);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        login("user2", "good");
    }
    @Test
    public void testUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","user1")
                .param("password", "123456")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("{'key':'value'}"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("value"))
                .andExpect(MockMvcResultMatchers.content().string("no admin no admin:delete"))
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse().toString());
    }

    @Test
    public void testAdmin() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","user2")
                .param("password", "good")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("{'key':'value'}"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("value"))
                .andExpect(MockMvcResultMatchers.content().string("admin admin:delete"))
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse().toString());
    }

    @Test
    public void testIsAdmin() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/isAdmin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("{'key':'value'}"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.key").value("value"))
                .andExpect(MockMvcResultMatchers.content().string("admin"))
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result.getResponse().toString());
    }
}
