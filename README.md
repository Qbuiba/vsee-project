1. Write a web testing framework do the following:
  ○ Open [room_url] on the browser and enter the waiting room (userA).
  ○ Open [room_url] on another browser, click For Providers, login
  [provider_credential] as userB and call userA (select “Continue on this browser”).
  ○ UserB sends a chat message and verifies userA can receive it.
  ○ UserB ends the call, then userA ends the call.
2. (Optional) Expand the framework so that the test script performs on different machines.
  For example:
  ○ Machine C → run the test script
  ○ Machine A → userA
  ○ Machine B → userB
  Hint: use selenium grid
3. Write an automated test script to retrieve information from this a github project (for
example this one https://github.com/SeleniumHQ) using github API
  a. How many total open issues are there across all repositories?
  b. Sort the repositories by date updated in descending order.
  c. Which repository has the most watchers?



ANSWER ASSIGNMENTS:
- ASSIGNMENT 1:
  -   Using Java, Selenium, TestNG frameworks
  -   Using extendreports: export html report in reports folder
  -   Using Log4j to wirte log in console for tracing
  -   Using WebDroverManager to manage different browsers with versions
  -   Page Object model, Page Factory
              
     -   CLIP DEMO: https://drive.google.com/file/d/12klFfYpSDlFs3IOOq4lIpIRQ6rDCGWdT/view?usp=sharing

- ASSIGNMENT 2:
  - Using Selenium Grid: Hub - Node mode, 1 selenium hub + 2 chrome node (User A and provider)
  - Using VNC Viewer to view the virtual chrome actions
             
    - CLIP DEMO: https://drive.google.com/file/d/1iVwTkQCNoLNyd-WagkO3Rz2YUJ-8H7Uy/view?usp=drive_link
 

HOW TO RUN THE 2 test cases above by command line:
1. Pull the repository
2. Using command: mvn test (to run the assignment 1)
3. Using command: mvn test -Dtest=SeleniumGrid (to run test class assignment 2)


- ASSIGNMENT 3: Using Python code with requests library to call to Github API: selenium
  Process the response then print the result as request -> Folder Assigment 3:
  ![image](https://github.com/user-attachments/assets/3d142beb-bce4-4eb1-989f-17ea0f3043be)
