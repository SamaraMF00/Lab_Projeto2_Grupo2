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

CREATE TABLE request (
                      id INT auto_increment PRIMARY KEY,
                      customer_id INT CHECK (customer_id > 0) NOT NULL,
                      inclusion_date TIMESTAMP(0) NULL DEFAULT NULL,
                      due_date TIMESTAMP(0) NULL DEFAULT NULL,
                      state VARCHAR(100) NOT NULL,

                      CONSTRAINT FK_for_request_customer_id FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE request_vehicle (
                      id INT auto_increment PRIMARY KEY,
                      vehicle_id INT CHECK (vehicle_id > 0) NOT NULL,
                      request_id INT CHECK (request_id > 0) NOT NULL,

                      CONSTRAINT FK_for_RequestVehicle_vehicle_id FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
                      CONSTRAINT FK_for_RequestVehicle_request_id FOREIGN KEY (request_id) REFERENCES request(id)

);
