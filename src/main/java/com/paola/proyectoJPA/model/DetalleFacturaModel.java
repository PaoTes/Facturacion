package com.paola.proyectoJPA.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Component
@Data
@Entity
@Table(name = "detallefacturas")
public class DetalleFacturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "dfacturas_id")
    private Integer id;
    @NotBlank(message="La cantidad de Productos del item no debe estar vacio")
    @NotNull(message="La cantidad de Productos del item no debe estar vacio")
    @Min(0)
    private Integer cantidad;
    @NotBlank(message="La cantidad del Producto no debe estar vacio")
    @NotNull(message="La cantidad del Producto no debe estar vacio")
    @Positive
    private double precio;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private FacturaModel factura;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoModel producto;

}
