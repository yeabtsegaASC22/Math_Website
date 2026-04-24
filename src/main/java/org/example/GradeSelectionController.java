package org.example;

public class GradeSelectionController {
    public static String[] validGradeLevels = {"Kindergarten", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5"};

    public static boolean checkGradeLevelValid(String argGradeLevel){
        for(String grade : validGradeLevels) {
            if(argGradeLevel.equals(grade)){
                return true;
            }
        }
        return false;
    }

    public static String chooseGradelevel(String argGradeLevel){
        if(!checkGradeLevelValid(argGradeLevel)){
            return "Grade level invalid";
        }

        return argGradeLevel + " selected";
    }

    public static String getQuestionsByGrade(String argGradeLevel){
        if(!checkGradeLevelValid(argGradeLevel)){
            return "Grade level invalid";
        }

        return argGradeLevel + " level questions retrieved";
    }
}
