package com.gft.dataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.dataservice.entities.DocumentPosition;


/**
 * Spring Data JPA repository for the User entity.
 */
public interface DocumentPositionRepository extends JpaRepository<DocumentPosition, Long> {
	DocumentPosition findOneById(String id);
}
