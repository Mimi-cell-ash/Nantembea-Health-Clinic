import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;


public class Patient extends Person {

    // Unique ID for each patient
    private int patientId;

    // List of the patient's medical records
    private ArrayList<MedicalRecord> medicalHistory;

    // Current status of the patient (e.g., Active, Inactive)
    private String status;

    // Appointments keyed by date (optional extra structure)
    private TreeMap<LocalDate, Appointment> appointments;

    // List of recorded allergies for this patient
    private ArrayList<String> allergies;

    // Constructor
    public Patient(int patientId, String name, String email, String phone, String address, String gender, LocalDate dateOfBirth, String emergencyContact) {

        super(name, email, phone, address, gender, dateOfBirth, emergencyContact);
        this.patientId = patientId;
        this.status = "Active";
        this.medicalHistory = new ArrayList<>();
        this.appointments = new TreeMap<>();
    }

    // Getters

    public int getPatientId() {
        return patientId;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<MedicalRecord> getMedicalHistory() {
        return medicalHistory;
    }

    public TreeMap<LocalDate, Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    // Setters

    // Updates the patient's status
    public void setStatus(String status) {
        this.status = status;
    }

    // Adds a medical record to the patient's history
    public void addMedicalRecord(MedicalRecord record) {
        medicalHistory.add(record);
    }

    // Adds a new allergy to the list
    public void addAllergy(String allergy) {
        allergies.add(allergy);
    }

    // Adds an appointment to the appointments map
    public void addAppointment(Appointment appointment) {
        appointments.put(appointment.getDate(), appointment);
    }
}
