import java.time.LocalDate;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClinicManagementSystem system = new ClinicManagementSystem();

        //Adding multiple patients
        Patient patient1 = system.addPatient(
                "Christine",
                "christine@gmail.com",
                "0552349986",
                "Kumasi",
                "Female",
                LocalDate.of(2007, 5, 8),
                "Dad: 02445678923"
        );

        Patient patient2 = system.addPatient(
                "Michelle",
                "michelle@gmail.com",
                "0249988775",
                "Accra",
                "Female",
                LocalDate.of(2005, 11, 6),
                "Mum: 054330211"
        );
        Patient patient3 = system.addPatient(
                "Getrude",
                "getrude@gmail.com",
                "0554697843",
                "HO",
                "Female",
                LocalDate.of(2007, 3, 17),
                "Aunt: 024567891"
        );

        System.out.println("New patient registered with ID: " + String.format("%04d", patient1.getPatientId()));
        System.out.println("New patient registered with ID: " + String.format("%04d", patient2.getPatientId()));
        System.out.println("New patient registered with ID: " + String.format("%04d", patient3.getPatientId()));

        //Adding multiple doctors
        Doctor doctor1 = system.addDoctor(
                "Orthopedic Surgeon",
                15,
                "Theatre 1",
                "Mon - Fri, 8:00 - 20:00",
                "Dr.Slyvester",
                "drsly@clinic.com",
                "02445678945",
                "Kumasi, Ghana",
                "Male",
                LocalDate.of(1971, 3, 19),
                "Mum: 0209988776"
        );
        Doctor doctor2 = system.addDoctor(
                "Pediatrician",
                10,
                "Room 6",
                "Mon - Fri, 8:00 - 21:00",
                "Dr.Eric",
                "eric@clinic.com",
                "0554678912",
                "Tema, Ghana",
                "Male",
                LocalDate.of(1985, 7, 9),
                "Mum: 0245567890"
        );
        Doctor doctor3 = system.addDoctor(
                "Gynecologist",
                18,
                "Theatre 2",
                "Mon - Fri, 8:00 - 22:00",
                "Dr.Eunice",
                "eunice@clinic.com",
                "0554667912",
                "Takoradi, Ghana",
                "Female",
                LocalDate.of(1985, 9, 9),
                "Sis: 045678912"
        );

        System.out.println("Doctor registered with ID: " + String.format("%04d", doctor1.getDoctorId()));
        System.out.println("Doctor registered with ID: " + String.format("%04d", doctor2.getDoctorId()));
        System.out.println("Doctor registered with ID: " + String.format("%04d", doctor3.getDoctorId()));

        //Multiple appointments
        Appointment appointment1 = system.scheduleAppointment(
                patient1.getPatientId(), doctor1.getDoctorId(),
                LocalDate.of(2025, 12, 5),
                "10:30", "General Checkup"
        );
        Appointment appointment2 = system.scheduleAppointment(
                patient2.getPatientId(), doctor1.getDoctorId(),
                LocalDate.of(2025, 8, 7),
                "12:45", "Knee pain"
        );
        Appointment appointment3 = system.scheduleAppointment(
                patient3.getPatientId(), doctor2.getDoctorId(),
                LocalDate.of(2025, 12, 10),
                "09:00", "Follow-up therapy"
        );

        System.out.println("Appointment Scheduled with ID: " + String.format("%04d", appointment1.getAppointmentId()));
        System.out.println("Appointment Scheduled with ID: " + String.format("%04d", appointment2.getAppointmentId()));
        System.out.println("Appointment Scheduled with ID: " + String.format("%04d", appointment3.getAppointmentId()));

        //Saving patients to file
        system.saveToFile("patients.txt");
        system.saveAppointmentsToFile("appointments.txt");

        System.out.println("\n PATIENT REPORT ");
        system.generatePatientReport(patient1.getPatientId());

        System.out.println("\n APPOINTMENTS ON 2025-08-07 ");
        system.generateAppointmentReport("2025-08-07");

        System.out.println("\n APPOINTMENTS ON 2025-12-10");
        system.generateAppointmentReport("2025-12-10");

    }
}