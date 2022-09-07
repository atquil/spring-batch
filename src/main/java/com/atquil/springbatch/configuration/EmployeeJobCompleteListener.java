package com.atquil.springbatch.configuration;


import com.atquil.springbatch.dao.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeJobCompleteListener  extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeJobCompleteListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void afterJobCompletedGetInfo(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT first_name, last_name, age FROM employee",
                    (rs, row) -> new Employee(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3))
            ).forEach(employee -> log.info("Employee : " + employee + "> in the database."));
        }
    }
}
