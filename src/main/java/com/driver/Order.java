package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public int Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        String[] parts = id.split(":"); // Splitting HH:MM
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes; // Convert to total minut
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}
