package com.driver.test;

import com.driver.Application;
import com.driver.OrderService;
import org.junit.jupiter.api.*;
import com.driver.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {


    @Autowired
    private OrderController orderController; // Let Spring inject the OrderController

    @BeforeEach
    public void setUp() {
        OrderService orderService = new OrderService(); // Create OrderService instance
        orderController = new OrderController(orderService); // Correctly initialize OrderController
    }

    @Test
    @Order(1)
    void testContextLoads() {
        assertNotNull(orderController, "OrderController should be initialized by Spring Boot");
    }
}