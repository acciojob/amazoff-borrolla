package com.driver;

public class Order {

    private final String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id = id;
        String[] parts = deliveryTime.split(":"); // Splitting HH:MM
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        this.deliveryTime = hours * 60 + minutes; // Convert to total minutes
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}

