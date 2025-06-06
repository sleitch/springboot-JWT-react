## Brief Desc

This project serves as a foundation for building secure applications with user authentication and profile management. You can extend its functionality by adding more features as needed.

spring-boot-react-app

## Backend

The backend is built using Spring Boot and provides the following features:

- **User Authentication**: Users can sign up and log in using JWT for secure authentication.
- **Profile Management**: Users can view and update their profile information.
- **Security Configuration**: Configured to use Spring Security with JWT.


## Frontend

The frontend is built using React and provides a user-friendly interface for authentication and profile management.

## Setup Instructions


2. **Backend Setup**:
   - Update the `application.properties` file with your database configuration.
   - Build the backend using Maven:
     ```
     mvn clean install
     ```
   - Run the Spring Boot application:
     ```
     mvn spring-boot:run
     ```
    or `UserOneApp.java`

3. **Frontend Setup**:
   - Navigate to the `frontend` directory.
   - Install dependencies:
     ```
     npm install
     ```
   - Start the React application:
     ```
     npm start
     ```

