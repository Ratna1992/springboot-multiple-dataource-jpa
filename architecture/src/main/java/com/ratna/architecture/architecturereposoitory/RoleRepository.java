package com.ratna.architecture.architecturereposoitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratna.architecture.architecturemodel.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
