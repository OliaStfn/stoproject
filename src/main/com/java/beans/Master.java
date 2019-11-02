package beans;

import DAO.Identificator;

import java.io.Serializable;
import java.time.LocalDate;

public class Master extends Person implements Identificator<Integer>, Serializable {
    private int id;
    private String post;
    private String workPlace;
    private String address;
    private String phoneNumber;
    private String passport;
    private MasterStatus status;

    public Master() {
        super();
        this.post = "none";
        this.workPlace = "none";
        this.address = "none";
        this.phoneNumber = "none";
        this.passport = "none";
        this.status = MasterStatus.FREE;
    }

    public Master(String name, String surname, LocalDate dateOfBirth,
                  String post, String workPlace, String address, String phoneNumber,
                  String passport, MasterStatus status) {
        super(name, surname, dateOfBirth);
        this.post = post;
        this.workPlace = workPlace;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.passport = passport;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public MasterStatus getStatus() {
        return status;
    }

    public void setStatus(MasterStatus status) {
        this.status = status;
    }
}
