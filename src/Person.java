// CLASS: Person
// Role: Base class representing any person in the clinic system and has generic features
// Encapsulates personal contact and emergency information


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {
    //Stores the name of the person
    private String name;

    //Stores the email address of the person
    private String email;

    //Stores the phone number of the person
    private String phone;

    //Stores the location of the person
    private String address;

    //Stores the gender identity of the person
    private String gender;

    //Stores date of Birth
    private LocalDate dateOfBirth;

    //Stores a trusted person's contact in case of emergencies
    private String emergencyContact;

    //Records when the person joined the system
    private LocalDateTime registrationDate;

// Constructor: Receives all required personal details and assigns them to the class fields
    public Person(String name, String email, String phone, String address,
                  String gender, LocalDate dateOfBirth, String emergencyContact) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.emergencyContact = emergencyContact;
        this.registrationDate = LocalDateTime.now();

    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    @Override
    public String toString() {
        return name + "," + email + "," + phone + "," + address + "," +
                gender + "," + dateOfBirth + "," + emergencyContact;
    }


}
