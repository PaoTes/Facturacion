package com.paola.proyectoJPA.repository;

import com.paola.proyectoJPA.model.FacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaModel, Integer> {

}
