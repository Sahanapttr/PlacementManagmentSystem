# Placement Management and Recruitment Tracking System

## 1. Project Overview

The Placement Management and Recruitment Tracking System is a Java console-based application developed using Java and MySQL. It provides a menu-driven interface to manage student records, company details, job vacancies, and job applications.

The application uses JDBC (Java Database Connectivity) to connect Java with the MySQL database.

## 2. Project Objectives

- Maintain student registration records.
- Store and display company information.
- Register and display job vacancies.
- Allow students to apply for available jobs.
- Track job application statuses throughout the recruitment process.
- Maintain data consistently using a relational database.

## 3. Technologies Used

- **Programming Language:** Java (JDK 17)
- **Database:** MySQL
- **Database Connectivity:** JDBC
- **IDE:** Visual Studio Code
- **JDBC Driver:** MySQL Connector/J

## 4. Main Features

### Student Management

- Register a new student.
- View all registered students.
- Search for a student by ID.
- Update student details.
- Delete a student record.

### Company Management

- Register a company.
- View all registered companies.

### Job Management

- Register a job vacancy.
- View available job records with company details.
- Display required qualifications, skills, salary ranges, and job status.

### Application Management

- Submit a job application for a student.
- View all job applications.
- Update the status of an application.
- Prevent duplicate applications by the same student for the same job.
- Display meaningful error messages for invalid or duplicate applications.

Supported application statuses:

- `APPLIED`
- `SHORTLISTED`
- `INTERVIEW`
- `SELECTED`
- `REJECTED`

## 5. Project Structure

```text
PlacementManagmentSystem/
├── .vscode/
│   └── settings.json
├── bin/                         (compiled classes; generated)
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── src/
│   ├── dao/
│   │   ├── StudentDAO.java
│   │   ├── CompanyDAO.java
│   │   ├── JobDAO.java
│   │   └── ApplicationDAO.java
│   ├── model/
│   │   ├── Student.java
│   │   ├── Company.java
│   │   ├── Job.java
│   │   └── Application.java
│   ├── service/
│   │   ├── StudentService.java
│   │   ├── CompanyService.java
│   │   ├── JobService.java
│   │   └── ApplicationService.java
│   ├── util/
│   │   └── DBConnection.java
│   └── Main.java
├── database.sql
└── README.md
```

**Note:** The `bin/` directory contains compiled Java classes. It is generated when the project is compiled and does not need to be maintained manually.

## 6. Database Setup

### Prerequisites

1. Install JDK 17 or a compatible newer JDK.
2. Install MySQL Community Server.
3. Ensure MySQL Server is running.
4. Ensure MySQL Connector/J is present in the project's `lib/` directory.

### Create the Database and Tables

1. Open a MySQL client.
2. Execute the SQL statements in `database.sql`.
3. Verify that the `placement_management` database contains these tables:
   - `students`
   - `companies`
   - `jobs`
   - `applications`

The SQL script uses `CREATE TABLE IF NOT EXISTS`. It creates missing tables but does not modify existing table definitions. If the database schema changes in the future, appropriate migration statements may be required.

## 7. Database Configuration

The database connection is configured in `src/util/DBConnection.java`.

- **Database URL:** `jdbc:mysql://localhost:3306/placement_management`
- **Username:** Read from the `DB_USER` environment variable. The application defaults to `root` if this variable is not set.
- **Password:** Read from the `DB_PASSWORD` environment variable.

For PowerShell, set the environment variables in the same terminal session before compiling and running the application:

```powershell
$env:DB_USER = "root"
$env:DB_PASSWORD = Read-Host "Enter your MySQL password"
```

Enter your own MySQL password when prompted. Do not share it or commit it to a public repository.

## 8. Compile and Run the Application

Open PowerShell in the project root directory:

`PlacementManagmentSystem`

### Step 1: Set the database environment variables

```powershell
$env:DB_USER = "root"
$env:DB_PASSWORD = Read-Host "Enter your MySQL password"
```

### Step 2: Compile the Java source files

```powershell
New-Item -ItemType Directory -Force bin
$sources = (Get-ChildItem .\src -Recurse -Filter *.java).FullName
javac -cp "lib\mysql-connector-j-26.7.0.jar" -d bin $sources
```

### Step 3: Run the application

```powershell
java -cp "bin;lib\mysql-connector-j-26.7.0.jar" Main
```

The main menu will appear in the console. Select an option by entering its corresponding number.

**Important:** Run these commands from the project root directory. Set the environment variables again whenever you open a new PowerShell session.

## 9. Validation and Database Constraints

- Student email addresses must be unique.
- Job records reference existing companies through a foreign key.
- Applications reference existing students and jobs.
- A student cannot apply for the same job more than once.
- Application statuses are restricted to the supported values.
- Input validation is implemented for student, company, job, and application operations as applicable.

## 10. Testing Performed

The following operations were tested through the console application:

- Viewing registered students.
- Searching for and updating student details.
- Viewing registered companies.
- Viewing job vacancies and company details.
- Viewing job applications.
- Updating an application status and verifying that the change persisted.
- Handling a duplicate job application without crashing the application.

## 11. Future Enhancements

- Add authentication for students and placement administrators.
- Develop a graphical user interface.
- Add job search and filtering functionality.
- Generate placement reports and statistics.
- Improve database credential management.
- Add role-based access control.
- Introduce additional reporting and administrative features.

## 12. Conclusion

The Placement Management and Recruitment Tracking System demonstrates the use of Java, object-oriented programming, JDBC, and MySQL to implement a basic recruitment management workflow. It organizes student, company, job, and application information through a console-based interface and a relational database.

 $env:DB_USER = "root"
>> $env:DB_PASSWORD = Read-Host "Enter your MySQL password"
Enter your MySQL password: 
java -cp "bin;lib\mysql-connector-j-26.7.0.jar" Main