\## Hi there, all projects were created using IntelliJ as the IDE, Java 21 is required for running them.
\## From the "food\_backend" folder, run maven spring to run the backend project:



mvn spring-boot:run



\## or



mvnw spring-boot:run



\## or run the JAR file in the target folder.



\## When it completes the run, access the Swagger URL to test the endpoints:



http://localhost:8080/swagger-ui/index.html



\## While the application is initializing, you will be able to see all the tables that were created. They are created in a H2 database in system memory using Hibernate.

\## The URL for the Demo endpoint with all features is this one (also present in the Swagger page)

http://localhost:8080/api/test/demo


\## I created a React Vite Web App with a simple paginated listing just to show the connection with the FE. It is very bare-bones, but I hope it is enough.

\## From the food\_frontend, run npm dev to run the project:

npm run dev



\## Then, when Vite initializes, access the URL:

http://localhost:5173/



\## Keep in mind that it expects the backend app to be running to populate the list. It uses a proxy since the ports are different to avoid CORS issues.





\## For improvements, I would add unit testing, investigate what other properties can be separated as constant values (like availability)

\## and put them into new tables with reference joins. I made the ingredient listing paginated because it is essential for almost all listings in my view.
\## I would also implement infinite scrolling in the React list, and add better styles.

\## Hope that the filters are enough for this demo. Please contact me in case of any questions, thanks a lot for the opportunity, see you all soon!







