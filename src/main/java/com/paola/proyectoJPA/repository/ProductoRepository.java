package com.paola.proyectoJPA.repository;
import com.paola.proyectoJPA.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//poder obtener operaciones sobre el modelo
@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Integer> {
    Optional<ProductoModel> findByCodigo(String codigo);
}