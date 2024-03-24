package org.stockfound.stockfoundinventorysoftware.entities;

import java.sql.Date;
import java.time.LocalDate;

public class Item {
        private Integer id;
        private Date date;
        private String serialNumber;
        private String customerName;
        private String status;
        private boolean invoice;
        private boolean packed;
        private boolean shipped;
        private int amount;


    public Item(Date date, String serialNumber, String customerName, String status, boolean invoice, boolean packed, boolean shipped, int amount) {
        this.date = date;
        this.serialNumber = serialNumber;
        this.customerName = customerName;
        this.status = status;
        this.invoice = invoice;
        this.packed = packed;
        this.shipped = shipped;
        this.amount = amount;
    }

    public Item(Integer id, Date date, String serialNumber, String customerName, String status, boolean invoice, boolean packed, boolean shipped, int amount) {
        this.id = id;
        this.date = date;
        this.serialNumber = serialNumber;
        this.customerName = customerName;
        this.status = status;
        this.invoice = invoice;
        this.packed = packed;
        this.shipped = shipped;
        this.amount = amount;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isInvoice() {
        return invoice;
    }

    public void setInvoice(boolean invoice) {
        this.invoice = invoice;
    }

    public boolean isPacked() {
        return packed;
    }

    public void setPacked(boolean packed) {
        this.packed = packed;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
