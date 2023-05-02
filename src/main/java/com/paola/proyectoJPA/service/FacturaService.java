package com.paola.proyectoJPA.service;

import com.paola.proyectoJPA.excepcion.FacturaAlreadyExistsException;
import com.paola.proyectoJPA.excepcion.FacturaNotFoundException;
import com.paola.proyectoJPA.model.DetalleFacturaModel;
import com.paola.proyectoJPA.model.FacturaModel;
import com.paola.proyectoJPA.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class FacturaService {
    @Autowired
    private ProductoService productoService; // Agrega el servicio de Producto
    @Autowired
    private FacturaRepository facturaRepository;

    //calcular el total de la factura
    private Double calcularTotal(FacturaModel factura) {
        Double total = 0.0;
        for (DetalleFacturaModel detalle : factura.getDetalleFacturas()) {
            total += detalle.getPrecio() * detalle.getCantidad();
        }
        return total;
    }

    // Guardar una factura
    public FacturaModel create(FacturaModel newFactura) throws FacturaAlreadyExistsException {
        Optional<FacturaModel> facturaOp = this.facturaRepository.findById(newFactura.getId());

        if (facturaOp.isPresent()){ //si ya existe el producto
            log.info("La factura que intenta agregar ya existe en la base de datos : " + newFactura);
            throw new FacturaAlreadyExistsException("La factura que intenta agregar ya existe en la base de datos"); // lanza excepcion declarada en el paquete exception
        }else { //si la factura no existe
            // Obtener la fecha actual
            LocalDateTime fechaActual = LocalDateTime.now();
            // Convertir la fecha en formato date
            Date fechaHoraActualDate = Date.from(fechaActual.atZone(ZoneId.systemDefault()).toInstant());
            Double total = calcularTotal(newFactura);
            newFactura.setFcreacion(fechaHoraActualDate);
            newFactura.setTotal(total);
            return this.facturaRepository.save(newFactura); // ingresa un nuevo producto
        }
    }

    public FacturaModel findById(Integer id) throws Exception {
        if (id <= 0){
            throw new Exception("El id brindado no es valido.");
        }
        Optional<FacturaModel> facturaOp = this.facturaRepository.findById(id);

        if (facturaOp.isEmpty()){
            log.info("La factura con el id brindado no existe en la base de datos : " + id);
            throw new FacturaNotFoundException("La factura que intenta solicitar no existe");
        }else {
            return facturaOp.get();
        }
    }

    public List<FacturaModel> findAll(){
        return this.facturaRepository.findAll();
    }
}
