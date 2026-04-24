package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterControllerTest {

    @Test
    public void TC1_validUser_validPwd_validRetype() {
        String result = RegisterController.registerUser("student99", "Pass123!", "Pass123!");
        assertEquals("Registration Successful", result);
    }

    @Test
    public void TC2_validUser_validPwd_invalidRetype() {
        String result = RegisterController.registerUser("student99", "Pass123!", "Pass124!");
        assertEquals("Retyped password does not match", result);
    }

    @Test
    public void TC3_validUser_validPwd_nullRetype() {
        String result = RegisterController.registerUser("student99", "Pass123!", null);
        assertEquals("Retyped password does not match", result);
    }

    @Test
    public void TC7_validUser_nullPwd_validRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser("student99", null, "Pass123!");
        });
    }

    @Test
    public void TC10_invalidUser_validPwd_validRetype() {
        String result = RegisterController.registerUser("stud", "Pass123!", "Pass123!");
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC19_nullUser_validPwd_validRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, "Pass123!", "Pass123!");
        });
    }

    @Test
    public void TC27_nullUser_nullPwd_nullRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, null, null);
        });
    }
}