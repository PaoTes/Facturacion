package com.paola.proyectoJPA.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
@Data
@Entity
@Table(name ="productos")
public class ProductoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @NotNull(message="El producto debe tener una descripcion")
    @Size(min = 2, max = 100)
    private String descripcion;
    @NotBlank
    @NotNull(message="El producto debe tener un codigo que lo identifique")
    @Size(min =1, max = 50)
    private String codigo;
    @NotBlank(message="La cantidad de Productos del item no debe estar vacio")
    @Positive
    private Integer stock;
    @NotBlank(message="El producto debe un precio")
    @Positive
    private double precio;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleFacturaModel> detallesFacturas;

}
