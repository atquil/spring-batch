package com.atquil.springbatch.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDao {

    private String lastName;
    private String firstName;
    private String age;

}