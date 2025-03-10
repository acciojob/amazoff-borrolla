package com.driver;



public class DeliveryPartner {

    public final String id;
    public int numberOfOrders;

    public DeliveryPartner(String id) {
        this.id = id;
        this.numberOfOrders = 0;
    }

    public void getNumberOfOrders() {  // ✅ Correct return type
    }

    @SuppressWarnings("unused") // Suppress unused method warning
    public void setNumberOfOrders(int numberOfOrders) {  // ✅ Fixed parameter type
        this.numberOfOrders = numberOfOrders;
    }

    @SuppressWarnings("unused") // Suppress unused method warning
    public String getId() {
        return id;
    }
}