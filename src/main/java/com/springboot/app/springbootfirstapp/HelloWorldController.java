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

    @GetMapping("/getq") // http://localhost:8080/randq
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

    @PostMapping("/checkans") // http://localhost:8080/checkans
    public int checkans(@RequestBody String answer) {
        System.out.println(answer);
        //extract the answer from the JSON string {"data":"hydrogen"} to "hydrogen"
        String extractedAnswer = answer.substring(answer.indexOf(":") + 2, answer.length() - 2);
        System.out.println(extractedAnswer);
        int points = QuestionGenerator.checkAnswer(extractedAnswer);
        System.out.println(points);
        return points;
    }

    // add a new POST method to allow an answer to be posted in a JSON format
    // curl -X POST -H "Content-Type: application/json" -d '{"message":"Hello World!"}' http://localhost:8080/send
    @PostMapping("/send")
    public String send(@RequestBody String message) {
        // print the message to the console
        System.out.println(message);
        // remove the last char from message
        message = message.substring(0, message.length() - 1);
        return "You chose: " + message;
    }

}