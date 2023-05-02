package com.paola.proyectoJPA.controller;

import com.paola.proyectoJPA.excepcion.ProductoAlreadyExistsException;
import com.paola.proyectoJPA.model.ProductoModel;
import com.paola.proyectoJPA.repository.ProductoRepository;
import com.paola.proyectoJPA.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoService productoService;

    @PostMapping(path = "/")
    public ResponseEntity<ProductoModel> create(@Valid @RequestBody ProductoModel producto) throws ProductoAlreadyExistsException {
        return new ResponseEntity<>(this.productoService.create(producto), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductoModel> update(@Valid @RequestBody ProductoModel producto, @PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(this.productoService.update(producto,id), HttpStatus.OK);
    }

    /*@GetMapping(path = "/{id}")
    public ResponseEntity<ProductoModel> findById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(this.productoService.findById(id), HttpStatus.OK);
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findByCodigo(@PathVariable String codigo) {
        Optional<ProductoModel> productoOptional = productoRepository.findByCodigo(codigo);
        if (productoOptional.isPresent()) {
            ProductoModel producto = productoOptional.get();
            Map<String, Object> response = new HashMap<>();
            response.put("Codigo ", producto.getCodigo());
            response.put("Descripción ", producto.getDescripcion());
            response.put("En existencia ", producto.getStock());
            response.put("Precio ", producto.getPrecio());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "El producto " + codigo + " no existe");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<ProductoModel>> findAll(){
        return new ResponseEntity<>(this.productoService.findAll(), HttpStatus.OK);
    }
}


