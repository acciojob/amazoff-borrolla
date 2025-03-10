package com.driver;
public class DeliveryPartner {

    private final String id;
    private int numberOfOrders;

    public DeliveryPartner(String id) {
        this.id = id;
        this.numberOfOrders = 0;
    }

    public String getId() {
        return id;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public void setNumberOfOrders() {
        this.setNumberOfOrders(numberOfOrders);
    }
}