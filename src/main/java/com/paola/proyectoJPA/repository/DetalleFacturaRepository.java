package com.paola.proyectoJPA.repository;

import com.paola.proyectoJPA.model.DetalleFacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFacturaModel, Integer> {
   Optional<DetalleFacturaModel> findByid (Integer id);
}
