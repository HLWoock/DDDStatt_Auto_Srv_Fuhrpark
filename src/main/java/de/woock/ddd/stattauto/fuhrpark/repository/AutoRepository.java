package de.woock.ddd.stattauto.fuhrpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.woock.ddd.stattauto.fuhrpark.entity.fahrzeug.Auto;

public interface AutoRepository extends JpaRepository<Auto, Long> {

	Auto findById(Long id);
}