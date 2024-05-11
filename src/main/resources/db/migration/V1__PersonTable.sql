CREATE TABLE person (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    dob date,
    location VARCHAR(100)
);