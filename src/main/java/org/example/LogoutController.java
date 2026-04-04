package org.example;

public class LogoutController {
    public static String[] existingIDs = {"student1", "student2", "student3"};
    public static boolean[] logoutStatus = {false, false, false};

    public static boolean checkUserExisting(String argUserID){
        for(String id : existingIDs) {
            if(argUserID.equals(id)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkUserLoggedOut(String argUserID){
        for(int i = 0; i < existingIDs.length; i++){
            if(existingIDs[i].equals(argUserID)){
                return logoutStatus[i];
            }
        }
        return false;
    }

    public static void logoutUser(String argUserID){
        for(int i = 0; i < existingIDs.length; i++){
            if(existingIDs[i].equals(argUserID)){
                logoutStatus[i] = true;
            }
        }
    }


    public static String requestLogout(String argUserID){
        if(!checkUserExisting(argUserID)){
            return "User does not exist, Logout error";
        }
        logoutUser(argUserID);
        return "Logout successful";
    }

    public static String confirmLogout(String argUserID){
        if(!checkUserExisting(argUserID)){
            return "User does not exist";
        }
        if(checkUserLoggedOut(argUserID)){
            return "User is logged out";
        }
        return "User has not logged out yet";
    }


}
