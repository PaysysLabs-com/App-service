package com.paysyslabs.mojaloop.dao.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paysyslabs.mojaloop.dao.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByUsername(String username);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Customer SET token = :token WHERE ID = :id")
	public void updateLatestToken(@Param("id") Long id, @Param("token") String token);
}
