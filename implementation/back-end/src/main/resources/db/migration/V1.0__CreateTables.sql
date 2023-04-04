CREATE TABLE customer (
                       id INT auto_increment PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       cpf VARCHAR(15) NOT NULL,
                       rg VARCHAR(15) NOT NULL,
                       address VARCHAR(100) NOT NULL unique,
                       employers VARCHAR(100) NOT NULL
);

CREATE TABLE vehicle (
                       id INT auto_increment PRIMARY KEY,
                       licensePlate VARCHAR(7) NOT NULL,
                       model VARCHAR(100) NOT NULL,
                       brand VARCHAR(20) NOT NULL,
                       year VARCHAR(4) NOT NULL,
                       owner VARCHAR(100) NOT NULL
);
