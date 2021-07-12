package com.dj.springboot;

import com.dj.springboot.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class SpringbootApplicationTests {

    private MockMvc mvc;

    @Test
    public void testHello() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new AccountController()).build();
        mvc.perform(MockMvcRequestBuilders.get("/springboot/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void contextLoads() {
    }

}
