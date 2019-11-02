package beans;

import DAO.Identificator;

import java.io.Serializable;

public class Service implements Identificator<Integer>, Serializable {
    private int id;
    private String name;
    private String category;
    private double price;
    private boolean needDepartment;

    public Service() {
        this.name = "none";
        this.category = "none";
        this.price = 0;
        this.needDepartment=false;
    }

    public Service(String name, String category, double price, boolean needDepartment) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.needDepartment=needDepartment;

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNeedDepartment() {
        return needDepartment;
    }

    public void setNeedDepartment(boolean needDepartment) {
        this.needDepartment = needDepartment;
    }
}
