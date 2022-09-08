package com.atquil.springbatch.batch;

import com.atquil.springbatch.dao.EmployeeDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class EmployeeProcessor implements ItemProcessor<EmployeeDao, EmployeeDao> {


    @Override
    public EmployeeDao process(final EmployeeDao employeeDao) throws Exception {
        final String firstName = employeeDao.getFirstName().toUpperCase();
        final String lastName = employeeDao.getLastName().toUpperCase();

        final EmployeeDao transformedEmployeeDao = new EmployeeDao(firstName, lastName);

        log.info("Converting (" + employeeDao + ") into (" + transformedEmployeeDao + ")");

        return transformedEmployeeDao;
    }

}