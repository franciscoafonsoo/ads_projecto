CREATE TABLE PRODUCT (ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY NOT NULL, ITEMID INTEGER, DESCRIPTION VARCHAR(255), PRICE DOUBLE, QTY DOUBLE)
CREATE TABLE SALEPRODUCT (ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY NOT NULL, SALE_ID INTEGER, PRODUCT_ID INTEGER, QTY DOUBLE)
CREATE TABLE SALE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY NOT NULL, DATE DATE, TOTAL DOUBLE, STATUS CHAR(1))
ALTER TABLE SALEPRODUCT ADD CONSTRAINT FK_SALEPRODUCT_PRODUCT_ID FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID)
ALTER TABLE SALEPRODUCT ADD CONSTRAINT FK_SALEPRODUCT_SALEPRODUCTS_ID FOREIGN KEY (SALE_ID) REFERENCES SALE (ID)
CREATE TABLE EMPLOYEE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY NOT NULL, NAME VARCHAR(255), PASSWORD VARCHAR(255), BIRTH DATE, TLM INT, ENTRY_DATE DATE, SALARY INTEGER, VAT INTEGER UNIQUE, SCORE_ONE INTEGER, SCORE_TWO INTEGER, SCORE_THREE INTEGER, FILED BOOLEAN, STORE_ID INTEGER, SECTION_ID INTEGER)
CREATE TABLE STORE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY NOT NULL, ADDRESS VARCHAR(255), DISTRICT VARCHAR(255), TLM INTEGER, FAX INTEGER, EMAIL VARCHAR(255))
CREATE TABLE VACANCIES (ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY NOT NULL, STORE_ID INTEGER, SECTION_ID INTEGER, FREE INTEGER, OCCUPIED INTEGER, ENTRY_DATE DATE)
ALTER TABLE VACANCIES ADD CONSTRAINT FK_VACANCIES_STORE_ID FOREIGN KEY (STORE_ID) REFERENCES STORE (ID)
CREATE TABLE TRANSFERS (ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1) PRIMARY KEY NOT NULL, VACANCY_ID INTEGER, EMPLOYEE_ID INTEGER, SCORE DOUBLE, ENTRY_DATE DATE, IS_PROCESSED BOOLEAN)
ALTER TABLE TRANSFERS ADD CONSTRAINT FK_TRANSFERS_VACANCY_ID FOREIGN KEY (VACANCY_ID) REFERENCES VACANCIES (ID)
ALTER TABLE TRANSFERS ADD CONSTRAINT FK_TRANSFERS_EMPLOYEE_ID FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE (ID)