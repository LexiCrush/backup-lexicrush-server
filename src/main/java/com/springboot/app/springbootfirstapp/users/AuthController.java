package com.springboot.app.springbootfirstapp.users;

import org.python.icu.impl.duration.TimeUnit;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*") // cross origin resource sharing (CORS) to allow requests from any origin
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

            // Build Access Token
            long expiredAt = System.currentTimeMillis() + 1000 * 60 * 60 * 2; 

            // user1|1234567890
            return username + "|" + expiredAt;
        }
        else {
            return null;
        }
    }
}

