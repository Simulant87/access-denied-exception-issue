# About

this example project showing an issue with `AccessDeniedException` logged, after the migration from spring boot 2.7.8 to 3.0.2

The main branch is showing the issue on spring boot 3.0.2. There is another branch `working-spring-boot-2.7.8` to compare the previous working configuration to without exception.

## Instructions

start the application with:
```
./gradlew bootRun
```

open [http://localhost:8080/file](http://localhost:8080/file)

Observed behaviour:

An example file is downloaded successfully.
In addition, there are multiple exceptions logged, caused by `org.springframework.security.access.AccessDeniedException: Access Denied`, but the user is not facing an issue.

Expected behaviour:

The file is correctly downloaded (as it currently is), but there should be no exceptions logged, like it is in the configuration based on spring boot 2.7.8
