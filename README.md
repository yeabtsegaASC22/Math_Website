# Math Adventure Website

This project was developed as part of a Software Engineering course.

---

## Group Members
- Yeabtsega Dube
- Tien Phat Dao
- Sirjan Dhakal
- Yujie Duan
- Nabintou Fofana
- Samuel Biju George
 
---
<img width="1912" height="867" alt="image" src="https://github.com/user-attachments/assets/725419a0-235d-4ec9-ba0e-b31c18fcbb7b" />


## Project Description
Math Adventure is a math practice website where students can log in, choose a grade level, practice math questions, receive instant feedback, and view their progress.

The project connects a frontend (HTML, CSS, JavaScript) with a Java Spring Boot backend using REST API calls.

---

## Technologies Used
- HTML, CSS, JavaScript (Frontend)
- Java Spring Boot (Backend)
- REST API (Frontend ↔ Backend communication)

---

## Features
- User login system
- Grade selection (Kindergarten to 5th Grade)
- Practice questions (addition, subtraction, multiplication, division, mixed)
- Instant feedback (correct / incorrect)
- Points system and timer
- Results page
- Progress tracking

---

## How to Run the Project

1. Open the project in IntelliJ
2. Go to:
   src/main/java/org/example/Application.java
3. Click the green **Run** button
4. Open your browser and go to:
   http://localhost:8080/

---

## Backend Test

To verify the backend is running, open:

http://localhost:8080/api/health

Expected result:

Java backend is running

---

## Demo Login

User ID: student1  
Password: Pass123!

---

## API Endpoints

- POST /api/login → User login  
- POST /api/practice → Start practice session  
- POST /api/answer → Submit answer  
- POST /api/logout → Logout user  

The frontend uses JavaScript `fetch()` to communicate with these endpoints.

---

## Important Notes

- The frontend is served directly by the Spring Boot backend  
- User actions (login, grade selection, answering questions) are sent to the backend  
- Backend console logs show data received from the frontend  

---

## Project Structure
```text
math-adventure-website/
│
├── src/
│ └── main/
│ ├── java/org/example/
│ │ ├── Application.java
│ │ ├── ApiController.java
│ │ ├── LoginController.java
│ │ ├── LogoutController.java
│ │ ├── AnswerController.java
│ │ └── PracticeController.java
│ │
│ └── resources/static/
│ ├── index.html
│ ├── style.css
│ └── app.js
│
├── pom.xml
├── README.md
└── .gitignore

---

## Summary

This project demonstrates:
- Full frontend and backend integration  
- REST API communication  
- Real-time interaction between user interface and backend logic

<img width="1914" height="867" alt="image" src="https://github.com/user-attachments/assets/a4f684a5-142b-4dd1-ae5c-d0c3c0ce3bdb" />
<img width="1914" height="870" alt="image" src="https://github.com/user-attachments/assets/bdc81006-3446-4d25-bd3a-591b9c42fca3" />
<img width="1896" height="864" alt="image" src="https://github.com/user-attachments/assets/c9ed4b52-f77f-4f15-80f2-06d6deccbbde" />
<img width="1906" height="869" alt="image" src="https://github.com/user-attachments/assets/29ed9586-c364-48b1-9d25-49bf108a3963" />
<img width="1910" height="868" alt="image" src="https://github.com/user-attachments/assets/598c7f66-0301-425d-8142-825d5173c57b" />




