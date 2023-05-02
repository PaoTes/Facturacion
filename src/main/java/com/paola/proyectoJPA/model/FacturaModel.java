package com.paola.proyectoJPA.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name ="facturas")
public class FacturaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fcreacion;
    @NotNull
    @NotBlank
    private double total;


    //Relacion muchos a uno con la tabla clientes
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    //Relacion uno a muchos con la tabla detallefacturas
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<DetalleFacturaModel> detalleFacturas;


}
