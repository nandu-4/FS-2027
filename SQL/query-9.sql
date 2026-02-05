/*
Write a SQL query to Find the diagnosis and room number for John Doe's most recent admission.

Tables:
-------
Admissions ==> AdmissionID int primary key auto_increment, PatientID int not null,
	       AdmissionDate date not null, DischargeDate date, 
	       Diagnosis text, DoctorAssigned varchar(50), RoomNumber varchar(10) 

Patient ==> PatientID int primary key auto_increment, FirstName varchar(50) not null, 
	    LastName varchar(50) not null, Gender enum('Male','Female','Other') not null,
	    DateOfBirth date not null, ContactNumber varchar(15), Address text

OUTPUT:
+-----------+------------+                                                                                                                            
| Diagnosis | RoomNumber |                                                                                                                            
+-----------+------------+                                                                                                                            
| Pneumonia | 101        |                                                                                                                            
+-----------+------------+ 
*/

use FS;
SELECT a.Diagnosis, a.RoomNumber 
FROM Admissions a 
JOIN Patient p ON p.PatientID = a.PatientID
WHERE p.FirstName = 'John' AND p.LastName = 'Doe' ORDER BY a.AdmissionDate DESC  LIMIT 1