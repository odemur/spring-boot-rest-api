package br.com.odemur.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.odemur.api.model.Customer;

/**
 * CustomerRespository Interface
 *
 * @author Odemur Marangoni
 * @version 1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
