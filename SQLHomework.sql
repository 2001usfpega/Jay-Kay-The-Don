------HOMEWORK------

-------QUESTION 2------

--2.1A
SELECT * FROM employee;
--2.1B
SELECT * FROM employee WHERE lastname='King';
--2.1C
SELECT * FROM employee WHERE firstname='Andrew' and reportsto IS NULL;


--2.2A
SELECT * FROM album ORDER BY title desc;
--2.2B
SELECT firstname FROM employee ORDER BY city;

--2.3A
SELECT * FROM Genre;
INSERT INTO genre VALUES (26, 'Rap');
INSERT INTO genre VALUES (27, '70/s');
--2.3B
Select * FROM Employee;
INSERT INTO employee VALUES (12,'Rhamy','Rob','CEO', 1, '03-OCT-97', '03-OCT-05', '1 Court', 'Medford' , 'NJ', 'United States', 'T5K 2N1', '609-623-6442', '6099236442','XXXXXX@XXXXX.com');
INSERT INTO employee VALUES (10,'Bamey','Bob','CCO', 1, '04-OCT-97', '04-OCT-05', '1 Court', 'Medford' , 'NJ', 'United States', 'T5K 2N1', '609-623-6442', '6099236442','XXXXXX@XXXXX.com');
--2.3C

Select * FROM Customer;
INSERT INTO customer VALUEs(60,'Robert','Rhamy', null, '1 Crimson drive', 'MEdford', 'NJ', 'UNITED STATES', '08055', '6099236442', null, 'Rhamdogg@Gmail.com', 5);
INSERT INTO customer VALUEs(61,'Colby','Agar', null, '1 Crimson drive', 'Baltimore', 'MD', 'UNITED STATES', '08055', '6099236442', null, 'colby@Gmail.com', 5);



--2.4A
UPDATE customer SET firstname='Robert' WHERE firstname = 'Aaron';
UPDATE customer SET lastname='Walter' WHERE lastname='Mitchell';

--2.4B
select * FROM artist ORDER BY name ;
update artist set name='CCR' WHERE name='Creedence Clearwater Revival';



--2.5A
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';



--2.6A
SELECT * FROM invoice WHERE total BETWEEN 15 and 50;
--2.6B
SELECT * FROM employee WHERE hire_date;


--2.7A
DELETE FROM customer WHERE firstname='Robert' AND lastname='Walter';



-------QUESTION 3: FUNCTIONS--------

--3.1A
SELECT sysdate FROM dual;
--3.1B
SELECT * FROM mediatype;
SELECT * From mediatype order by length(name) FROM dual;

--3.2A
SELECT * FROM invoice;
SELECT AVG(total) FROM invoice;

--3.2B
SELECT * FROM track;
SELECT MAX(unitprice) FROM track;

--3.3A
SELECT * FROM  invoiceline;
SELECT AVG(unitprice) FROM  invoiceline;

--3.4A
SELECT * FROM employee;
SELECT * FROM employee WHERE birthdate > '1-JAN-1968'; 

----QUESTION 4: STORED PROCEDURES-----
--4.1A
SELECT * FROM employee;
CREATE OR REPLACE PROCEDURE empDetails
IS

BEGIN
    SELECT firstname, lastname FROM employee; 
END;
/


--4.2A

SELECT * FROM employee;
CREATE OR REPLACE PROCEDURE empInfo
IS

BEGIN
    UPDATE employee SET firstname='Jacob' WHERE employeeID=1;
    UPDATE employee SET lastname='Wilson' WHERE employeeID=1;
END;
/


--4.2B
SELECT * FROM employee;
CREATE OR REPLACE PROCEDURE empManagers
IS

BEGIN
    SELECT employee.reportsto FROM employee; 
END;


--4.3A
CREATE OR REPLACE PROCEDURE customerInfo
IS

BEGIN
    SELECT employee.reportsto FROM employee; 
END;


----QUESTION 6: TRIGGERS-----

--6.1A
CREATE OR REPLACE TRIGGER new_record
AFTER INSERT
ON employee
FOR EACH ROW

BEGIN
    DBMS_OUTPUT.PUT_LINE('NEW EMPLOYEE');
END;
/
--6.1B
CREATE OR REPLACE TRIGGER update_row
AFTER UPDATE
ON album
FOR EACH ROW

BEGIN
    DBMS_OUTPUT.PUT_LINE('UPDATE ALBUM');
END;
/
CREATE OR REPLACE TRIGGER delete_row
AFTER DELETE
ON CUSTOMER
FOR EACH ROW

BEGIN
    DBMS_OUTPUT.PUT_LINE('DELETE CUSTOMER');
END;
/


----QUESTION 7: JOINS------
--7.1A
SELECT a.firstname, b.invoiceid FROM customer a INNER JOIN invoice b ON a.customerid = b.invoiceid;
--7.2A 
SELECT * FROM invoice
SELECT a.customerid, a.firstname, a.lastname, b.invoiceid, b.total FROM customer a LEFT OUTER JOIN invoice b ON a.customerid = b.customerid;
--7.3A
SELECT a.name, b.title FROM artist a RIGHT JOIN album b ON a.artistid=b.artistid;
--7.4A
SELECT * FROM artist CROSS JOIN album ORDER BY artist.name;
--7.5A
SELECT * FROM employee a INNER JOIN employee b ON a.reportsto = b.reportsto;


