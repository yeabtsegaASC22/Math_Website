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
    public void TC4_validUser_invalidPwd_validRetype() {
        String result = RegisterController.registerUser("student99", "password", "password");
        assertEquals("Password does not satisfy rules for valid password", result);
    }

    @Test
    public void TC5_validUser_invalidPwd_invalidRetype() {
        String result = RegisterController.registerUser("student99", "password", "password1");
        assertEquals("Password does not satisfy rules for valid password", result);
    }

    @Test
    public void TC6_validUser_invalidPwd_nullRetype() {
        String result = RegisterController.registerUser("student99", "password", null);
        assertEquals("Password does not satisfy rules for valid password", result);
    }

    @Test
    public void TC7_validUser_nullPwd_validRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser("student99", null, "Pass123!");
        });
    }

    @Test
    public void TC8_validUser_nullPwd_invalidRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser("student99", null, "password1");
        });
    }

    @Test
    public void TC9_validUser_nullPwd_nullRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser("student99", null, null);
        });
    }

    @Test
    public void TC10_invalidUser_validPwd_validRetype() {
        String result = RegisterController.registerUser("stud", "Pass123!", "Pass123!");
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC11_invalidUser_validPwd_invalidRetype() {
        String result = RegisterController.registerUser("stud", "Pass123!", "Pass124!");
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC12_invalidUser_validPwd_nullRetype() {
        String result = RegisterController.registerUser("stud", "Pass123!", null);
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC13_invalidUser_invalidPwd_validRetype() {
        String result = RegisterController.registerUser("stud", "password", "password");
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC14_invalidUser_invalidPwd_invalidRetype() {
        String result = RegisterController.registerUser("stud", "password", "password1");
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC15_invalidUser_invalidPwd_nullRetype() {
        String result = RegisterController.registerUser("stud", "password", null);
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC16_invalidUser_nullPwd_validRetype() {
        String result = RegisterController.registerUser("stud", null, "Pass123!");
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC17_invalidUser_nullPwd_invalidRetype() {
        String result = RegisterController.registerUser("stud", null, "password1");
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC18_invalidUser_nullPwd_nullRetype() {
        String result = RegisterController.registerUser("stud", null, null);
        assertEquals("User id does not satisfy length requirement", result);
    }

    @Test
    public void TC19_nullUser_validPwd_validRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, "Pass123!", "Pass123!");
        });
    }

    @Test
    public void TC20_nullUser_validPwd_invalidRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, "Pass123!", "password1");
        });
    }

    @Test
    public void TC21_nullUser_validPwd_nullRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, "Pass123!", null);
        });
    }

    @Test
    public void TC22_nullUser_invalidPwd_validRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, "password", "Pass123!");
        });
    }

    @Test
    public void TC23_nullUser_invalidPwd_invalidRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, "password", "password1");
        });
    }

    @Test
    public void TC24_nullUser_invalidPwd_nullRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, "password", null);
        });
    }

    @Test
    public void TC25_nullUser_nullPwd_validRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, null, "Pass123!");
        });
    }

    @Test
    public void TC26_nullUser_nullPwd_invalidRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, null, "password1");
        });
    }

    @Test
    public void TC27_nullUser_nullPwd_nullRetype() {
        assertThrows(NullPointerException.class, () -> {
            RegisterController.registerUser(null, null, null);
        });
    }
}