package com.springboot.app.springbootfirstapp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HintGeneratorTest {
    
    @Test
    public void testHint_startsWithLetter_yemen() throws Exception {
        String question = "Name A Country in the World that starts with the letter y";
        String generatedHint = HintGenerator.makeHint(question);
        char firstLetterOfHint = generatedHint.charAt(0);
        // make sure the first letter of the hint is 'y' (this is a deterministic aspect of the hint generator when the question type is "begin")
        assertEquals('y', firstLetterOfHint);
        // make sure the hint is the correct length
        assertEquals(5, generatedHint.length());
    }

}
