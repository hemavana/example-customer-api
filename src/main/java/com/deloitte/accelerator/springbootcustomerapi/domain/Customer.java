package com.deloitte.accelerator.springbootcustomerapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Generates getters, setters, toString, equalsAndHashCode
@AllArgsConstructor // Generates a constructor with all arguments
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

}

