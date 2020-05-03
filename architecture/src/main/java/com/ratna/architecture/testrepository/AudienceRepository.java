package com.ratna.architecture.testrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratna.architecture.testmodel.Audience;

@Repository
public interface AudienceRepository extends JpaRepository<Audience, Integer> {

}
