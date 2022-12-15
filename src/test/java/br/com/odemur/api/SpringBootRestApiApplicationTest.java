package br.com.odemur.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.odemur.api.model.Customer;
import br.com.odemur.api.service.CustomerService;

@WebMvcTest
public class SpringBootRestApiApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void listCustomersTest() throws Exception {
		List<Customer> customers = new ArrayList<>();
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john@mail.com");
		customers.add(customer);
		Mockito.when(customerService.list()).thenReturn(customers);
		mockMvc.perform(get("/customer")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].id", Matchers.equalTo((int) customer.getId())))
				.andExpect(jsonPath("$[0].firstName", Matchers.equalTo(customer.getFirstName())));
	}

	@Test
	public void saveCustomerTest() throws Exception {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john@mail.com");
		Mockito.when(customerService.save(ArgumentMatchers.any())).thenReturn(customer);
		String json = mapper.writeValueAsString(customer);
		mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", Matchers.equalTo((int) customer.getId())))
				.andExpect(jsonPath("$.firstName", Matchers.equalTo(customer.getFirstName())));
	}

}