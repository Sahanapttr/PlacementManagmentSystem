CREATE DATABASE IF NOT EXISTS placement_management;

USE placement_management;

-- 1. Student details
CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    qualification VARCHAR(50) NOT NULL,
    graduation_year INT,
    skills VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Company details
CREATE TABLE IF NOT EXISTS companies (
    company_id INT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(100) NOT NULL,
    industry VARCHAR(100),
    location VARCHAR(100),
    contact_email VARCHAR(100)
);

-- 3. Job vacancies
CREATE TABLE IF NOT EXISTS jobs (
    job_id INT PRIMARY KEY AUTO_INCREMENT,
    company_id INT NOT NULL,
    job_title VARCHAR(100) NOT NULL,
    required_qualification VARCHAR(100),
    required_skills VARCHAR(500),
    min_salary DECIMAL(10,2),
    max_salary DECIMAL(10,2),
    is_active BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_jobs_company
        FOREIGN KEY (company_id)
        REFERENCES companies(company_id)
);

-- 4. Student job applications
CREATE TABLE IF NOT EXISTS applications (
    application_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    job_id INT NOT NULL,
    application_date DATE NOT NULL,
    status ENUM(
        'APPLIED',
        'SHORTLISTED',
        'INTERVIEW',
        'SELECTED',
        'REJECTED'
    ) DEFAULT 'APPLIED',

    CONSTRAINT uq_student_job
        UNIQUE (student_id, job_id),

    CONSTRAINT fk_applications_student
        FOREIGN KEY (student_id)
        REFERENCES students(student_id),

    CONSTRAINT fk_applications_job
        FOREIGN KEY (job_id)
        REFERENCES jobs(job_id)
);

-- Display the tables created
SHOW TABLES;