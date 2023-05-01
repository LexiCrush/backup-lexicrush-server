package com.springboot.app.springbootfirstapp;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.springbootfirstapp.user.UserService;
import com.springboot.app.springbootfirstapp.user.UserStatService;

@CrossOrigin(origins = "*", allowedHeaders = "*") // cross origin resource sharing will allow requests from any origin
@RestController

@RequestMapping("/api")
public class CoreLogicController {

    @GetMapping("/getq")
    public String randq() throws Exception {
        String question = QuestGenerator.getRandomQuestion();
        System.out.println("GET Question: " + question); // TODO remove
        return question;
    }

    @GetMapping("/bot") // returns the bot's answer
    public String botscore(@RequestParam String question) throws Exception {
        String botAnswer = QuestGenerator.botAnswer(question);
        System.out.println("Bot Answer: " + botAnswer); // TODO remove
        return botAnswer;
    }

    @GetMapping("/hint") // returns a hint
    public String hint(@RequestParam String question) throws Exception {
        String hint = HintGenerator.makeHint(question);
        System.out.println("Hint: " + hint); // TODO remove
        return hint;
    }

    @PostMapping("/score")
    public String results(@RequestHeader("Access-Token") String accessToken, @RequestParam String playerAnswer,
            @RequestParam String botAnswer) throws Exception {
        if (UserService.parseAccessToken(accessToken) == null) {
            return "Invalid access token";
        } else {
            String username = UserService.parseAccessToken(accessToken);
            // compare lengths of playerAnswer and botAnswer in LONGEST MODE
            int pal = playerAnswer.length();
            int bal = botAnswer.length();

            if (pal > bal) {

                int currentScore = UserStatService.getScoreByType(username, "current_score");
                int balPalDiff = pal - bal;
                int updatedScore = currentScore + balPalDiff;
                UserStatService.updateScoreByType(username, updatedScore, "current_score");

                return ("You WIN! Your answer was " + balPalDiff + " letters longer than the bot's answer!");
            } else if (pal < bal) {
                int balPalDiff = bal - pal;
                return "You LOSE! The bot's answer was " + balPalDiff + " letters longer than your answer!";
            } else {
                return "Its a tie... Both answers were" + pal + " letters long!";
            }
        }
    }

    @PostMapping("/checkans") // http://localhost:8080/checkans
    public int checkans(@RequestParam String question, @RequestParam String answer) throws Exception {
        System.out.println(QuestGenerator.checkAnswer(question, answer)); // TODO remove
        return QuestGenerator.checkAnswer(question, answer);
    }

}
