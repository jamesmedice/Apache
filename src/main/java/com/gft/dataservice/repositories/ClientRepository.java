package com.gft.dataservice.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.dataservice.entities.Client;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findOneByNumber(String number);

	List<Client> findAllByCreatedDateBefore(Date dateTime);

	Client findOneByDesignation(String email);
	
}
