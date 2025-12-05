// Class: ClinicManagementSystem
// Coordinates all core operations of the clinic (patients, doctors, appointments, records)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class ClinicManagementSystem implements FileOperations, ReportGenerator {

    // Maps a unique patientId (key) to a Patient object (value)
    private HashMap<Integer, Patient> patients;

    // Stores all registered doctors in a list
    private ArrayList<Doctor> doctors;

    // Stores all scheduled appointments
    private ArrayList<Appointment> appointments;

    // Stores all medical records created in the system
    private ArrayList<MedicalRecord> medicalRecords;

    // ID counters
    private int nextPatientId;
    private int nextDoctorId;
    private int nextAppointmentId;
    private int nextRecordId;

    // Maps each date (String "YYYY-MM-DD") to a list of appointments on that date
    private TreeMap<String, ArrayList<Appointment>> appointmentsByDate;

    // Constructor: initializes collections and ID counters
    public ClinicManagementSystem() {
        patients = new HashMap<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        medicalRecords = new ArrayList<>();
        appointmentsByDate = new TreeMap<>();

        nextPatientId = 1;
        nextDoctorId = 1;
        nextAppointmentId = 1;
        nextRecordId = 1;
    }

    // Uses nextPatientId as the new patient's unique ID
    public Patient addPatient(String name, String email, String phone, String address, String gender, LocalDate dateOfBirth, String emergencyContact) {
        int id = nextPatientId;
        Patient patient = new Patient(id, name, email, phone, address, gender, dateOfBirth, emergencyContact);
        patients.put(id, patient);
        nextPatientId++;
        return patient;
    }

    // Returns the Patient object if found, or null if no match
    public Patient findPatient(int patientId) {
        return patients.get(patientId);
    }

    // Uses nextDoctorId as the new doctor's unique ID
    public Doctor addDoctor(String specialisation, int yearsOfExperience, String hospitalRoom, String availabilitySchedule,
                            String name, String email, String phone, String address, String gender, LocalDate dateOfBirth, String emergencyContact) {

        int docId = nextDoctorId;
        Doctor doctor = new Doctor(docId, name, email, phone, address, gender, dateOfBirth, emergencyContact,
                specialisation, yearsOfExperience, hospitalRoom, availabilitySchedule);

        doctors.add(doctor);
        nextDoctorId++;
        return doctor;
    }

    // Returns the Doctor object if found, or null otherwise
    public Doctor findDoctor(int doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId() == doctorId) {
                return doctor;
            }
        }
        return null;
    }

    // Returns the created Appointment object, or null if patient/doctor is not found
    public Appointment scheduleAppointment(int patientId,
                                           int doctorId,
                                           LocalDate date,
                                           String time,
                                           String reason) {

        Patient patient = findPatient(patientId);
        Doctor doctor = findDoctor(doctorId);

        if (patient == null || doctor == null) {
            // Either the patient or doctor does not exist
            return null;
        }

        int id = nextAppointmentId;
        Appointment appointment = new Appointment(id, time, reason, patient, doctor, date, "Scheduled");
        appointments.add(appointment);

        // Use a String key "YYYY-MM-DD" for consistency with the lab
        String dateKey = date.toString();

        ArrayList<Appointment> dailyList = appointmentsByDate.get(dateKey);
        if (dailyList == null) {
            dailyList = new ArrayList<>();
            appointmentsByDate.put(dateKey, dailyList);
        }
        dailyList.add(appointment);

        nextAppointmentId++;
        return appointment;
    }

    // Save current system data (patients) to a text file in CSV format
    @Override
    public void saveToFile(String filename) throws IOException {
        // Use try-with-resources so the writer closes automatically
        try (FileWriter writer = new FileWriter(filename)) {
            for (Integer id : patients.keySet()) {
                Patient p = patients.get(id);

                // 4-digit ID (0001, 0002, ...)
                String idFormatted = String.format("%04d", id);

                // CSV line: id,name,email,phone,address,gender,dateOfBirth,emergencyContact
                String line = idFormatted + ","
                        + p.getName() + ","
                        + p.getEmail() + ","
                        + p.getPhone() + ","
                        + p.getAddress() + ","
                        + p.getGender() + ","
                        + p.getDateOfBirth().toString() + ","
                        + p.getEmergencyContact();

                writer.write(line + System.lineSeparator());
            }
        }
    }

    // Save all appointments to a file (appointments.txt)
    // CSV format per line:
    // appointmentId,date,time,patientId,patientName,doctorId,doctorName,reason,status
    public void saveAppointmentsToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Appointment a : appointments) {
                String line =
                        String.format("%04d", a.getAppointmentId()) + "," +
                                a.getDate().toString() + "," +
                                a.getTime() + "," +
                                a.getDoctor().getName() + "," +
                                a.getReason() + "," +
                                a.getStatus();

                writer.write(line + System.lineSeparator());
            }
        }
    }

    // Load patient data from file and recreate Patient objects
    @Override
    public void loadFromFile(String filename) throws IOException {
        // Clear any existing patients and reset ID counter
        patients.clear();
        nextPatientId = 1;

        // try-with-resources ensures Scanner is closed automatically
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip blank lines
                if (line.isEmpty()) {
                    continue;
                }

                // CSV format:
                // id,name,email,phone,address,gender,dateOfBirth,emergencyContact
                String[] parts = line.split(",");
                if (parts.length < 8) {
                    System.out.println("Skipping bad line: " + line);
                    continue;
                }

                String idText = parts[0];          // e.g. "0001"
                int id = Integer.parseInt(idText); // 1

                String name = parts[1];
                String email = parts[2];
                String phone = parts[3];
                String address = parts[4];
                String gender = parts[5];
                LocalDate dateOfBirth = LocalDate.parse(parts[6]);
                String emergencyContact = parts[7];

                Patient p = new Patient(
                        id,
                        name,
                        email,
                        phone,
                        address,
                        gender,
                        dateOfBirth,
                        emergencyContact
                );

                patients.put(id, p);

                // Keep nextPatientId ahead of the largest id we see
                if (id >= nextPatientId) {
                    nextPatientId = id + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("The file could not be read: " + e.getMessage());
            throw e;
        }
    }

    // Generate a summary/report for a specific patient
    @Override
    public void generatePatientReport(int patientId) {
        // Look up the patient
        Patient p = patients.get(patientId);

        //  If not found, print message and stop
        if (p == null) {
            String idFormatted = String.format("%04d", patientId);
            System.out.println("No patient found with ID: " + idFormatted);
            return;
        }

        //  Print the patient details as a report
        String idFormatted = String.format("%04d", patientId);
        System.out.println("----- PATIENT REPORT -----");
        System.out.println("ID: " + idFormatted);
        System.out.println("Name: " + p.getName());
        System.out.println("Email: " + p.getEmail());
        System.out.println("Phone: " + p.getPhone());
        System.out.println("Address: " + p.getAddress());
        System.out.println("Gender: " + p.getGender());
        System.out.println("Date of Birth: " + p.getDateOfBirth());
        System.out.println("Emergency Contact: " + p.getEmergencyContact());
        System.out.println("--------------------------");
    }

    // Generate a report showing all appointments scheduled on a given date
    @Override
    public void generateAppointmentReport(String date) {
        // Normalize incoming date if user used single-digit day or month (optional)
        String[] parts = date.split("-");
        if (parts.length == 3) {
            String y = parts[0];
            String m = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
            String d = parts[2].length() == 1 ? "0" + parts[2] : parts[2];
            date = y + "-" + m + "-" + d;
        }

        ArrayList<Appointment> dailyList = appointmentsByDate.get(date);

        if (dailyList == null || dailyList.isEmpty()) {
            System.out.println("No appointments on " + date);
            return;
        }

        System.out.println("===== APPOINTMENTS ON " + date + " =====");
        for (Appointment a : dailyList) {
            String idFormatted = String.format("%04d", a.getAppointmentId());
            System.out.println(
                    "ID: " + idFormatted +
                            ", Time: " + a.getTime() +
                            ", Patient: " + a.getPatient().getName() +
                            ", Doctor: " + a.getDoctor().getName() +
                            ", Reason: " + a.getReason() +
                            ", Status: " + a.getStatus()
            );
        }
        System.out.println("=====================================");
    }
}
