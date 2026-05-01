package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    @GetMapping("/health")
    public String health() {
        return "Java backend is running";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        System.out.println("==== LOGIN REQUEST FROM FRONTEND ====");
        System.out.println("User ID: " + req.userId);
        System.out.println("=====================================");

        return LoginController.requestLogin(req.userId, req.password);
    }

    @PostMapping("/practice")
    public String practice(@RequestBody PracticeRequest req) {
        System.out.println("==== PRACTICE SESSION FROM FRONTEND ====");
        System.out.println("User: " + req.userId);
        System.out.println("Grade: " + req.grade);
        System.out.println("Operation: " + req.operation);
        System.out.println("========================================");

        return "Practice session created";
    }

    @PostMapping("/answer")
    public String answer(@RequestBody AnswerRequest req) {
        System.out.println("==== ANSWER SUBMITTED FROM FRONTEND ====");
        System.out.println("Question: " + req.question);
        System.out.println("User Answer: " + req.answer);
        System.out.println("Expected Answer: " + req.expectedAnswer);
        System.out.println("========================================");

        try {
            double userAnswer = Double.parseDouble(req.answer);
            double correctAnswer = Double.parseDouble(req.expectedAnswer);
            return userAnswer == correctAnswer ? "Correct" : "Incorrect";
        } catch (Exception e) {
            return "Answer format is invalid";
        }
    }

    @PostMapping("/logout")
    public String logout(@RequestBody UserRequest req) {
        System.out.println("==== LOGOUT REQUEST FROM FRONTEND ====");
        System.out.println("User: " + req.userId);
        System.out.println("======================================");

        return LogoutController.requestLogout(req.userId);
    }
}

class LoginRequest {
    public String userId;
    public String password;
}

class PracticeRequest {
    public String userId;
    public String grade;
    public String operation;
}

class AnswerRequest {
    public String sessionId;
    public String question;
    public String answer;
    public String expectedAnswer;
}

class UserRequest {
    public String userId;
}