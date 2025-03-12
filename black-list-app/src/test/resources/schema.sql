CREATE TABLE IF NOT EXISTS black_listed_persons (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    person_uuid UUID NOT NULL UNIQUE,
    PRIMARY KEY(id)
);