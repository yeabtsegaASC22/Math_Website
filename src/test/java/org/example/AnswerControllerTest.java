package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerControllerTest {

    @Test
    public void TC1_correctAnswer() {
        String result = AnswerController.checkSubmittedAnswer("3", "1 + 2");
        assertEquals("Correct", result);
    }

    @Test
    public void TC2_incorrectAnswer() {
        String result = AnswerController.checkSubmittedAnswer("5", "1 + 2");
        assertEquals("Incorrect", result);
    }

    @Test
    public void TC3_invalidFormat() {
        String result = AnswerController.checkSubmittedAnswer("abc", "1 + 2");
        assertEquals("Answer format is invalid", result);
    }

    @Test
    public void TC4_questionNotExist() {
        String result = AnswerController.checkSubmittedAnswer("3", "10 + 10");
        assertEquals("Question does not exist", result);
    }

    @Test
    public void TC5_anotherCorrectAnswer() {
        String result = AnswerController.checkSubmittedAnswer("12", "3 * 4");
        assertEquals("Correct", result);
    }
}