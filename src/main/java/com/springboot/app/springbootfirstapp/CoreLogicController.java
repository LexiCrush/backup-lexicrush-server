package com.springboot.app.springbootfirstapp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/results")
    public String results(@RequestHeader("Access-Token") String accessToken) {

        if (accessToken == null) {
            return "Unauthorized";
        } else {

            String[] parts = accessToken.split("\\|");
            String username = parts[0];
            long expiredAt = Long.parseLong(parts[1]);

            if (expiredAt < System.currentTimeMillis()) {
                return "Session Expired";
            } else {

                return "Authenticated User: " + username;

            }
        }
    }

    @PostMapping("/checkans") // http://localhost:8080/checkans
    public int checkans(@RequestParam String question, @RequestParam String answer) throws Exception {
        System.out.println(QuestGenerator.checkAnswer(question, answer)); // TODO remove
        return QuestGenerator.checkAnswer(question, answer);
    }

}
