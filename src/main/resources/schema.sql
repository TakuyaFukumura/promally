CREATE TABLE IF NOT EXISTS message (
    id BIGINT AUTO_INCREMENT,
    text VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS property (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    rent DECIMAL(10,0),
    area DECIMAL(6,2),
    building_age INT,
    layout VARCHAR(20),
    status VARCHAR(20) NOT NULL,
    description VARCHAR(1000),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);
