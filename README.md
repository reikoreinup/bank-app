# Simple Bank App

## Instructions to run

The project comes with a gradle wrapper. 

Running `./gradlew bootRun` in the project root directory will build and start the application.

Without configuration the application will run on `localhost:8080/bank/`

## API

The application has multiple endpoints for conducting business logic. 

After starting the application, the documentation for these endpoints is available at `http://localhost:8080/bank/swagger-ui/`

## Initial data

On every start of the application, migration scripts will be run in order.

Migration scripts initiate the database tables and insert two accounts.

These account numbers are EE1 and EE2. Both have a starting balance of 1000.

## Testing

The lonely test included in the project can be run with gradle wrapper.

Running `./gradlew test` in the project root directory will run the test.


