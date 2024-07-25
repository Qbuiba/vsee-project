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
