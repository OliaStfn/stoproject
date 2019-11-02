package beans;

import DAO.Identificator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

public class Order implements Identificator<Integer>, Serializable {
    private int id;
    private String carBrand;
    private String carModel;
    private String licensePlate;
    private String description;
    private String receptionPoint;
    private LocalDate orderDate;
    private OrderStatus status;
    private int masterId;
    private int customerId;
    private HashSet<Service> services;
    private double allPrice;

    public Order() {
        this.carBrand = "none";
        this.carModel = "none";
        this.licensePlate = "none";
        this.description = "none";
        this.receptionPoint = "none";
        this.orderDate = LocalDate.now();
        this.status = OrderStatus.WAITING;
        this.masterId = 0;
        this.customerId = 0;
        this.services = new HashSet<>();
        setAllPrice();
    }

    public Order(String carBrand, String carModel, String licensePlate, String receptionPoint,
                 LocalDate orderDate, OrderStatus status, int masterId, int customerId) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.licensePlate = licensePlate;
        this.description = "none";
        this.receptionPoint = receptionPoint;
        this.orderDate = orderDate;
        this.status = status;
        this.masterId = masterId;
        this.customerId = customerId;
        this.services = new HashSet<>();
        setAllPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceptionPoint() {
        return receptionPoint;
    }

    public void setReceptionPoint(String receptionPoint) {
        this.receptionPoint = receptionPoint;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public HashSet<Service> getServices() {
        return services;
    }

    public void setServices(HashSet<Service> services) {
        this.services = services;
        setAllPrice();
    }

    public void addService(Service service) {
        services.add(service);
        setAllPrice();
    }

    public double getAllPrice() {
        return allPrice;
    }

    public void setAllPrice() {
        allPrice = 0;
        for (Service service : this.services) {
            allPrice += service.getPrice();
        }
    }
}
