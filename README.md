# Task Tracker Application

This is a simple Task Tracker application that allows users to manage tasks. The application provides a RESTful API for performing CRUD (Create, Read, Update, Delete) operations on tasks. It uses Spring Boot, Spring Data JPA, and PostgreSQL for data storage.

## Features

- View a list of all tasks.
- Retrieve task details by task ID.
- Add a new task.
- Update task details (title, description, due date).
- Delete a task.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven (or Gradle)
- RESTful API

## Getting Started

### Prerequisites

- Java JDK (version X.X.X)
- Maven (or Gradle) (version X.X.X)
- PostgreSQL database (version X.X.X)

### Installation and Setup


Create a table named 'Tasks' in Postgresql with the following attributes:
  * id (String): Unique identifier for the task.
  * title (String): Title of the task.
  * description (String): Description of the task.
  * dueDate (Date): Due date of the task.
Here, the id is auto-generated

Create Table sql:
  - CREATE TABLE tasks (
        id SERIAL PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        dueDate DATE
      );

Steps to auto generate id:<br>
* CREATE SEQUENCE tasks_id_seq START 1;
* CREATE OR REPLACE FUNCTION generate_task_id()
      RETURNS TRIGGER AS $$
      BEGIN
        NEW.id := CONCAT('TASK', LPAD(nextval('tasks_id_seq')::TEXT, 5, '0'));
        RETURN NEW;
      END;
      $$ LANGUAGE plpgsql;
* CREATE TRIGGER set_task_id
      BEFORE INSERT ON tasks
      FOR EACH ROW
      EXECUTE FUNCTION generate_task_id();

Screenshot of table:
(contains some dummy data)
![Screenshot 2023-08-08 213000](https://github.com/aryapanja/Task-Tracker/assets/83545460/8018e2b2-bb3f-498c-8573-b223f57dedec)

Clone the repository:

   ```bash
   git clone https://github.com/your-username/task-tracker.git
   cd task-tracker
   ```


Build and Run:

  ```bash
   mvn spring-boot:run
   ```

## API Endpoints

  * GET /task-tracker/tasks: Retrieve a list of all tasks.
  * GET /task-tracker/tasks/{id}: Retrieve details of a task by ID.
  * POST /task-tracker/tasks: Add a new task.
  * PUT /task-tracker/tasks/{id}: Update task details (title, description, due date).
  * DELETE /task-tracker/tasks/{id}: Delete a task.

## Error Handling
  * The API returns appropriate HTTP status codes and response messages for various scenarios, including task not found, validation errors, and server errors.
  

## Screenshots of the working:

* View all tasks
![Screenshot 2023-08-08 214556](https://github.com/aryapanja/Task-Tracker/assets/83545460/896b17c5-fabb-4ef0-b140-19f717aee5fd)


* View task by id
![image](https://github.com/aryapanja/Task-Tracker/assets/83545460/1e56a332-e924-453d-8611-db3b64ef0d3c)


* Add a new task
![Screenshot 2023-08-08 215057](https://github.com/aryapanja/Task-Tracker/assets/83545460/8b3828be-09ac-4bf4-b2a5-21f78ecf1a1e)


* Delete task by id
![image](https://github.com/aryapanja/Task-Tracker/assets/83545460/922c9d92-1b6f-4148-8b74-becbde51d3a9)

* Update task by id
![image](https://github.com/aryapanja/Task-Tracker/assets/83545460/3e1b928a-fcc9-462b-baa7-9194f5ff6096)


