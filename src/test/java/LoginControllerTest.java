package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    
    @Test
    void testUserLength_valid() {
        assertTrue(LoginController.checkUserLength("student1")); // 8 chars
    }

    @Test
    void testUserLength_tooShort() {
        assertFalse(LoginController.checkUserLength("abc")); // 3 chars
    }

    @Test
    void testUserLength_tooLong() {
        assertFalse(LoginController.checkUserLength("thisistoolong")); // 13 chars
    }

   
    @Test
    void testBadChars_hasSpace() {
        assertTrue(LoginController.checkUserForBadCharacters("user name"));
    }

    @Test
    void testBadChars_clean() {
        assertFalse(LoginController.checkUserForBadCharacters("student1"));
    }

    
    @Test
    void testPwdLength_valid() {
        assertTrue(LoginController.checkPwdLength("Pass123!"));
    }

    @Test
    void testPwdLength_tooShort() {
        assertFalse(LoginController.checkPwdLength("P1!"));
    }

    
    @Test
    void testPwdValid_allRulesMet() {
        assertTrue(LoginController.checkPwdForValidCharacters("Pass123!"));
    }

    @Test
    void testPwdValid_noUpperCase() {
        assertFalse(LoginController.checkPwdForValidCharacters("pass123!"));
    }

    @Test
    void testPwdValid_noNumber() {
        assertFalse(LoginController.checkPwdForValidCharacters("Password!"));
    }

    @Test
    void testPwdValid_noSpecialChar() {
        assertFalse(LoginController.checkPwdForValidCharacters("Password1"));
    }

    
    @Test
    void testLogin_successful() {
        assertEquals("Login Successful", LoginController.requestLogin("student1", "Pass123!"));
    }

    @Test
    void testLogin_wrongPassword() {
        assertEquals("Password invalid", LoginController.requestLogin("student1", "Wrong123!"));
    }

    @Test
    void testLogin_userNotFound() {
        assertEquals("User id does not exist", LoginController.requestLogin("unknown1", "Pass123!"));
    }
}