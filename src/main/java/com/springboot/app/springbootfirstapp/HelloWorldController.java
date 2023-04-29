package com.springboot.app.springbootfirstapp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*") // cross origin resource sharing (CORS) to allow requests from any origin
@RestController

@RequestMapping("/api")
public class HelloWorldController {
    
    @GetMapping("/getq") // http://localhost:8080/api/getq
    public String randq() throws Exception {
        String q = QuestGenerator.getRandomQuestion();
        System.out.println("GET Question: " + q);
        return q;
    }

    // @GetMapping("/bots") // http://localhost:8080/api/botscore
    // public String botscore() {
    //     return "TODO";
    // }


    // curl -X POST -H "Content-Type: application/json" -d '{"message":"Hello World!"}' http://localhost:8080/send
    @PostMapping("/checkans") // http://localhost:8080/checkans 
    public int checkans(@RequestParam String question, @RequestParam String answer) throws Exception {
        System.out.println("POST Question: " + question);
        System.out.println("POST Answer: " + answer);
        System.out.println("POST Result: " + QuestGenerator.checkAnswer(question, answer));
        return QuestGenerator.checkAnswer(question, answer);
    }
}