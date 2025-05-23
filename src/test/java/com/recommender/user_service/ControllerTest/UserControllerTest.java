package com.recommender.user_service.ControllerTest;

import com.recommender.user_service.model.ESize;
import com.recommender.user_service.model.EStyle;
import com.recommender.user_service.model.User;
import com.recommender.user_service.service.UserService;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recommender.user_service.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("GET /api/user/{id} devuelve un usuario correctamente")
    public void getUserByIdTest() throws Exception {
        User user = new User();
        user.setName("MockUser");
        user.setSizes(List.of(ESize.L));
        user.setStyles(List.of(EStyle.PUNK));
        var savedUser = userService.saveUser(user);

        MvcResult result = mockMvc.perform(get("/api/user/" + savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("MockUser"));

    }


}
