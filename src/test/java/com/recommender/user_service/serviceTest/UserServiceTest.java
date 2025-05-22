package com.recommender.user_service.serviceTest;

import com.recommender.user_service.exceptions.UserNotFoundException;
import com.recommender.user_service.model.ESize;
import com.recommender.user_service.model.EStyle;
import com.recommender.user_service.model.User;
import com.recommender.user_service.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setName("Test User");
        testUser.setSizes(List.of(ESize.M, ESize.L));
        testUser.setStyles(List.of(EStyle.CASUAL, EStyle.FORMAL));
        testUser = userService.saveUser(testUser);
    }

    @AfterEach
    void tearDown() {
        userService.deleteUser(testUser.getId());
    }

    @Test
    @DisplayName("Find by Id works correctly")
    public void getUserByIdTest(){

        User foundUser= userService.getUserById(testUser.getId());

        assertNotNull(foundUser);

        assertEquals("Test User", foundUser.getName());
        assertEquals(testUser.getId(), foundUser.getId());

        assertNotNull(foundUser.getStyles());
        assertFalse(foundUser.getStyles().isEmpty());

        assertNotNull(foundUser.getSizes());
        assertFalse(foundUser.getSizes().isEmpty());

    }

    @Test
    @DisplayName("Save user works correctly")
    public void saveUserTest(){
        User user = new User();
        user.setName("Julian");
        user.setStyles(List.of(EStyle.BOHEMIAN,EStyle.FORMAL));
        user.setSizes(List.of(ESize.XS));

        User savedUser= userService.saveUser(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());

        assertEquals("Julian", savedUser.getName());
        assertEquals(ESize.XS, savedUser.getSizes().getFirst());
        assertTrue(savedUser.getStyles().containsAll(List.of(EStyle.BOHEMIAN, EStyle.FORMAL)));


    }

    @Test
    @DisplayName("Delete user works correctly")
    public void deleteUserTest(){
        User user = new User();
        user.setName("Julian");
        user.setStyles(List.of(EStyle.BOHEMIAN,EStyle.FORMAL));
        user.setSizes(List.of(ESize.XS));

        User savedUser = userService.saveUser(user);

        User delUser= userService.deleteUser(savedUser.getId());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(savedUser.getId());
        });
    }


}
