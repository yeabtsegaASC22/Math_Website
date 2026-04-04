package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LogoutControllerTest {

  /*** because the session Id is created automatically after the user login successfully, if we have a valid user Id
   we will have a session Id , all these test case are use the UserId as input to test
   **/
    // TC1 valid user logout
    @Test
    public void TC1_validLogout() {
        String result = LogoutController.requestLogout("student1");
        assertEquals("Logout successful", result);
    }

    // confirm logout status
    @Test
    public void TC1_confirmLogout() {
        LogoutController.requestLogout("student1");
        String result = LogoutController.confirmLogout("student1");
        assertEquals("User is logged out", result);
    }

    // TC2 empty string
    @Test
    public void TC2_emptyUserID() {
        String result = LogoutController.requestLogout("");
        assertEquals("User does not exist, Logout error", result);
    }

    // TC3 null userID
    @Test
    public void TC3_nullUserID() {
        assertThrows(NullPointerException.class, () -> {
            LogoutController.requestLogout(null);
        });
    }

    // extra test: user not logged out yet
    @Test
    public void TC4_notLoggedOutYet() {
        String result = LogoutController.confirmLogout("student2");
        assertEquals("User has not logged out yet", result);
    }
}