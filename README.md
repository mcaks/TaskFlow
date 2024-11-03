# TaskFlow 📋

TaskFlow is a personal task management app designed to help users organize 🗂️, prioritize ⭐, and track 🕒 their daily tasks and goals with ease. This app offers an intuitive way to create ✍️, update 🔄, and manage tasks through a clean, streamlined interface. ✨

## Table of Contents

1. [Overview](#overview)
2. [Features](#Features)
3. [Technology Stack](#technology-stack)
4. [Setup and Usage](#setup-and-usage)
   - [Local Setup](#local-setup)
   - [User Guide](#user-guide)
5. [Project Structure](#project-structure)
6. [Installation Instructions](#installation-instructions)
7. [Contribution Guidelines](#contribution-guidelines)
9. [Glossary](#Glossary)


## Features ✨

### Basic Task Management:
- Add tasks with titles, due dates, and statuses.
- Edit task details as needed.
- Delete tasks when no longer relevant.

### Task Status Tracking:
- Track tasks across three main statuses: To-Do, Doing, and Done.
- Easily change statuses to reflect real-time progress.

## Technology Stack 🛠️

- **Backend:** Java Spring Boot
- **Frontend:** JSP, HTML, CSS
- **Database:** MySQL 
- **Build Tool:** Maven

## Project Structure 📂

The project is organized as follows:

```
src/main/java: Contains the Java source files.
  controller: Handles HTTP requests and routes them to services.
  service: Contains business logic.
  model: Defines data structures (e.g., Task).
src/main/resources: Contains resources like configuration files and templates.
  templates: JSP files for the frontend views.
  static: Stores CSS, JavaScript, and images.
target: Compiled application files.
pom.xml: Maven file for dependencies.
```

## Installation Instructions 🚀

For a step-by-step guide on installing and setting up TaskFlow on your local machine, follow the link below:

👉 [TaskFlow Installation Instructions](https://cloudy-tern-837.notion.site/TaskFlow-Installation-Instructions-1497a2b34d76401bbe200d9a805740f7?pvs=4)

## Contribution Guidelines 🤝

We welcome contributions to improve TaskFlow! Here’s how you can get started:

1. Fork the Repository and clone it locally.
2. Create a Feature Branch:
   ```bash
   git checkout -b feature/<feature-name>
   ```
3. Make Changes: Follow coding standards and ensure code quality.
4. Commit and Push:
   ```bash
   git add .
   git commit -m "Add feature <feature-name>"
   git push origin feature/<feature-name>
   ```
5. Create a Pull Request: Describe your changes and link relevant issues for review.

   

## Glossary 📖

- **Task:** A single unit of work that needs to be completed. It includes a title, due date, and status.
- **Status:** The current state of a task, which can be one of the following:
  - **To-Do:** The task has been created but not yet started.
  - **Doing:** The task is currently in progress.
  - **Done:** The task has been completed.
- **Controller:** A component that handles incoming HTTP requests, processes them, and returns the appropriate response.
- **Service:** A component that contains the business logic of the application, often called by the controller to perform operations on data.
- **Model:** Represents the data structure of the application, defining how data is organized and managed.
- **JSP (JavaServer Pages):** A technology used for developing web pages that support dynamic content, allowing for the integration of Java code within HTML.
- **Maven:** A build automation tool used primarily for Java projects, managing project dependencies and build processes.
