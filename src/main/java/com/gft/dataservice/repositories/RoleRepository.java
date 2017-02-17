package com.gft.dataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.dataservice.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{

}
