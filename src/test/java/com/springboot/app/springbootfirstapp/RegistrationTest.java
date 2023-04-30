package com.springboot.app.springbootfirstapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {
    @Test // adding and removing a user
    public void testRegistration() throws Exception {
        String username = "tempUser";
        String password = "tempPassword";
        UserService.insertUser(username, password);
        assertEquals(true, UserService.validateUser(username, password));
        UserService.removeUser(username); // remove test user
        assertEquals(false, UserService.validateUser(username, password)); // check if user is removed
    }

    @Test // login and validate
    public void testLogin() throws Exception {
        String username = "newPlayer";
        String password = "newPlayerPassword";
        UserService.insertUser(username, password);
        assertEquals(true, UserService.validateUser(username, password));
        UserService.removeUser(username);
        assertEquals(false, UserService.validateUser(username, password));
    }
    
}
