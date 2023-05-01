package com.springboot.app.springbootfirstapp;

import org.junit.jupiter.api.Test;

import com.springboot.app.springbootfirstapp.user.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRegistrationTest {
    @Test // adding and removing a user
    public void testRegistration() throws Exception {
        String username = "tempUser";
        UserService.removeUser(username); // remove test user if already exists
        String password = "tempPassword";
        UserService.insertUser(username, password);
        assertEquals(true, UserService.validateUser(username, password));
        UserService.removeUser(username); // remove test user
        assertEquals(false, UserService.validateUser(username, password)); // check if user is removed
    }

    @Test // login and validate
    public void testLogin() throws Exception {
        String username = "newPlayer";
        UserService.removeUser(username); // remove test user if already exists
        String password = "newPlayerPassword";
        UserService.insertUser(username, password);
        assertEquals(true, UserService.validateUser(username, password));
        UserService.removeUser(username);
        assertEquals(false, UserService.validateUser(username, password));
    }

    @Test // ensure user is a valid user (used in score service)
    public void testUserExists() throws Exception {
        String username = "Hacker";
        UserService.removeUser(username); // remove test user if already exists
        String password = "HackersPassword";
        UserService.insertUser(username, password);
        assertEquals(true, UserService.userExists(username));
        UserService.removeUser(username);
        assertEquals(false, UserService.userExists(username));
    }

}
