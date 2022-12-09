package br.com.odemur.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.odemur.api.model.Customer;
import br.com.odemur.api.repository.CustomerRepository;

/**
 * CustomerService Class
 *
 * @author Odemur Marangoni
 * @version 1.0
 */
@Component
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> list() {
		return customerRepository.findAll();
	}

	public Customer getById(Long id) {
		return customerRepository.findById(id).get();
	}

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer update(Long id, Customer updateCustomer) {
		Customer customer = customerRepository.getOne(id);

		customer.setFirstName(updateCustomer.getFirstName());
		customer.setLastName(updateCustomer.getLastName());
		customer.setEmail(updateCustomer.getEmail());

		return customerRepository.save(customer);
	}

	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

}
