package com.springboot.app.springbootfirstapp.user;

import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*") // cross origin resource sharing (CORS) to allow requests from any
                                                  // origin
@RestController

@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register") // http://localhost:8080/auth/register
    public String create(@RequestParam String username, @RequestParam String password) throws Exception {

        try {
            UserService.insertUser(username, password);
            return username;

        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/login") // http://localhost:8080/auth/login
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {

        System.out.println("username: " + username);

        if (UserService.validateUser(username, password)) {
            return UserService.createAccessToken(username);
        } else {
            return "Invalid username or password";
        }
    }

    @PostMapping("/deleteAccount") // http://localhost:8080/auth/deleteaccount
    public String deleteAccount(@RequestParam String username, @RequestParam String password) throws Exception {
    
            if (UserService.validateUser(username, password)) {
                UserService.removeUser(username);
                return "Account sucesfully deleted!";
            } else {
                return "Invalid username or password";
            }
        }
}
