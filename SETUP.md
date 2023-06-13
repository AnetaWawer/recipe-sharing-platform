# RECIPE SHARING PLATFORM CHALLENGE SETUP

### Below guide provides instructions on how to set up and run application.

### Here are the steps that you should follow in order:

#### To run the program:
1. Clone the repository to your local machine.
2. Before starting setup process, ensure that you have JDK and Apache Maven installed.
3. Create database in PostgreSQL.
4. Configure your database and update connection details. To do that, insert environment variables in your IDE as below:

| name        | value                                                     |
|-------------|-----------------------------------------------------------|
| DB_URL      | _your database url_                                       |
| DB_USER     | _your database username_                                  |
| DB_PASSWORD | _your database password_                                  |
| SECRET_KEY  | _947vwipw94874ncow7d023jfi3c83o8dm20vefvs630vm20cwmci80s_ |

5. Add PostgreSQL as data source in your IntelliJ. Add user, password and database name to data source properties.
6. Run RecipeSharingChallengeJavaApplication.

#### To run tests:
1. Follow steps 1-4 from previous section.
5. Run 'Tests in 'java'' to see result of all test.
