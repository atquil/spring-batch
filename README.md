# Spring batch with HyperSQL
- It's a simple implementation of Spring batch using HyperSQL
- In most of the production environment you will not find the use of HyperSQL
## Important Points : 

1. `schema-all.sql` : Spring Boot runs schema-@@platform@@.sql automatically during startup, and `-all` is default for all platform
2. **Execution Method** : There are two ways to execute the spring batch
   - **Execute through REST endpoint**: As in most of the cases, there will be a `trigger` which will call this endpoint to start the batch processing
       - In application.properties `spring.batch.job.enabled=false`
       - Trigger the endpoint : `GET http://localhost:8080/spring-batch/with-hyper-sql`
       - Check in console
   - **Execute on start** : Job will start running onnce you start the spring boot
     - In `application.properties` add `spring.batch.job.enabled=true`
     - Optional Step to remove anything related to REST endpoint
       - Remove `SpringBatchController.java` file
       - From `BatchConfiguration.java` this piece of code, which will execute the job, once pinged from REST endpoint
```aidl
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
```
3. Business Logic i.e. processing on data based on business requirement is done in `EmployeeProcessor.java` which will take `EmployeeDao.java` and filter out employee with age higher than 50
4. `JobCompletionNotificationListner.java` on completion of Job, will check the database and pull out the results.
5. Important **dependency** for spring-batch in **gradle**
   - `implementation 'org.springframework.boot:spring-boot-starter-batch'`
   - `runtimeOnly 'org.hsqldb:hsqldb'`

## Spring Batch Architecture

For more details go to main branch : https://raw.githubusercontent.com/atquil/spring-batch/main/Spring%20Batch%20Architecture.gif 

![Spring Batch Architecture](https://raw.githubusercontent.com/atquil/spring-batch/main/Spring%20Batch%20Architecture.gif)
