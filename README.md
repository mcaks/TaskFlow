# TaskFlow 📋

TaskFlow is a personal task management app designed to help users organize 🗂️, prioritize ⭐, and track 🕒 their daily tasks and goals with ease. This app offers an intuitive way to create ✍️, update 🔄, and manage tasks through a clean, streamlined interface. ✨

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Technology Stack](#technology-stack)
4. [Setup and Usage](#setup-and-usage)
   - [Installation Instructions](#installation-instructions)
   - [User Guide](#user-guide)
5. [Project Structure](#project-structure)
7. [Contribution Guidelines](#contribution-guidelines)
9. [Glossary](#Glossary)
10. [Visual Documentation Section](#visual-documentation-section)


## Features ✨

- Task Management 📝 - Easily add tasks with customizable titles, due dates, and priorities.
- Progress Tracking 📈 - Track tasks across statuses: To-Do, Doing, and Done.
- Status Updates 📊 - Quickly update task status to reflect real-time progress.

- Task Editing 🔄 - Modify task details anytime to stay flexible.
- Task Deletion 🗑️ - Remove completed or irrelevant tasks with ease.

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
## Setup and Usage 🚀
### Installation Instructions 

For a step-by-step guide on installing and setting up TaskFlow on your local machine, follow our Notion documentation in the link below:

👉 [TaskFlow Installation Instructions](https://cloudy-tern-837.notion.site/TaskFlow-Installation-Instructions-1497a2b34d76401bbe200d9a805740f7?pvs=4) 👈

### User Guide

👉[Watch the User Guide Video](https://www.loom.com/share/521e42802e7946199abe5e180d242e00?sid=b071e22e-8ec9-469d-b4f0-66fdd71cd202)👈
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

- **Task:** A single unit of work that needs to be completed. It includes a title, due date, due time and status.
- **Task Name:** The title or label of a task, providing a brief summary of the task’s purpose or objective.
- **Date:** The specific day on which the task is due to be completed.
- **Time:** The exact hour and minute by which the task should be completed.
- **Status:** The current state of a task, which can be one of the following:
  - **To-Do:** The task has been created but not yet started.
  - **Doing:** The task is currently in progress.
  - **Done:** The task has been completed.
- **Filter by Status:** A feature allowing users to filter tasks based on their current status (e.g., To-Do, Doing, Done) to better manage and view task progress.
- **Mark Complete:** An action that updates a task’s status to "Done," indicating it has been completed.
- **Edit Task:** An option to modify an existing task, allowing updates to task details such as name, date, time, and status.
- **Delete Task:** An option to remove a task permanently from the task list.
- **Task List View:** A display or table showing tasks in a structured layout with columns such as Task Name, Date, Time, Status, and action buttons like Edit, Delete, and Mark Complete.
- **Controller:** A component that handles incoming HTTP requests, processes them, and returns the appropriate response.
- **Service:** A component that contains the business logic of the application, often called by the controller to perform operations on data.
- **Model:** Represents the data structure of the application, defining how data is organized and managed.
- **JSP (JavaServer Pages):** A technology used for developing web pages that support dynamic content, allowing for the integration of Java code within HTML.
- **Maven:** A build automation tool used primarily for Java projects, managing project dependencies and build processes.

## Visual Documentation Section 🗂️
![image](https://github.com/user-attachments/assets/66075bb3-bf36-477d-93bd-9d73ac9f24b7)
