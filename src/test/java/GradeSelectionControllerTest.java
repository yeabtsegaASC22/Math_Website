package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeSelectionControllerTest {
    
    
    @Test
    public void TC1_validGrade3() {
        String result = GradeSelectionController.chooseGradelevel("Grade 3");
        assertEquals("Grade 3 selected", result);
        
        
        String questionsResult = GradeSelectionController.getQuestionsByGrade("Grade 3");
        assertEquals("Grade 3 level questions retrieved", questionsResult);
    }
    
    
    @Test
    public void TC2_invalidGrade7() {
        String result = GradeSelectionController.chooseGradelevel("Grade 7");
        assertEquals("Grade level invalid", result);
        
        String questionsResult = GradeSelectionController.getQuestionsByGrade("Grade 7");
        assertEquals("Grade level invalid", questionsResult);
    }
    
    
    @Test
    public void TC3_nullGradeLevel() {
        assertThrows(NullPointerException.class, () -> {
            GradeSelectionController.chooseGradelevel(null);
        });
        
        assertThrows(NullPointerException.class, () -> {
            GradeSelectionController.getQuestionsByGrade(null);
        });
    }
}