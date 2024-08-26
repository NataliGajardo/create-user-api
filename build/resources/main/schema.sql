CREATE TABLE IF NOT EXISTS user_data (
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    is_active BOOLEAN,
    creation_date TIMESTAMP,
    modification_date TIMESTAMP,
    last_login TIMESTAMP
);

CREATE TABLE IF NOT EXISTS phone_data (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    number VARCHAR(20) NOT NULL,
    citycode VARCHAR(10),
    countrycode VARCHAR(10),
    FOREIGN KEY (user_id) REFERENCES user_data(id)
);

