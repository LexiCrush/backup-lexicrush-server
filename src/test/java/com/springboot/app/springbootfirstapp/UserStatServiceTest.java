package com.springboot.app.springbootfirstapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.springboot.app.springbootfirstapp.user.UserService;
import com.springboot.app.springbootfirstapp.user.UserStatService;

public class UserStatServiceTest {

    @Test
    public void testCurrentAndHighScore() throws Exception {

        UserService.removeUser("bestlexicrusher");
        UserService.insertUser("bestlexicrusher", "imthebest123");

        assertEquals(0, UserStatService.getScoreByType("bestlexicrusher", "current_score"));
        assertEquals(0, UserStatService.getScoreByType("bestlexicrusher", "high_score"));

        UserStatService.updateScoreByType("bestlexicrusher", 100, "current_score");
        UserStatService.updateScoreByType("bestlexicrusher", 12345, "high_score");

        assertEquals(100, UserStatService.getScoreByType("bestlexicrusher", "current_score"));
        assertEquals(12345, UserStatService.getScoreByType("bestlexicrusher", "high_score"));

        UserService.removeUser("bestlexicrusher");
    }

    @Test
    public void testRemainingUserStats() throws Exception {

        UserService.removeUser("hintfiend");

        UserService.insertUser("hintfiend", "ineedahint123");
        assertEquals(3, UserStatService.getAvailableHints("hintfiend")); // starts with 3 hints
        assertEquals(0, UserStatService.getGamesPlayed("hintfiend"));
        assertEquals(0, UserStatService.getGamesWon("hintfiend"));

        UserStatService.updateAvailableHints("hintfiend", 5);
        UserStatService.updateGamesPlayed("hintfiend", 10);
        UserStatService.updateGamesWon("hintfiend", 5);

        assertEquals(5, UserStatService.getAvailableHints("hintfiend"));
        assertEquals(10, UserStatService.getGamesPlayed("hintfiend"));
        assertEquals(5, UserStatService.getGamesWon("hintfiend"));

        UserService.removeUser("hintfiend");

    }

}