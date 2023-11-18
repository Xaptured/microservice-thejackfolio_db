![Static Badge](https://img.shields.io/badge/java-v17-green)
[![Build and Test Java Spring Boot Application](https://github.com/Xaptured/microservice-thejackfolio_db/actions/workflows/build-and-test.yml/badge.svg)](https://github.com/Xaptured/microservice-thejackfolio_db/actions/workflows/build-and-test.yml)

# thejackfolio_db

This microservice is used as a layer on the Database to do all kind of operations like add, update, select and delete. There are other microservices which are using this service to get data from the database or save data in the database. 

 

If you want to clone this repository and use it in your local then please follow these steps: 

 

Step 1: Please go to the path “src/main/java/resources” and there you will find “keys_dummy.properties” file. 

 

Step 2: Rename the file from “keys_dummy.properties” to “keys.properties” and add your values accordingly. You can also take reference from Google to fill the appropriate keys in the file. 

 

Step 3: Please select the appropriate environment like dev or prod in “application.properties” which internally selects the appropriate profile and change the database values accordingly. 

 

Other links that can be useful while running this repo in local: 

Swagger UI: http://localhost:{your-port}/swagger-ui/index.html 

H2-Console: http://localhost:{your-port}/h2-console 

All the APIs related to this service has been documented in a postman_collection file which can be viewed by importing in postman application. It gives you better idea regarding APIs and the parameters that we are passing into them.
This doscument will be uploaded in the TheJackFolioParentReposiotry as well as in each microservice. Please go through it and also you can directly test the APIs after importing into Postman.


### Will be adding more information here if I add any new feature 
