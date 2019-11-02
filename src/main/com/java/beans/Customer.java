package beans;

import DAO.Identificator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Customer extends Person implements User, Identificator<Integer>,Serializable {
    private int id;
    private String login;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdDate;
    private ArrayList<Order> orders;
    private boolean usedBus;

    public Customer() {
        super();
        this.login = "none";
        this.password = "none";
        this.email = "none";
        this.phoneNumber = "none";
        createdDate = LocalDateTime.now();
        this.orders=new ArrayList<>();
        this.usedBus=false;

    }

    public Customer(String name, String surname, LocalDate dateOfBirth, String login,
                    String password, String email, String phoneNumber) {
        super(name, surname, dateOfBirth);
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orders=new ArrayList<>();
        setUsedBus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
        setUsedBus();
    }

    private void setUsedBus() {
        for(Order order: orders){
            for (Service service: order.getServices()){
                if(!service.isNeedDepartment()){
                    usedBus=true;
                    break;
                }
            }
        }
    }

    public boolean isUsedBus() {
        return usedBus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdDate=" + createdDate +
                ", orders=" + orders +
                ", usedBus=" + usedBus +
                '}';
    }
}
