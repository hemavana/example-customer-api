package com.deloitte.accelerator.springbootcustomerapi;

import com.deloitte.accelerator.springbootcustomerapi.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class SpringBootCustomerApiApplicationTests {

	@Autowired
	private MockMvc mockMvc; // Mock the web environment

	@Test
	void getAllCustomers() throws Exception {
		mockMvc.perform(get("/customers"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$", hasSize(3))) // Check for 3 customers
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("Alice")))
				.andExpect(jsonPath("$[1].lastName", is("Johnson")))
				.andExpect(jsonPath("$[2].email", is("charlie.brown@example.com")));
	}

	@Test
	void getCustomerById_found() throws Exception {
		mockMvc.perform(get("/customers/2"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.firstName", is("Bob")));
	}

	@Test
	void getCustomerById_notFound() throws Exception {
		mockMvc.perform(get("/customers/4"))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.message", is("Customer not found with ID: 4")));
	}

}
