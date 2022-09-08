-- Spring Boot runs schema-@@platform@@.sql automatically during startup.
-- -all is the default for all platforms.
DROP TABLE employee IF EXISTS;

CREATE TABLE employee  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    age VARCHAR(3)
);
