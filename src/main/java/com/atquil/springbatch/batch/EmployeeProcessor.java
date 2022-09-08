package com.atquil.springbatch.batch;

import com.atquil.springbatch.dao.EmployeeDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class EmployeeProcessor implements ItemProcessor<EmployeeDao, EmployeeDao> {


    @Override
    public EmployeeDao process(EmployeeDao employee) throws Exception {
        log.info("Employee: {} has age: {}",employee.getFirstName(),employee.getAge());
        // Will return Employee whose age is less than 50
        return Integer.parseInt(employee.getAge()) > 50 ? null : employee ;
    }
}