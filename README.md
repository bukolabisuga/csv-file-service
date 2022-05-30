# League Backend Challenge Solution

The web application is written in Java - SpringBoot and built with Maven.

### Run the SpringBoot Application
##### _Step 1_
Download the project or clone from GitHub [here](https://github.com/bukolabisuga/csv-file-service.git)

##### _Step 2_
Install intelliJ on your PC and open the project folder.

##### _Step 3_
Select and run the RunCSVApplication to start up the app locally and execute the endpoints in Postman using this collection - https://www.getpostman.com/collections/611069a292e1a285e6fb

### API Endpoints
Given an uploaded csv file
```
1,2,3
4,5,6
7,8,9
```

1. Echo http://localhost:8080/echo
    - Returns the matrix as a string in matrix format.
   ##### Output

    ```
    1,2,3
    4,5,6
    7,8,9
    ``` 
2. Invert http://localhost:8080/invert
    - Returns the matrix as a string in matrix format where the columns and rows are inverted
   ##### Output
    ```
    1,4,7
    2,5,8
    3,6,9
    ``` 
3. Flatten http://localhost:8080/flatten
    - Returns the matrix as a 1 line string, with values separated by commas.
   ##### Output
    ```
    1,2,3,4,5,6,7,8,9
    ``` 
4. Sum http://localhost:8080/sum
    - Return the sum of the integers in the matrix
   ##### Output

    ```
    45
    ``` 
5. Multiply http://localhost:8080/multiply
    - Returns the product of the integers in the matrix
   ##### Output

    ```
    362880
    ```
   