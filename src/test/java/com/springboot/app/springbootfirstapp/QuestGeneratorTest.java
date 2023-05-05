package com.springboot.app.springbootfirstapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestGeneratorTest {

    @Test
    // check question parser works for "any mode"
    public void checkAnswer_validAnswer_returnsCorrectScore_anyMode() throws Exception {
        String question = "Name A State in the USA";
        String answer = "Indiana";
        int expectedScore = 7;
        int score = QuestGenerator.checkAnswer(question, answer);
        assertEquals(expectedScore, score);
    }

    @Test
    // check question parser works for "starts with mode"
    public void checkAnswer_validAnswer_returnsCorrectScor_startsWithMode() throws Exception {
        String question = "Name A State in the USA That Starts With The Letter H";
        String answer = "Hawaii";
        int expectedScore = 6;
        int score = QuestGenerator.checkAnswer(question, answer);
        assertEquals(expectedScore, score);
    }

    // SOFT SCORING TESTS
    @Test
    // check question parser excludes spaces from answer score
    public void checkAnswer_validAnswer_returnsCorrectScore_trimmed() throws Exception {
        String question = "Name A State in the USA That Starts With The Letter N";
        String answer = " New Hampshire";
        int expectedScore = 12;
        int score = QuestGenerator.checkAnswer(question, answer);
        assertEquals(expectedScore, score);
    }

    @Test
    // check question parser excludes spaces from answer score
    public void checkAnswer_validAnswer_returnsCorrectScore_sandwichedSpaces() throws Exception {
        String question = "Name A State in the USA That Starts With The Letter N";
        String answer = "Newhampshire";
        int expectedScore = 12;
        int score = QuestGenerator.checkAnswer(question, answer);
        assertEquals(expectedScore, score);
    }

    @Test
    // check question parser works for "ends with mode"
    public void checkAnswer_validAnswer_returnsCorrectScore_endsWithMode() throws Exception {
        String question = "Name A Word in the English Language That Ends With The Letter Y";
        String answer = "Concurrency";
        int expectedScore = 11;
        int score = QuestGenerator.checkAnswer(question, answer);
        assertEquals(expectedScore, score);
    }

    @Test
    // check question parser works for invalid answer
    public void checkAnswer_invalidAnswer_returnsZeroScore() throws Exception {
        String question = "Name A State in the USA";
        String answer = "Singapore";
        int expectedScore = 0;
        int score = QuestGenerator.checkAnswer(question, answer);
        assertEquals(expectedScore, score);
    }

    @Test
    // generate a question like /getq and check that it is valid
    public void generateQuestion_validQuestion_returnsValidQuestion() throws Exception {
        String question = QuestGenerator.getRandomQuestion();
        String[] parsedQuestion = QuestionUtil.parseQuestion(question);
        assertEquals(3, parsedQuestion.length); // question parser should return 3 elements
        String questionType = parsedQuestion[0];
        // check that the question type is valid
        assertEquals(true,
                questionType.equals(QuestionUtil.QUEST_TYPE_BEGIN) || questionType.equals(QuestionUtil.QUEST_TYPE_END)
                        || questionType.equals(QuestionUtil.QUEST_TYPE_ANY));
    }

}