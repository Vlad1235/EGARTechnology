DROP TABLE IF EXISTS securities;

CREATE TABLE securities(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    uuid VARCHAR(100) NOT NULL UNIQUE,
    date_issue DATE NOT NULL,
    security_name VARCHAR(100) NOT NULL,
    price INT
);

INSERT INTO SECURITIES (uuid, date_issue, security_name, price) VALUES
    ('7bc8476b-7ed2-4490-8a2e-807135069d86', DATE '2020-04-21', 'Бумага1', 1200),
    ('a4184742-0a8d-47fb-b99a-41718635c24c', DATE '2020-04-22', 'Бумага2', 3000),
    ('c6485fcd-2f6a-4047-ae63-be8162965372', DATE '2020-02-14', 'Бумага3', 500),
    ('c6485fcd-2f6a-4047-ae63-be8162115372', DATE '2020-02-15', 'Бумага4', 2100);


