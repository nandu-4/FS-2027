/*
Write a SQL query to Find How many admissions has each doctor handled, and what is the total number of patients per doctor?


Tables:
-------
Admissions ==> AdmissionID int primary key auto_increment, PatientID int not null,
	       AdmissionDate date not null, DischargeDate date, 
	       Diagnosis text, DoctorAssigned varchar(50), RoomNumber varchar(10) 

Patient ==> PatientID int primary key auto_increment, FirstName varchar(50) not null, 
	    LastName varchar(50) not null, Gender enum('Male','Female','Other') not null,
	    DateOfBirth date not null, ContactNumber varchar(15), Address text

OUTPUT:
+----------------+-----------------+                                                                                                                  
| DoctorAssigned | TotalAdmissions |                                                                                                                  
+----------------+-----------------+                                                                                                                  
| Dr. Allen      |               1 |                                                                                                                  
+----------------+-----------------+

*/
use fs;
SELECT DoctorAssigned, COUNT(*) AS TotalAdmissions 
FROM Admissions 
GROUP BY DoctorAssigned;