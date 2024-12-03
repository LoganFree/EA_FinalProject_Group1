---
## 0. Guide for Setup 

Database Implementation-

WAMP Server's phpMyAdmin

---
User Account (see "application.properties" file)-

username: admin

password: admin

spring.jpa.hibernate.ddl-auto = create

---
Database Structure-

name: budgetbuddy

tables: see "SQL_Imports" folder on project to download tables.

---
## 1. Title

Budget Buddy - Enterprise Budgeting Application

---

## 2. Introduction

Keeping track of your budget can be a difficult task. Budget Buddy is a web-app designed to help users manage their finances by providing a comprehensive budgeting tool. The application allows users to input their financial information, adjust entries, calculate budgets, and view their financial status through an intuitive dashboard.

- Goal 1: Provide a user-friendly interface for entering and managing financial information.
- Goal 2: Accurately calculate budgets based on user inputs.
- Goal 3: Display budget information in an easy-to-understand dashboard.

- Objective 1: Gernate ideas and work on the concept of the service.   
- Objective 2: Develop a User Entry Form Service for inputting user information.
- Objective 3: Create an Adjust Entries Form Service for modifying financial data.
- Objective 4: Implement a Budget Calculation Service for processing budget data.
- Objective 5: Establish a Database for storing user and budget data.
- Objective 6: Design a Dashboard Service for visualizing budget information.
---
## 3. Screenshots

![Screenshot 2024-12-02 185705](https://github.com/user-attachments/assets/828b5acf-8a03-44a4-ac4b-50475e3067b2)

![Screenshot 2024-12-02 185728](https://github.com/user-attachments/assets/bde9e8bc-68ca-4898-ae93-dc1827bd75f4)

![Screenshot 2024-12-02 185756](https://github.com/user-attachments/assets/f6840a80-6129-4291-a864-0d21736ef43a)

![Screenshot 2024-12-02 185820](https://github.com/user-attachments/assets/be2dd393-6dd0-4f02-bd92-805a340977cf)


---

## 4. Functional Requirements 
1. User Entry Form Service - I want to add grocery expense to my budget.
   - Given: A user has accessed the User Entry Form to add expense to their budget.
   - When: The user inputs the amount spent, the category for the expense, a description, and other optional information.
   - Then: The system validates the inputs, insuring the amount is greater than 0 and that both the category and description are valid, then stores the information in the database. If the validation fails, an error message is displayed, prompting the user to correct the input.
  
2. User Edit Entry Form Service - I want to delte expense information in my budget.
   - Given: A user needs to delete their expense information, such as food, grocery,etc.
   - When: The user delete personal expenese, bills in the manage page.
   - Then: The system validates and ensure the expense and bill that user wants to delete is existing. If the validation fails, an error message is displayed, prompting the user to try again.

3. Budget Calculation Service - I want to see how much my expenses are.
   - Given: A user has entered or adjusted their financial information.
   - When: The user requests a budget calculation.
   - Then: The system processes the inputs and calculates the budget information accurately.
  
4. User Budget Inspectation - As a college student, I want to be able to view weekly statement.
   - Given: A user needs to view their budget for each week.
   - When: The user navigates to View Statement section and select Weekly View option.
   - Then: The system displays the user's budget, broken down by each week.

---
## 5. Class Diagram

![UML](https://github.com/user-attachments/assets/8408b834-4271-44d1-b86a-2b182789fa0d)
([Link to LucidChart](https://lucid.app/lucidchart/42ded405-90bc-41cd-a671-8c033ac885cf/edit?viewport_loc=-678%2C37%2C4517%2C2193%2C0_0&invitationId=inv_6836303a-8d2c-4b1d-a5d8-89373cd3cad6))
---
## 6. JSON Schema
```json
{
    "$schema": "http://json-schema.org/draft-06/schema#",
    "$ref": "#/definitions/BudgetBuddy",
    "definitions": {
        "BudgetBuddy": {
            "type": "object",
            "additionalProperties": false,
            "properties": {
                "Earning": {
                    "$ref": "#/definitions/Earning"
                },
                "Bill": {
                    "$ref": "#/definitions/Bill"
                },
                "Expense": {
                    "$ref": "#/definitions/Expense"
                }
            },
            "required": [
                "Bill",
                "Earning",
                "Expense"
            ],
            "title": "BudgetBuddy"
        },
        "Bill": {
            "type": "object",
            "additionalProperties": false,
            "properties": {
                "billAmount": {
                    "type": "integer"
                },
                "billDueDate": {
                    "type": "string"
                },
                "billDescription": {
                    "type": "string"
                }
            },
            "required": [
                "billAmount",
                "billDescription",
                "billDueDate"
            ],
            "title": "Bill"
        },
        "Earning": {
            "type": "object",
            "additionalProperties": false,
            "properties": {
                "earnIsSalary": {
                    "type": "boolean"
                },
                "earnAmount": {
                    "type": "integer"
                }
            },
            "required": [
                "earnAmount",
                "earnIsSalary"
            ],
            "title": "Earning"
        },
        "Expense": {
            "type": "object",
            "additionalProperties": false,
            "properties": {
                "expAmount": {
                    "type": "integer"
                },
                "expCategory": {
                    "type": "string"
                },
                "expDescription": {
                    "type": "string"
                }
            },
            "required": [
                "expAmount",
                "expCategory",
                "expDescription"
            ],
            "title": "Expense"
        }
    }
}


```
---
## 7. Scrum Roles

Scrum Master: Logan Freeman

Scrum Development Team
   - Testing/Debugging: Alex Brooksbank 
   - Development: Anthony Johnson, Loc Nguyen
   - Design: Mickey Ofosu-Frimpong, Logan Freeman

---
## 8. Github.com Project Link

https://github.com/LoganFree/EA_FinalProject_Group1

---
## 9. Scrum Board

https://github.com/users/LoganFree/projects/2

- Milestone 1: Come up With a Design for the App ([Link to Milestone 1 Project](https://github.com/users/LoganFree/projects/2/views/1))
- Milestone 2: Complete User Entry Form Service 
- Milestone 3: Develop Adjust Entries Form Service 
- Milestone 4: Implement Budget Calculation Service
- Milestone 5: Establish a Database 
- Milestone 6: Design Dashboard Service 
- Milestone 7: Conduct testing and quality assurance 
- Milestone 8: Launch Budget Buddy 

---
