package org.example;

public class LoginController {
    public static final int MIN_LENGTH_USER_ID = 8;
    public static final int MAX_LENGTH_USER_ID = 12;
    public static final int MIN_LENGTH_PWD = 8;
    public static final int MAX_LENGTH_PWD = 12;

    public static String[] existingIDs = {"student1", "student2", "student3"};
    public static String[] existingPwds = {"Pass123!", "!456ssaP", "100@Pass"};
    public static char[] badChars = {' ', ',', '/', '\\'};
    //public static String customerAccNum = "700022197231";
    //public static double customerBalance = 300.25;

    public static boolean checkUserLength(String argUserID){
        return MIN_LENGTH_USER_ID <= argUserID.length() && argUserID.length() <= MAX_LENGTH_USER_ID;
    }
    public static boolean checkUserExisting(String argUserID){
        for(String id : existingIDs) {
            if(argUserID.equals(id)){
                return true;
            }
        }
        return false;
    }
    public static boolean checkUserForBadCharacters(String argUserID){
        for(char c : badChars){
            if(argUserID.indexOf(c) >= 0){
                return true;
            }
        }
        for(char c : argUserID.toCharArray()){
            if( Character.isISOControl(c)){
                return true;
            }
        }
        return false;
    }


    public static boolean checkPwdLength(String argPwd){
        return MIN_LENGTH_PWD <= argPwd.length() && argPwd.length() <= MAX_LENGTH_PWD;
    }

    public static boolean checkPwdForValidCharacters(String argPwd) {
        boolean foundUpperLetter = false;
        boolean foundNumber = false;
        boolean foundSpecialChar = false;
        for (char c : argPwd.toCharArray()) {
            if (Character.isISOControl(c) || Character.isSpaceChar(c)) {
                return false;
            } else if (Character.isLetter(c)) {
                if(Character.isUpperCase(c)) {
                    foundUpperLetter = true;
                }
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else {
                foundSpecialChar = true;
            }
        }
        return( foundUpperLetter && foundNumber && foundSpecialChar );
    }

    public static boolean checkPwdMatches(String argUserID, String argPwd){
        for(int i = 0; i < existingIDs.length; i++){
            if(existingIDs[i].equals(argUserID)){
                return argPwd.equals(existingPwds[i]);
            }
        }
        return false;
    }

    public static String requestLogin(String argUserID, String argPwd){
        if( !checkUserLength( argUserID ) ) {
            return "User id does not satisfy length requirement";
        }
        if( checkUserForBadCharacters( argUserID ) ){
            return "User id has space/control/special character in it";
        }
        if( !checkUserExisting( argUserID ) ) {
            return "User id does not exist";
        }

        if( !checkPwdLength( argPwd ) ){
            return "Password does not satisfy length requirement";
        }
        if( !checkPwdForValidCharacters( argPwd ) ){
            return "Password does not satisfy rules for valid password";
        }
        if(!checkPwdMatches(argUserID, argPwd)){
            return "Password invalid";
        }
        return "Login Successful";

    }

    public static String startSession(String argUserID){
        if(!checkUserExisting(argUserID)){
            return "User does not exist, Session has failed to start";
        }
        return "Session started";
    }

    public static String loadDashboard(String argUserID){
        if(!checkUserExisting(argUserID)){
            return "User does not exist, Dashboard has failed to load";
        }
        return "Dashboard loaded";
    }
}

