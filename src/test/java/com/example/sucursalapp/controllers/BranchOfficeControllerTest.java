package com.example.sucursalapp.controllers;

import com.example.sucursalapp.SucursalAppApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = SucursalAppApplication.class)

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test_application.properties")
@AutoConfigureTestDatabase
public class BranchOfficeControllerTest {

    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(BranchOfficeControllerTest.class);

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void verifyAllBranchOffice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/branch-office").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5))).andDo(print());
    }


    @Test
    public void verifyBranchOfficeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/branch-office/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void verifyInvalidBranchOfficeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/branch-office/0").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andDo(print());
    }


}
