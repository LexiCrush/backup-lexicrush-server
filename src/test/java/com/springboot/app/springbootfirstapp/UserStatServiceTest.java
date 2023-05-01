package com.springboot.app.springbootfirstapp;

import com.springboot.app.springbootfirstapp.users.UserStatService;
import com.springboot.app.springbootfirstapp.users.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UserStatServiceTest {

    @Test
    public void testAllScoreCRUD() throws Exception {

        UserService.insertUser("bestlexicrusher", "imthebest123");

        assertEquals(0, UserStatService.getScoreByType("bestlexicrusher", "current_score"));
        assertEquals(0, UserStatService.getScoreByType("bestlexicrusher", "high_score"));

        UserStatService.updateScoreByType("bestlexicrusher", 100, "current_score");
        UserStatService.updateScoreByType("bestlexicrusher", 12345, "high_score");

        assertEquals(100, UserStatService.getScoreByType("bestlexicrusher", "current_score"));
        assertEquals(12345, UserStatService.getScoreByType("bestlexicrusher", "high_score"));

        UserService.removeUser("bestlexicrusher");

    }

}