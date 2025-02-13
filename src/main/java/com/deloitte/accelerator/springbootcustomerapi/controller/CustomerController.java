package com.deloitte.accelerator.springbootcustomerapi.controller;

import com.deloitte.accelerator.springbootcustomerapi.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerController() {
        // Hardcode some sample customer data
        customers.add(new Customer(1, "Alice", "Smith", "alice.smith@example.com"));
        customers.add(new Customer(2, "Bob", "Johnson", "bob.johnson@example.com"));
        customers.add(new Customer(3, "Charlie", "Brown", "charlie.brown@example.com"));
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        var customer = customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get()); // 200 OK if found
        } else {
            var responseBody = new HashMap<>();
            responseBody.put("message", "Customer not found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }
    }
}


