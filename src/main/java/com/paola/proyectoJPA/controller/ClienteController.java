package com.paola.proyectoJPA.controller;

import com.paola.proyectoJPA.excepcion.ClienteAlreadyExistsException;
import com.paola.proyectoJPA.model.ClienteModel;
import com.paola.proyectoJPA.repository.ClienteRepository;
import com.paola.proyectoJPA.service.ClienteService;
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
@RequestMapping(path = "api/cliente")

public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    @PostMapping(path = "/")
    public ResponseEntity<ClienteModel> create(@Valid @RequestBody ClienteModel cliente) throws ClienteAlreadyExistsException {
        return new ResponseEntity<>(this.clienteService.create(cliente), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClienteModel> update(@Valid @RequestBody ClienteModel cliente, @PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(this.clienteRepository.save(cliente), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        Optional<ClienteModel> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            ClienteModel cliente = clienteOptional.get();
            Map<String, Object> response = new HashMap<>();
            response.put("nombre", cliente.getNombre());
            response.put("apellido", cliente.getApellido());
            response.put("dni", cliente.getDni());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "El cliente con ID " + id + " no existe");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(path = "/")
    public ResponseEntity<List<ClienteModel>> findAll() {
        return new ResponseEntity<>(this.clienteRepository.findAll(), HttpStatus.OK);
    }
}


