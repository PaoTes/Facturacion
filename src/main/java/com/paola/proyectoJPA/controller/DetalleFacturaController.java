package com.paola.proyectoJPA.controller;

import com.paola.proyectoJPA.model.DetalleFacturaModel;
import com.paola.proyectoJPA.service.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallefactura")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @PostMapping("/agregar")
    public void agregarDetalleFactura(@RequestBody DetalleFacturaModel detalleFactura) {
        detalleFacturaService.agregarDetalleFactura(detalleFactura);
    }

    @GetMapping("/factura/{facturaId}")
    public List<DetalleFacturaModel> obtenerDetallesFactura(@PathVariable int facturaId) {
        return detalleFacturaService.obtenerDetallesFactura(facturaId);
    }

}
