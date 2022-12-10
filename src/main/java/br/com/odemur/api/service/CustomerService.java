package br.com.odemur.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.odemur.api.model.Customer;
import br.com.odemur.api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> list() {
		return customerRepository.findAll();
	}

	public Optional<Customer> findById(long id) {
		return customerRepository.findById(id);
	}

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public void saveAll(List<Customer> customer) {
		customerRepository.saveAll(customer);
	}

	public Customer update(long id, Customer updateCustomer) {
		Customer customer = customerRepository.findById(id).get();

		if (updateCustomer.getFirstName() != null) {
			customer.setFirstName(updateCustomer.getFirstName());
		}

		if (updateCustomer.getLastName() != null) {
			customer.setLastName(updateCustomer.getLastName());
		}

		if (updateCustomer.getEmail() != null) {
			customer.setEmail(updateCustomer.getEmail());
		}

		return customerRepository.save(customer);
	}

	public void delete(long id) {
		customerRepository.deleteById(id);
	}

}
