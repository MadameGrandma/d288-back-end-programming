package com.example.demo.dao;

import com.example.demo.entities.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "Excursion", path = "excursion")
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
