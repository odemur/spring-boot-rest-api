package br.com.odemur.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.odemur.api.model.Customer;
import br.com.odemur.api.service.CustomerService;

/**
 * CustomerController Class
 *
 * @author Odemur Marangoni
 * @version 1.0
 */
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer")
	public List<Customer> getAll() {
		return customerService.list();
	}

	@GetMapping("/customer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer getCustomerById(@PathVariable Long id) {
		return customerService.getById(id);
	}

	@PostMapping("/customer")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@PathVariable(value = "id") Long id, @RequestBody Customer customer) {
		return customerService.update(id, customer);
	}

	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.delete(id);
	}

}
