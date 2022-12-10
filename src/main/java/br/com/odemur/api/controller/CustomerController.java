package br.com.odemur.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public Iterable<Customer> list() {
		return customerService.list();
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable("id") long id) {

		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent())
			return ResponseEntity.ok().body(customer.get());
		else
			return ResponseEntity.notFound().build();

	}

	@PostMapping("/customer")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> Put(@PathVariable("id") long id, @RequestBody Customer updateCustomer) {

		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent())
			return ResponseEntity.ok().body(customerService.update(id, updateCustomer));
		else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {

		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent()) {
			customerService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
