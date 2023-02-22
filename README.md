# CREDIT APPLICATION SYSTEM

## Project Description:

- Writing a Restful application using Spring Boot framework for a loan application system, which receives loan application requests and returns the loan result to the customer according to the relevant criteria, and optionally writing its frontend.

## Backend:

- Customer information includes identity number, name-surname, monthly income, phone information, date of birth, and collateral (optional). By assuming that the credit score service, which was previously written, is accessed with the identity number, the credit score of the person concerned is taken and the loan result is shown to the user according to the following rules. (There can be two options: Approval or Rejection.)

## Frontend (optional):

- Identity number, name-surname, monthly income, phone information, date of birth, and collateral (optional) information are collected with a form, and the loan result and credit limit are shown to the user. JavaScript, HTML, and CSS are sufficient in the simplest way, and a front-end framework can be used optionally. The interface design and experience are left to you.

## Rules:

- New users can be defined in the system, existing customers can be updated or deleted. Users are rejected if their credit score is below 500. (Loan result: Rejection)

- If the credit score is between 500 and 1000, and the monthly income is less than 5000 TL, the user's loan application is approved and a limit of 10,000 TL is assigned to the user. (Loan result: Approval) If the user has provided collateral, an amount up to 10% of the collateral value is added to the credit limit.

- If the credit score is between 500 and 1000, and the monthly income is between 5000 TL and 10,000 TL, the user's loan application is approved and a limit of 20,000 TL is assigned to the user. (Loan result: Approval) If the user has provided collateral, an amount up to 20% of the collateral value is added to the credit limit.

- If the credit score is between 500 and 1000, and the monthly income is above 10,000 TL, the user's loan application is approved and a limit equal to MONTHLY INCOME * CREDIT LIMIT MULTIPLIER/2 is assigned to the user. (Loan result: Approval) If the user has provided collateral, an amount up to 25% of the collateral value is added to the credit limit.

- If the credit score is equal to or above 1000, a limit equal to MONTHLY INCOME * CREDIT LIMIT MULTIPLIER is assigned to the user. (Loan result: Approval) If the user has provided collateral, an amount up to 50% of the collateral value is added to the credit limit.

- After the loan application is completed, it is saved in the relevant application database. Afterwards, an information SMS is sent to the relevant phone number, and the status information (approval or rejection) and the limit information are returned from the endpoint.

- A completed loan application can only be queried with the identity number and date of birth information. If the date of birth and identity information do not match, it should not be possible to query.

```Notes: The credit limit multiplier is assumed to be 4.```

## Expected Deliverables (Scorecard):

- Backend project should work correctly according to the specified rules (adding a readme file or writing a Dockerfile)

- Attention should be paid to the quality (Clean Code), structure, and understandability of the code, and it should be open to development for new features (SOLID principles).

- Test writing

- Usage of Design Patterns

- Documentation (Swagger, OpenApi, etc.)

- Usage of technologies such as NoSQL, RDBMS(ORM) and Hibernate (JPA)

- Establishment of logging mechanism

- Frontend project running

## Technologies Used For BackEnd
- Spring Boot
- MySQL Database
- Tomcat 10

## Spring Features
- Spring Data JPA
- Spring Boot Maven Plugin
- MySQL Driver
- Lombok

## Virtualization Technologies
-Docker

## Technologies Used For Frontend
- React.js
- Material UI

## Tools
- Swagger UI
- MySQL Workbench 8.0 CE

## IDE
- Intellij IDEA


# Running Credit Application System locally

**Clone the project to your computer.**

```
	git clone https://github.com/keremcengiz0/credit-application-system-v1.git
```

**Make sure that ports 8080 and 3036 are not occupied before running the project. If another application is already running on MySQL, terminate it from the task manager. If you encounter an error message saying that port 8080 is already in use, kill the process running on port 8080.**

**Please navigate to the directory ./credit-application-system-v1 in the terminal, which contains the backend and frontend folders as well as the docker-compose file.**

**Write the following command afterwards.**

```
docker-compose up
```

**After running the "docker-compose up" command, you can monitor the project's startup process in the terminal. Once the project is up and running, you can access and use it by typing http://localhost:3000/ into your browser.**

# Images from project

### Customer creation page
![1](https://user-images.githubusercontent.com/112478277/220646548-8dacdec5-2e10-4dcd-b65d-52f7c8c7f1e4.png)

### Application page after customer creation
![2](https://user-images.githubusercontent.com/112478277/220647025-dd9d24e6-7f26-4014-88e5-1ce2871a598a.png)

### Application status inquiry page with relevant information
![3](https://user-images.githubusercontent.com/112478277/220647220-be925ae6-6cf6-410c-952a-ccc0f6d98824.png)
![4](https://user-images.githubusercontent.com/112478277/220647257-a1838269-06a8-417d-bfb7-690d09431c63.png)

### The page that lists all customers and includes deletion and customer information update functions
![5](https://user-images.githubusercontent.com/112478277/220647622-d76bb4fd-2745-4690-8d13-56e4fe9aff5d.png)

### Filter by identity number
![6](https://user-images.githubusercontent.com/112478277/220647838-f43c1bee-ce34-405a-9f80-f93f2207da85.png)

### Customer information update page
![7](https://user-images.githubusercontent.com/112478277/220648057-383bab4c-33c0-4e71-bdc2-7c3e54309ff6.png)

### Popup after clicking the delete customer icon
![8](https://user-images.githubusercontent.com/112478277/220648360-9f221735-b4f9-49d7-bdd2-085ba9c8d195.png)
