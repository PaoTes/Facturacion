package com.paola.proyectoJPA.controller;

import com.paola.proyectoJPA.excepcion.FacturaAlreadyExistsException;
import com.paola.proyectoJPA.model.ClienteModel;
import com.paola.proyectoJPA.model.FacturaModel;
import com.paola.proyectoJPA.repository.FacturaRepository;
import com.paola.proyectoJPA.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/factura")
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaService facturaService;

    @PostMapping(path = "/")
    public ResponseEntity<FacturaModel> create(@RequestBody FacturaModel factura) throws FacturaAlreadyExistsException {
        return new ResponseEntity<>(this.facturaService.create(factura), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        Optional<FacturaModel> facturaOptional = facturaRepository.findById(id);
        if (facturaOptional.isPresent()) {
            FacturaModel factura = facturaOptional.get();
            Map<String, Object> response = new HashMap<>();
            response.put("Factura N° ", factura.getId());
            response.put("Nombre ", factura.getCliente().getNombre());
            response.put("Apellido ", factura.getCliente().getApellido());
            response.put("Total ", factura.getTotal());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "La Factura N° " + id + " no existe");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<FacturaModel>> findAll() {
        return new ResponseEntity<>(this.facturaService.findAll(), HttpStatus.OK);
    }
}
