package com.atquil.springbatch.controller;

import com.atquil.springbatch.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spring-batch")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // For triggering the batch processing from the endpoint we will have to disable spring.batch.job.enable to false in application.properties
    @PostMapping("/save-to-batch")
    public void addToDatabase(){
        employeeService.saveFileToDbUsingBatchAfterProcessing();
    }
}
