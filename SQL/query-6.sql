/*
Write a SQL query to find which patients (first and last name) were admitted for "Pneumonia", and
which doctor is treatingthem?
Tables:
-------
Admissions ==> AdmissionID int primary key auto_increment, PatientID int not null,
	       AdmissionDate date not null, DischargeDate date, 
	       Diagnosis text, DoctorAssigned varchar(50), RoomNumber varchar(10) 

Patient ==> PatientID int primary key auto_increment, FirstName varchar(50) not null, 
	    LastName varchar(50) not null, Gender enum('Male','Female','Other') not null,
	    DateOfBirth date not null, ContactNumber varchar(15), Address text


OUTPUT:
+-----------+----------+----------------+                                                                                                             
| FirstName | LastName | DoctorAssigned |
+-----------+----------+----------------+ 
| John      | Doe      | Dr. Allen      |
+-----------+----------+----------------+
*/
use fs;
SELECT p.FirstName, p.LatName, a.DoctorAssigned 
FROM Patient p
JOIN Admissions a ON p.PatientID = a.PatientID 
WHERE a.Diagnosis = 'Pnemonia';