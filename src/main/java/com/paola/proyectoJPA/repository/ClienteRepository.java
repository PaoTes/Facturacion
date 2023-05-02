package com.paola.proyectoJPA.repository;

import com.paola.proyectoJPA.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository  extends JpaRepository<ClienteModel, Integer>{
    Optional<ClienteModel> findByDni (String dni);
}
