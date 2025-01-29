# studentManagementSystem

Overview

This is a Spring Boot application for managing student information, including registration, course selection, fee management, and student details.

# Technologies Used
    Java (Spring Boot)
    Spring MVC (Controller layer)
    Spring Data JPA (Repository layer)
    MySQL (Database)
    Thymeleaf (View layer)
    Bootstrap (Styling)
    Features

# Student Registration
    View All Students
    Edit and Delete Student Information
    Search Students by Course
    View Fee Details
    Group Students by Course

# Project Structure

    com.pinnacle.student
    │── controller
    │   ├── StudentController.java
    │── model
    │   ├── Student.java
    │── repository
    │   ├── StudentRepository.java
    │── service
    │   ├── StudentService.java
    │   ├── imp
    │       ├── StudentServiceImp.java
    │── resources
    │   ├── templates
    │   │   ├── homePage.html
    │   │   ├── registrationStudentPage.html
    │   │   ├── allStudentsPage.html
    │   │   ├── editStudentPage.html
    │   │   ├── studentByCourse.html
    │   │   ├── showFeesDetails.html
    │   │   ├── coursesWithStudents.html
    │── application.properties
    │── Student1Application.java

# Setup and Installation

# Prerequisites
    Java 17 or later
    MySQL Database
    Maven
    Eclipse/VS Code (IDE of choice)

# Steps to Run the Project

# Clone the Repository
    git clone <repository-url>
    cd student-management-system

# Build and Run the Application
    mvn clean install
    mvn spring-boot:run

# Access the Application
    Open http://localhost:8888/student/ in the browser.

# Contribution

    Feel free to fork and contribute to this project. Make sure to create a new branch and raise a pull request.

# License

    This project is licensed under the MIT License.