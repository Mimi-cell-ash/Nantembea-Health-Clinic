//Class: MedicalRecord
//Represents one medical record entry created during a doctor’s appointment with a patient.

import java.time.LocalDate;
import java.util.ArrayList;


public class MedicalRecord{
    //Attributes
    //Unique medical record ID assigned by ClinicManagementSystem
    private int recordId;

    //Stores the date the medical record was created
    private LocalDate date;

    //Description of patient's condition
    private String diagnosis;

    //List of prescribed medications given to the patient
    private ArrayList<String> prescriptions;

    //Additional notes written by the doctor
    private String doctorNotes;

    //Notes that describe the treatment
    private String treatmentNotes;


    //Constructor
    public MedicalRecord(int recordId, LocalDate date, String diagnosis, String treatmentNotes,String doctorNotes){
        this.recordId = recordId;
        this.date = date;
        prescriptions = new ArrayList();
        this.diagnosis = diagnosis;
        this.doctorNotes = doctorNotes;
        this.treatmentNotes = treatmentNotes;

    }
    //Accessors or getters
    public int getRecordId(){
        return recordId;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getDiagnosis(){
        return diagnosis;
    }
    public String getTreatmentNotes(){
        return treatmentNotes;
    }
    public ArrayList<String> getPrescriptions() {
        return prescriptions;
    }
    public String getDoctorNotes(){
        return doctorNotes;
    }
    //Setters or mutators
    public void setTreatmentNotes(String treatmentNotes){
         this.treatmentNotes = treatmentNotes;
    }
    public void setDoctorNotes(String doctorNotes){
        this.doctorNotes = doctorNotes;
    }

}