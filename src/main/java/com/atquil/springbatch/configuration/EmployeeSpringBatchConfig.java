package com.atquil.springbatch.configuration;

import com.atquil.springbatch.dao.Employee;
import com.atquil.springbatch.processor.EmployeeProcessor;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class EmployeeSpringBatchConfig {

    // One Job can trigger many Steps using StepBuilder

    ///
    ///                              A job can trigger one
    //                               or more than one steps
    // ┌─────────────────────────────┐            ┌─────────────────────────────┐
    // │                             ├───────────►│  Steps(StepBuilderFactory   │
    // │  Job (JobBuilderFactory)    │            └┬────────────────────────────┘
    // └─┬───────────────────────────┘             │
    //   │                                         │  ┌────────────────────────────────────────┐
    //   │                                         │  │ 1 ItemReader(Read file from source)    │
    //   │                                         │  │                                        │
    //   │  Once a job is started, it will save    │  │                                        │
    //   │                                         └► │                                        │
    //   │    it's metadata in job repository         │                                        │
    //   │                                            │  2 ItemProcessor (Business Logic)      │
    //   │       which will help in debuging.         │                                        │
    //   │                                            │                                        │
    //   ▼                                            │                                        │
    //┌─────────────────────┐                         │                                        │
    //│                     │                         │                                        │
    //│    Job Repository   │                         │  3  ItemWriter(Update the file to DB)  │
    //└─────────────────────┘                         └────────────────────────────────────────┘


    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    // Reader part
    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("EmployeeItemReader")
                .resource(new ClassPathResource("EmployeeData.csv"))
                .delimited()
                .names(new String[]{"firstName", "lastName","age"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
                    setTargetType(Employee.class);
                }})
                .build();
    }

    // Business Processing Part
    @Bean
    public EmployeeProcessor processor(){
        return new EmployeeProcessor();
    }

    // Writer Part : Add the csv to database
    @Bean
    public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO employee (first_name, last_name , age) VALUES (:firstName, :lastName, :age)")
                .dataSource(dataSource)
                .build();
    }


    // Now let's first create the steps i.e READ --> PROCESS --> WRITE
    @Bean
    public Step step1(JdbcBatchItemWriter<Employee> writer) {
        return stepBuilderFactory.get("Started Batch Processing")
                .<Employee, Employee> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    // Now let's create the Job which will trigger the step1
    @Bean
    public Job startJobForEmployee(EmployeeJobCompleteListener listener, Step step1) {
        return jobBuilderFactory.get("Job is triggering the Steps")
                .incrementer(new RunIdIncrementer())
                .listener(listener) // this will be called once the flow of step 1 is completed
                .flow(step1)
                .end()
                .build();
    }

    // So, now let's call an Executor will trigger the job from the endpoint
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

}
