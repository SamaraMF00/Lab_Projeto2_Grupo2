CREATE TABLE customer (
                       id INT auto_increment PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       cpf VARCHAR(15) NOT NULL,
                       rg VARCHAR(15) NOT NULL,
                       address VARCHAR(100) NOT NULL unique,
                       employers VARCHAR(100) NOT NULL

);
