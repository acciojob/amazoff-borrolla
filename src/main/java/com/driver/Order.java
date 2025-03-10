package com.driver;

public class Order {

    private final String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id = id;
        try {
            String[] parts = deliveryTime.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid time format. Expected HH:MM.");
            }
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
                throw new IllegalArgumentException("Invalid time value. Hours: 0-23, Minutes: 0-59.");
            }
            this.deliveryTime = hours * 60 + minutes;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in delivery time: " + deliveryTime, e);
        }
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        String[] parts = deliveryTime.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        this.deliveryTime = hours * 60 + minutes;
    }
}

