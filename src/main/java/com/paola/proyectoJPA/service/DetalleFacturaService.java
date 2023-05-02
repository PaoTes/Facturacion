package com.paola.proyectoJPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paola.proyectoJPA.dao.DetalleFacturaDAO;
import com.paola.proyectoJPA.model.DetalleFacturaModel;
import com.paola.proyectoJPA.model.ProductoModel;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaDAO detalleFacturaDAO;

    @Autowired
    private ProductoService productoService;

    public void agregarDetalleFactura(DetalleFacturaModel detalleFactura) {
        detalleFacturaDAO.agregarDetalleFactura(detalleFactura);
        ProductoModel producto = detalleFactura.getProducto();
        int cantidad = detalleFactura.getCantidad();
        producto.setStock(producto.getStock() - cantidad);
        productoService.actualizarProducto(producto);
    }

    public List<DetalleFacturaModel> obtenerDetallesFactura(int facturaId) {
        return detalleFacturaDAO.obtenerDetallesFactura(facturaId);
    }

}
