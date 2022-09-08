# Batch Processing 
```
What ?  It's a method for processing a large amount of data in a consistent manner. 

Why ? To efficiently process, large number of iterative data jobs

```

# Spring batch

```aidl
It's  lightweight comprehensive framework which enable development of robust batch applications. 
```

Features : 

1. **Efficiency** : When resource are provided, it allows company to efficiently process data.
2. **Chunk based processing** : Helps in setting limitation for data processing 
3. **Simplicity** : Simple and easy to set-up then batch processing
4. **Business Intelligence** : Process large volume of data quickly, and many records parallely thus providing power to take action faster

## Spring Batch Architecture

![Spring Batch Architecture](Spring%20Batch%20Architecture.gif)

## When to use it ?

1. `Data Migration`: Moving data from one system (e.g. Legacy system) to other and doing processing to make it compatible on the go 
2. `Fast Processing`: Moving huge amount of data in parallel to save time but also having **robust transactional control** and **failure recovery mechanisms**
3. `Continuous Processing`: Having continuous data flow, spring batch allows optimized processing through configuration
4. `Task Orchestration`: To execute some complex task, having several macro operation spring batch provide Spring cloud dataflow
5. `ETL (Extract, Transform and Load)`: Generated file periodically needs to be transformed and loaded in database

Credits: 
1. Spring.io: https://spring.io/guides/gs/batch-processing/
2. Hevodata: https://hevodata.com/learn/spring-batch-jobs/
3. Top Total: https://www.toptal.com/spring/spring-batch-tutorial
4. Giuliana Bexerra : https://giuliana-bezerra.medium.com/why-you-should-be-using-spring-batch-for-batch-processing-83f5aafb965f
5. JavaTechie: https://www.youtube.com/watch?v=hr2XTbKSdAQ&ab_channel=JavaTechie 
