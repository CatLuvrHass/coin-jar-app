# starling-challenge

``` the flow:
GET - > Accounts
function -> get the accountUUID and  CategoryUUID
GET -> Transaction of week
function -> RoundUp.
if no goal
PUT -> CREATES a GOAL
then
GET -> Gets the goal
function -> Adds up the current + roundup
PUT -> Confirm update the goal.
```

## To use:
    - Run the main application
    - Input your access Token to the terminal
    - Create your goal if not exists
    - Confirm your transfer or Create a new goal.
    - save up.



## Mr Hindsight and some thoughts:
    - Building models for each response would have made things a lot easier and more clear,
    rather than the data operations functions perhaps.
    - Creating an interface for the requests and http connections might also clean this up more
    - With more time, I could add more dealing with broken input from the user. 
    - Persisting the data somehow into a database could allow for extra fun. Not sure if it is
    in the scope of the challange to not allow the user to round up again and again, draining 
    their bank account.# Challenge-Starling
