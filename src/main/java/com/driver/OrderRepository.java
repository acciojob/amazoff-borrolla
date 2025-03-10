package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private final HashMap<String, Order> orderMap;
    private final HashMap<String, DeliveryPartner> partnerMap;
    private final HashMap<String, HashSet<String>> partnerToOrderMap;
    private final HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<>();
        this.partnerMap = new HashMap<>();
        this.partnerToOrderMap = new HashMap<>();
        this.orderToPartnerMap = new HashMap<>();
    }

    public void saveOrder(Order order){
        // your code here
        orderMap.put(order.getId(), order);

    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
        partnerMap.put(partnerId, new DeliveryPartner(partnerId));
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here
            //add order to given partner's order list
            //increase order count of partner
            //assign partner to this order
            orderToPartnerMap.put(orderId,partnerId);
            partnerToOrderMap.computeIfAbsent(partnerId, k -> new HashSet<>()).add(orderId);
            partnerMap.get(partnerId).setNumberOfOrders();
        }
    }

    public Order findOrderById(String orderId){
        // your code here
        return orderMap.getOrDefault(orderId,null);
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        return partnerMap.getOrDefault(partnerId, null);
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        // your code here
       return partnerToOrderMap.getOrDefault(partnerId, new HashSet<>()).size();
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        return new ArrayList<>(partnerToOrderMap.getOrDefault(partnerId,new HashSet<>()));

    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders
        return new ArrayList<>(orderMap.keySet());
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        if (partnerMap.containsKey(partnerId)) {
            HashSet<String> assignedOrders = partnerToOrderMap.getOrDefault(partnerId, new HashSet<>());

            for (String orderId : assignedOrders) {
                orderToPartnerMap.remove(orderId);
            }

            partnerToOrderMap.remove(partnerId);
            partnerMap.remove(partnerId);
        }
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by
        if (orderMap.containsKey(orderId)) {
            String partnerId = orderToPartnerMap.get(orderId);
            if (partnerId != null) {
                partnerToOrderMap.getOrDefault(partnerId, new HashSet<>()).remove(orderId);
                partnerMap.get(partnerId).setNumberOfOrders();
                orderToPartnerMap.remove(orderId);
            }
            orderMap.remove(orderId);
        }
    }

    public Integer findCountOfUnassignedOrders(){
        // your code here
        return (int) orderMap.keySet().stream().filter(orderId -> !orderToPartnerMap.containsKey(orderId)).count();
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
        int count = 0;

        // Convert HH:MM timeString to integer (minutes)
        String[] timeParts = timeString.split(":");
        int givenTime = Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);

        if (partnerToOrderMap.containsKey(partnerId)) {
            for (String orderId : partnerToOrderMap.get(partnerId)) {
                Order order = orderMap.get(orderId);

                // Compare delivery time with givenTime
                if (order != null && order.getDeliveryTime() > givenTime) {
                    count++;
                }
            }
        }
        return count;
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        String lastTime = "00:00";
        if(partnerToOrderMap.containsKey(partnerId))
        {
            for(String orderId : partnerToOrderMap.get(partnerId))
            {
                Order order = orderMap.get(orderId);
                if(order != null && order.getDeliveryTime() > 0)
                {
                    lastTime = String.valueOf(order.getDeliveryTime());
                }
            }
        }
        return lastTime;
    }
}