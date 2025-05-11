# EduInvestor

EduInvestor is a comprehensive platform designed to manage loans, feedback, and user authentication for educational investments. This project is built using a Spring Boot backend and an Angular frontend.

## Features

### Backend (Spring Boot)
- **Loan Management**: Add, update, delete, and retrieve loans.
- **User Authentication**: Secure login and JWT-based authentication.
- **Feedback System**: Collect and manage user feedback.
- **Swagger API Documentation**: Easily explore and test APIs.
- **Database Integration**: MySQL database with Hibernate for ORM.

### Frontend (Angular)
- **Responsive UI**: User-friendly interface for managing loans and feedback.
- **Dynamic Forms**: Validation and submission of loan applications.
- **Real-time Feedback**: Display feedback and loan status updates.

## Prerequisites

- **Backend**:
  - Java 17+
  - Maven 3.8+
  - MySQL 8.0+
- **Frontend**:
  - Node.js 16+
  - Angular CLI 13+

## Getting Started

### Backend Setup

1. Navigate to the backend directory:
   ```sh
   cd backend/springapp
   ```

2. Configure the database connection in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost/appdb?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=pwd
   ```

3. Build and run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```

### Frontend Setup

1. Navigate to the frontend directory:
   ```sh
   cd frontend/angularapp
   ```

2. Install dependencies:
   ```sh
   npm install
   ```

3. Start the Angular development server:
   ```sh
   ng serve
   ```

4. Access the application at:
   ```
   http://localhost:4200
   ```

## API Endpoints

### Loan Management
- `POST /api/loan`: Add a new loan.
- `GET /api/loan/{loanId}`: Retrieve a loan by ID.
- `GET /api/loan`: Retrieve all loans.
- `PUT /api/loan/{loanId}`: Update a loan by ID.
- `DELETE /api/loan/{loanId}`: Delete a loan by ID.

### Feedback Management
- `POST /api/feedback`: Add feedback.
- `GET /api/feedback`: Retrieve all feedback.

### Authentication
- `POST /api/auth/login`: User login.

## Logging

Logs are stored in the `logs/` directory:
- `app.log`: Current log file.
- Rotated logs: `app.log.<date>.gz`.

## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Submit a pull request.

## License

This project is licensed under the MIT License.


