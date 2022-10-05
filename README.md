# Round Up feature for savings

- The flow:
  - GET - > Accounts 
    - function -> get the accountUUID and  CategoryUUID 
  - GET -> Transaction of week 
    - function -> RoundUp. 
    - if no goal 
  - PUT -> CREATES a GOAL 
    - then 
  - GET -> Gets the goal 
    - function -> Adds up the current + roundup 
  - PUT -> Confirm update the goal.


## To run:
- Run the main in App.
- if you're feeling lucky and fancy ``mvn clean compile assembly:single`` then ``docker build -t roundup:latest .`` & ``docker run -it --pid=host roundup:latest``.
- or directly run the jar file with ``mvn clean compile assembly:single`` then ``java -jar target/target/roundup-1.0-SNAPSHOT-jar-with-dependencies.jar``.

    - Input your access Token to the terminal.
    - Create your goal if not exists.
    - Confirm your transfer or Create a new goal.
    - save up.


## Future work:
- Building models for each response would have made things a lot easier and more clear, rather than the data operations functions, perhaps.
- Creating an interface for the requests and http connections might also clean this up more.
- With more time, I could add more dealing with broken input from the user. Write tests. 
- Persisting the data somehow into a database could allow for more functionality. To not allow the user to round up again and again, draining their bank account.
