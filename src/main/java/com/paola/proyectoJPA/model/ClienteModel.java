package com.paola.proyectoJPA.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
@Data
@Entity
@Table(name ="clientes")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message="El nombre no debe estar vacio")
    @NotNull(message="El nombre no debe estar vacio")
    @Size(min = 5, max = 75)
    private String nombre;
    @NotBlank(message="El apellido no debe estar vacio")
    @NotNull(message="El apellido no debe estar vacio")
    @Size(min = 5, max = 75)
    private String apellido;
    @NotBlank (message="El dni no debe estar vacio")
    @NotNull(message="El dni no debe estar vacio")
    @Size(min = 5, max = 11)
    private String dni;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<FacturaModel> facturas;


}
