# Programming challenge

## Background
In food animal systems, animals move to different farms as they age. Each farm has a unique ID and must keep a record of the movement of animals from one farm to another. Here, we present some fictitious records of movements among pig farms.

## Mandatory
 #### Task needs to run with "docker compose up"

*Description of the data folder* 

*	*movements*: all records of animal movements 
    -  new_originpremid - column with the ID of the origin farm 
    -  new_destinationpremid - column with the ID of the destination farm 
    -  new_numitemsmovedcolumn - column with the number of moved animals

*	*population*: complete list of the farms
    -  premiseid - column with the ID of the farms
    -  total_animal - column with the current number of animals for the farm

## Challenge
The challenge is to create a system to visualize and manage the movement records (CRUD Operations with appropriate error handling on the code, both UI and API). This
system have to follow the requirements bellow:

- Has to be composed of 3 components: a REST API, a SPA WEB client, and a relational database.
- The relational database can be any database that you like, PostgreSQL, MariaDB, etc..
- The data provided in this repo should be auto-imported into the database in a normalized schema. done
- The REST API has to written in Java Spring Boot. done
- The Web Client have to written in Angular.
- The project should have an authentication system for both UI and API components. i.e. data from API shouldn't be accessible from the Network tab / Browser console.
- The project should have atleast 2 unit test cases each for UI and API and 1 end-to-end test (UI) using testing frameworks like Cypress.
- The project submitted should include appropriate documentation and a working video of the entire project. For the video, start from scratch. Clone the repo in a fresh location and run the project. Then, navigate the UI. Show API logs. Keep the browser console open on the side.
- As mentioned earlier, the task should run with a single command. `docker compose up`.

## Bonus
Bonus points will be awarded for implementing:
- Role based access control
    - Create atleast 2 roles. `ADMIN` and `USER`. ADMINS can add more users. USERS can't add more users.
    - You can create a default admin account on app startup and provide the creds in project submission README file.
    - Additionally, you can implement a `VIEWER` role that can perform only read operations.
- Auto-updating Swagger API documentation
    - Think of an appropriate place to host this (Within app? External?)
    - How will authentication work for a user to be able to directly hit the API without going through the UI?
    - Can this become a github action for keeping it always up-to-date? If so, feel free to include one such workflow in the project.  
- Monitoring dashboard
    - A tool to monitor and visualize user stats (visits, clicks/tab, on screen click-density, etc...).
    - Feel free to be creative if you are attempting this.
      
*Note*: "If you are unable to implement the bonus section, include your ideas about the above questions in the README. Lay out the steps of how you would implement the same."

## Evaluation 
The submitted project will be evaluated considering your experience. For example, a
person with a background in backend development will be evaluated with higher
expectations of the API and database code. A UI person will be evaluated with
higher expectations on the design of the UI. You need to clearly mention which role you are targetting in the Github repository / README that you submit as a part of the project. 
