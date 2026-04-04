package org.example;

public class PracticeController {
    public static String[] existingIDs = {"student1", "student2", "student3"};
    public static String[] validOperations = {"addition", "subtraction", "multiplication", "division", "mixed"};
    public static String[] validGradeLevels = {"Kindergarten", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5"};

    public static boolean checkGradeLevelValid(String argGradeLevel){
        for(String grade : validGradeLevels) {
            if(argGradeLevel.equals(grade)){
                return true;
            }
        }
        return false;
    }
    public static boolean checkUserExisting(String argUserID){
        for(String id : existingIDs) {
            if(argUserID.equals(id)){
                return true;
            }
        }
        return false;
    }
    public static boolean checkOperationValid(String argOperationType){
        for(String ops : validOperations) {
            if(argOperationType.equals(ops)){
                return true;
            }
        }
        return false;
    }

    public static String startPractice(String argUserID){
        if(!checkUserExisting(argUserID)){
            return "User does not exist";
        }
        return "Practice started";
    }

    public static String createPracticeSession(String argUserID, String argGradeLevel, String argOperationType){
        if(!checkUserExisting(argUserID)){
            return "User does not exist";
        }
        if(!checkGradeLevelValid(argGradeLevel)){
            return "Grade level invalid";
        }
        if(!checkOperationValid(argOperationType)){
            return "Operation type invalid";
        }

        return "Practice session created";
    }

    public static String loadFirstQuestion(String argGradeLevel, String argOperationType){
        if(!checkGradeLevelValid(argGradeLevel)){
            return "Grade level invalid";
        }
        if(!checkOperationValid(argOperationType)){
            return "Operation type invalid";
        }

        return "First question loaded";
    }
}
