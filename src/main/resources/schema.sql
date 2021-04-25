DROP TABLE IF EXISTS TBL_EMPLOYEE;
DROP TABLE IF EXISTS TBL_COMPANY;

CREATE TABLE TBL_COMPANY (
   id identity NOT NULL PRIMARY KEY,
   name VARCHAR(250) NOT NULL
);

CREATE TABLE TBL_EMPLOYEE (
   id identity NOT NULL PRIMARY KEY,
   companyId INT NOT NULL,
   name VARCHAR(250) NOT NULL,
   surname VARCHAR(250) NOT NULL,
   email VARCHAR(250) NOT NULL,
   address VARCHAR(1000) NOT NULL,
   salary double NOT NULL,
   FOREIGN KEY(companyId) REFERENCES TBL_COMPANY(id)
);

