package br.com.odemur.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	// @GetMapping("/customer")
	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Customer> list() {
		return customerService.list();
	}

	// @GetMapping("/customer/{id}")
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Customer> findCustomerById(@PathVariable("id") long id) {

		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent())
			return ResponseEntity.ok().body(customer.get());
		else
			return ResponseEntity.notFound().build();

	}

	// @PostMapping("/customer")
	@RequestMapping(value = "/customer", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	// @PutMapping("/customer/{id}")
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Customer> Put(@PathVariable("id") long id, @RequestBody Customer updateCustomer) {

		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent())
			return ResponseEntity.ok().body(customerService.update(id, updateCustomer));
		else
			return ResponseEntity.notFound().build();
	}

	// @DeleteMapping("/customer/{id}")
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {

		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent()) {
			customerService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
