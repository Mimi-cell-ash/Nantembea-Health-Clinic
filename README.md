📌 Nantembea Health Clinic – OOP Java Project
🏥 Project Overview
Nantembea Health Clinic is a Java-based clinic management system developed as part of Object-Oriented Programming (OOP) Lab Exercise 4.
The system manages:
Patients
Doctors
Appointments
Medical Records

It demonstrates key OOP concepts including inheritance, composition, encapsulation, interfaces, file handling, and reporting.

🧩 Project Structure
src/
 ├── Main.java
 ├── Person.java
 ├── Patient.java
 ├── Doctor.java
 ├── Appointment.java
 ├── MedicalRecord.java
 ├── ClinicManagementSystem.java
 ├── FileOperations.java
 └── ReportGenerator.java

patients.txt
appointments.txt
README.md

✨ Features Implemented
👤 Patient Management
i.Add new patients
ii.Unique 4-digit patient IDs
iii.Store personal info + emergency contacts
iv.Generate patient report
v.Save/Load patient data from patients.txt

🩺 Doctor Management
i.Register doctors
ii.Track specialisation, room, availability schedule
iii.Auto-generated doctor IDs

📅 Appointment Scheduling
i.Link patients to doctors
ii.Store date, time, reason, and status
iii.View appointments for a specific date
iv.Save all appointments to appointments.txt

📄 Reporting System
Includes two reports:
Patient Report – detailed summary of patient info
Appointment Report – shows all appointments on a given date

💾 File Handling
i.Implements the FileOperations interface to:
ii.Save patient data
iii.Load patient data
iv.Save appointment data

🧪 Sample Console Output
New patient registered with ID: 0001
Doctor registered with ID: 0001
Appointment Scheduled with ID: 0001

----- PATIENT REPORT -----
ID: 0001
Name: Christine
Email: christine@gmail.com
Phone: 0552349986
Address: Kumasi
Gender: Female
Date of Birth: 2007-05-08
Emergency Contact: Dad: 02445678923
--------------------------

===== APPOINTMENTS ON 2025-12-10 =====
ID: 0003, Time: 09:00, Patient: Getrude, Doctor: Dr.Eric, 
Reason: Follow-up therapy, Status: Scheduled
=====================================

📂 File Format Samples
patients.txt
0001,Christine,christine@gmail.com,0552349986,Kumasi,Female,2007-05-08,Dad: 02445678923
0002,Michelle,michelle@gmail.com,0249988775,Accra,Female,2005-11-06,Mum: 054330211

appointments.txt
0001,2025-12-05,10:30,0001,0001,General Checkup,Scheduled
0002,2025-08-07,12:45,0002,0001,Knee pain,Scheduled

▶️ How to Run the Program
i.Open in IntelliJ, VS Code, or terminal
ii.Compile:javac src/*.java
iii.Run:java src/Main

🛠️ Technologies Used

i.Java SE
ii.Object-Oriented Programming
iii.File Handling (Scanner, FileWriter)
iv.Collections (ArrayList, HashMap, TreeMap)
v.LocalDate for date management

👩🏽‍💻 Author

Miriam Gale (Mimi-cell-ash)
