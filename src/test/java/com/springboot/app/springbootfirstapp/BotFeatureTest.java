package com.springboot.app.springbootfirstapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class BotFeatureTest {

    @Test
    public void testBot_startsWithLetter() throws Exception { // Test Bot's ability to conditionally query the database
        String question = "Name A Country in the World that starts with the letter y";
        String botResponse = QuestGenerator.botAnswer(question);
        assertEquals("yemen", botResponse); // Yemen is the only country that starts with the letter y
    }

    @Test
    public void testBot_endsWithLetter() throws Exception {
        String question = "Name A Country in the World that Ends with the letter y";
        String botResponse = QuestGenerator.botAnswer(question);

        List<String> countries = new ArrayList<String>();
        countries.add("germany");
        countries.add("hungary");
        countries.add("italy");
        countries.add("norway");
        countries.add("paraguay");
        countries.add("turkey");
        countries.add("uruguay");
        countries.add("vatican city");

        // assert that bot's response is one of the countries in the list
        assertEquals(true, countries.contains(botResponse));
    }
}