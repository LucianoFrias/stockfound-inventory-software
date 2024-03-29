package org.stockfound.stockfoundinventorysoftware.entities;

import java.sql.Date;
import java.time.LocalDate;

public class Item {
        private int id;
        private Date date;
        private String serialNumber;
        private String customerName;
        private String type;
        private String brand;
        private String model;
        private String status;
        private int price;


    public Item(Date date, String serialNumber, String customerName, String type, String brand, String model, String status, int price) {
        this.date = date;
        this.serialNumber = serialNumber;
        this.customerName = customerName;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.price = price;
    }

    public Item(int id, Date date, String serialNumber, String customerName, String type, String brand, String model, String status, Integer price) {
        this.id = id;
        this.date = date;
        this.serialNumber = serialNumber;
        this.customerName = customerName;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
