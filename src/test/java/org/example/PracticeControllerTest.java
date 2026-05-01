package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PracticeControllerTest {
    // TC1: valid grade level and operation
    @Test
    public void testValidPracticeSession() {
        String result = PracticeController.createPracticeSession("student1", "Grade 3", "addition");
        assertEquals("Practice session created", result);
    }

    // TC2: invalid operation (modulus)
    @Test
    public void testInvalidOperation() {
        String result = PracticeController.createPracticeSession("student1", "Grade 3", "modulus");
        assertEquals("Operation type invalid", result);
    }

    // TC3: invalid grade level
    @Test
    public void testInvalidGradeLevel() {
        String result = PracticeController.createPracticeSession("student1", "Grade 7", "addition");
        assertEquals("Grade level invalid", result);
    }

    // TC4: null operation
    @Test
    public void testNullOperation() {
        assertThrows(NullPointerException.class, () -> {
            PracticeController.createPracticeSession("student1", "Grade 3", null);
        });
    }

    // TC5: null grade level
    @Test
    public void testNullGradeLevel() {
        assertThrows(NullPointerException.class, () -> {
            PracticeController.createPracticeSession("student1", null, "addition");
        });
    }

    // TC6: user does not exist    
    @Test
    public void testInvalidUser() {
        String result = PracticeController.createPracticeSession("student5", "Grade 3", "addition");
        assertEquals("User does not exist", result);
    }

    // TC7 which inside the TC1 : load first question valid
    @Test
    public void testLoadFirstQuestionValid() {
        String result = PracticeController.loadFirstQuestion("Grade 3", "addition");
        assertEquals("First question loaded", result);
    }

    // TC8 which inside the TC3: load first question invalid grade
    @Test
    public void testLoadFirstQuestionInvalidGrade() {
        String result = PracticeController.loadFirstQuestion("Grade 7", "addition");
        assertEquals("Grade level invalid", result);
    }

    // TC9: Which inside TC2  load first question invalid operation
    @Test
    public void testLoadFirstQuestionInvalidOperation() {
        String result = PracticeController.loadFirstQuestion("Grade 3", "modulus");
        assertEquals("Operation type invalid", result);
    }


}
