/*
Write a SQL query to Provide a list of all patients who have been admitted but do not yet have a
discharge date recorded.


Tables:
-------
Admissions ==> AdmissionID int primary key auto_increment, PatientID int not null,
	       AdmissionDate date not null, DischargeDate date, 
	       Diagnosis text, DoctorAssigned varchar(50), RoomNumber varchar(10) 

Patient ==> PatientID int primary key auto_increment, FirstName varchar(50) not null, 
	    LastName varchar(50) not null, Gender enum('Male','Female','Other') not null,
	    DateOfBirth date not null, ContactNumber varchar(15), Address text

OUTPUT:
+-----------+----------+---------------+
| FirstName | LastName | AdmissionDate |
+-----------+----------+---------------+
| Jane      | Smith    | 2024-11-15    |
+-----------+----------+---------------+ 
*/
use fs;
SELECT p.FirstName, p.LastName, a.AdmissionDate
FROM Patient p 
JOIN Admissions a ON p.PatientID = a.PatientID
WHERE a.DischargeDate IS NULL;