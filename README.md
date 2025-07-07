QuizApp - Full Stack Quiz Management Platform

Overview:
QuizApp is a full-stack quiz management system built using Angular for the frontend and Spring Boot for the backend. It supports Google OAuth2 login, role-based access (Player and GameMaster), quiz creation and participation, and result tracking.

Key Technologies:

Frontend:
- Angular
- TypeScript
- Reactive Forms
- Angular Routing
- RxJS

Backend:
- Java 17+
- Spring Boot 3.x
- Spring Security with OAuth2 and JWT
- Spring Data JPA
- MySQL (for local development) / H2 (for deployed backend)
- Maven

API Communication:
- RESTful APIs with JSON

Project Features:

1. Google OAuth2 Login
- Uses Spring Security for secure login with Google.
- JWT used for session management.
- Role-based access implemented: Player and GameMaster.

2. Role-Based User Access
- Player: Can attempt quizzes and view results.
- GameMaster: Can create, update, delete quizzes and view statistics.

3. Quiz Management
- GameMasters can manage quizzes (create, update, delete).
- Players can view available quizzes and attempt them.
- Quizzes have multiple questions with multiple-choice answers.

4. Timer-Based Quizzes
- Each quiz supports a countdown timer.
- Players must submit answers within the time limit.

5. Responsive UI
- Built with Angular Material for modern design.
- Fully responsive on mobile and desktop.
- Form validations and routing with guards implemented.

6. Error Handling
- Frontend handles errors like unauthorized access and validation failures.
- Backend uses custom exception handling with proper status codes and messages.

How to Run the Project Locally:

Backend Setup:
1. Navigate to: FullStackQuizApp/backend/QuizApp-1/
2. Configure the database and OAuth2 client in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/quizdb  
spring.datasource.username=root  
spring.datasource.password=your_password  

spring.security.oauth2.client.registration.google.client-id=your_client_id  
spring.security.oauth2.client.registration.google.client-secret=your_client_secret  

3. Run the backend using:
mvn spring-boot:run  
Runs on: http://localhost:8080

Frontend Setup:
1. Navigate to: FullStackQuizApp/frontend/angular-starter-project-master/
2. Install dependencies:
npm install

3. Run Angular app:
ng serve  
Runs on: http://localhost:4200

Folder Structure:

FullStackQuizApp/
├── backend/
│   └── QuizApp-1/
│       ├── controller/
│       ├── service/
│       ├── repository/
│       ├── dto/
│       ├── entity/
│       ├── config/
│       └── application.properties
├── frontend/
│   └── angular-starter-project-master/
│       ├── app/
│       │   ├── auth/ (signup, login, home)
│       │   ├── module/ (user and admin)
│       │   ├── services/ (quizservice)
│       │   ├── guards/
│       │   └── app-routing.module.ts
│       ├── assets/
│       ├── environments/
│       └── index.html

Deployment:

Find below the GitHub repositories and live deployment links for my completed full-stack projects:

1. QuizApp – Full Stack Quiz Management Platform  
GitHub Repository: https://github.com/khushigupta345/FullStackQuizApp  
Frontend Live URL: https://full-stack-quiz-app-khushis-projects-d7056416.vercel.app/  
Backend Live URL: https://fullstackquizapp.onrender.com/

Important Notes for QuizApp:

- The backend uses an H2 in-memory database in deployment and MySQL for local development.
- Since H2 is in-memory, all data resets after the server restarts.

Admin Access:
You can log in using the email quizappowner2025kg@gmail.com to access admin functionality.  
No signup is required for admin.

User Access:
- A user must sign up using their email before logging in.
- User login will only work if the email used for login matches the one used during signup.
- Login will fail if attempted without prior signup.

