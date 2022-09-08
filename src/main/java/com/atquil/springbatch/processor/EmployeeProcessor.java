package com.atquil.springbatch.processor;

import com.atquil.springbatch.dao.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;


@Slf4j
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(Employee employee) throws Exception {
        //It will contain the business logic for the Employee
        log.info("STEP 2: PROCESSING THE VALUES: "+employee);
        return employee;
    }


}
