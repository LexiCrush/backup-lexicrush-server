package com.springboot.app.springbootfirstapp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*") // cross origin resource sharing (CORS) to allow requests from any origin
@RestController

@RequestMapping("/api")
public class HelloWorldController {
    
    // http://localhost:8080/getdb 
    @GetMapping("/getdb") 
    public String getdb() {
        // select a random row from the database and return it as a string
        return SQLiteConnector.displayAllTables();
    
    }

    @GetMapping("/getq") // http://localhost:8080/api/getq
    public String randq() {
        QuestionGenerator game = new QuestionGenerator();
        game.getRandomNbTable();
        game.getReadableNounFromTableName();
        game.getFilter();
        game.getMode();
        String assembledPrompt = QuestionGenerator.promptAssembler();
        System.out.println(QuestionGenerator.promptAssembler());
        return assembledPrompt;
    }

    @GetMapping("/bots") // http://localhost:8080/api/botscore
    public String botscore() {
        return QuestionGenerator.botAnswer();
    }

    // curl -X POST -H "Content-Type: application/json" -d '{"message":"Hello World!"}' http://localhost:8080/send
    @PostMapping("/checkans") // http://localhost:8080/checkans 
    public int checkans(@RequestBody String answer) { 
        String extractedAnswer = answer.substring(answer.indexOf(":") + 2, answer.length() - 2);
        System.out.println(extractedAnswer);
        int points = QuestionGenerator.checkAnswer(extractedAnswer);
        System.out.println(points);
        return points;
    }

}