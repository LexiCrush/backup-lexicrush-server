package com.springboot.app.springbootfirstapp;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*") // cross origin resource sharing (CORS) to allow requests from any origin
@RestController

@RequestMapping("/api")
public class HelloWorldController {
    
    @GetMapping("/getq")
    public String randq( ) throws Exception {

        String question = QuestGenerator.getRandomQuestion();
        System.out.println("GET Question: " + question);
        return question;
    }

    @GetMapping("/bot")
    //takes in a question as raw text param and returns a score
    public String botscore(@RequestParam String question) throws Exception {
        // question = question.replaceAll("%20", " ");
        System.out.println("GET Question: " + question);
        String botAnswer = QuestGenerator.botAnswer(question);
        System.out.println("botAnswer: " + botAnswer);
        return botAnswer;
    }

    @GetMapping("/hint")
    //takes in a question as raw text param and returns a hint
    public String hint(@RequestParam String question) throws Exception {
        // question = question.replaceAll("%20", " ");
        String hint = HintGenerator.makeHint(question);
        System.out.println("hint: " + hint);
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
        System.out.println("POST Question: " + question);
        System.out.println("POST Answer: " + answer);
        System.out.println("POST Result: " + QuestGenerator.checkAnswer(question, answer));
        return QuestGenerator.checkAnswer(question, answer);
    }
    
}
