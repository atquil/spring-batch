# spring-batch



For spring batch application to run, you must have some database configured

## Be careful on what dependency you are importing. 

## Flow

```
                              A job can trigger one
                               or more than one steps
 ┌─────────────────────────────┐            ┌─────────────────────────────┐
 │                             ├───────────►│  Steps(StepBuilderFactory   │
 │  Job (JobBuilderFactory)    │            └┬────────────────────────────┘
 └─┬───────────────────────────┘             │
   │                                         │  ┌────────────────────────────────────────┐
   │                                         │  │ 1 ItemReader(Read file from source)    │
   │                                         │  │                                        │
   │  Once a job is started, it will save    │  │                                        │
   │                                         └► │                                        │
   │    it's metadata in job repository         │                                        │
   │                                            │  2 ItemProcessor (Business Logic)      │
   │       which will help in debuging.         │                                        │
   │                                            │                                        │
   ▼                                            │                                        │
┌─────────────────────┐                         │                                        │
│                     │                         │                                        │
│    Job Repository   │                         │  3  ItemWriter(Update the file to DB)  │
└─────────────────────┘                         └────────────────────────────────────────┘

```