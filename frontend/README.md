# Frontend Documentation for Spring Boot React App

This project is a full-stack application that implements user authentication and profile management using Spring Boot for the backend and React for the frontend. It utilizes JWT (JSON Web Tokens) for secure authentication.

## Features

- User Signup: New users can create an account.
- User Login: Existing users can log in to their accounts.
- Profile Management: Users can view and update their profile information.

## Getting Started

### Prerequisites

- Node.js and npm installed on your machine.
- Java Development Kit (JDK) installed for the backend.
- A running instance of a database (e.g., MySQL, PostgreSQL) for user data storage.

### Installation

1. Clone the repository:

   ```
   git clone <repository-url>
   ```

2. Navigate to the frontend directory:

   ```
   cd spring-boot-react-app/frontend
   ```

3. Install the dependencies:

   ```
   npm install
   ```

### Running the Application

1. Start the backend server:

   Navigate to the backend directory and run:

   ```
   cd ../backend
   mvn spring-boot:run
   ```

2. Start the frontend application:

   Navigate back to the frontend directory and run:

   ```
   cd ../frontend
   npm start
   ```

3. Open your browser and go to `http://localhost:3000` to access the application.


### Tech Stack
- React for the frontend.
- Spring Boot for the backend.
- JWT for authentication.
- Axios for making HTTP requests.

### Contributing

Feel free to submit issues or pull requests for improvements or bug fixes.

### License

This project is licensed under the MIT License.