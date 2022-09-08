DROP TABLE employee IF EXISTS;

CREATE TABLE employee  (
                         person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
                         first_name VARCHAR(20),
                         last_name VARCHAR(20)
);
