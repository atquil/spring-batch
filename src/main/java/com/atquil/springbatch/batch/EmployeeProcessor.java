package com.atquil.springbatch.batch;

import com.atquil.springbatch.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(Employee employee) throws Exception {
        if(Integer.parseInt(employee.getAge() )<18){
            log.info("Age is smaller than 18");
            return null;
        }
        return employee;
    }
}
