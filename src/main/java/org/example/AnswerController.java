package org.example;

public class AnswerController {
    public static String sessionID = "session123";
    public static String[] questions = {"1 + 2", "3 * 4", "5 - 6"};
    public static String[] answers = {"3", "12", "-1"};

    public static boolean checkAnswerFormat(String argAnswer){
        try{
            double answer = Double.parseDouble(argAnswer);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static boolean checkSessionExisting(String argSessionID){
        return sessionID.equals(argSessionID);
    }

    public static String checkSubmittedAnswer(String argAnswer, String argQuestion){
        if(!checkAnswerFormat(argAnswer)){
            return "Answer format is invalid";
        }

        for(int i = 0; i < questions.length; i++){
            if(questions[i].equals(argQuestion)){
                if(argAnswer.equals(answers[i])) {
                    return "Correct";
                }
                return "Incorrect";
            }
        }
        return "Question does not exist";
    }

    public static String createSubmission(String argSessionID, String argQuestion, String argAnswer){
        if(!sessionID.equals(argSessionID)){
            return "Session ID does not exist";
        }
        if(!checkAnswerFormat(argAnswer)){
            return "Answer format is invalid";
        }
        return "Submission created";
    }

    public static String loadNextQuestion(String argSessionID){
        if(checkSessionExisting(argSessionID)){
            return "Next question loaded";
        }
        return "Session ID does not exist";
    }
}
