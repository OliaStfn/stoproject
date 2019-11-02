package beans;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Person implements Serializable {
    private String name;
    private String surname;
    LocalDate dateOfBirth;

    public Person() {
        this.name = "none";
        this.surname = "none";
        this.dateOfBirth = LocalDate.now();
    }

    public Person(String name, String surname, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth =dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
