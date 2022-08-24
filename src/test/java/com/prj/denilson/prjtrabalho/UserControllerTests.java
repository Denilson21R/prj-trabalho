package com.prj.denilson.prjtrabalho;

import com.prj.denilson.prjtrabalho.controller.UserController;
import com.prj.denilson.prjtrabalho.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void TestCanGetAllUsers() throws Exception {
        //prepare

        //act
        MvcResult result = mvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String content = result.getResponse().toString();
    }
}
