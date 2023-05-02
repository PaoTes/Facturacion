package com.paola.proyectoJPA.service;

import com.paola.proyectoJPA.excepcion.ClienteAlreadyExistsException;
import com.paola.proyectoJPA.excepcion.ClienteNotFoundException;
import com.paola.proyectoJPA.model.ClienteModel;
import com.paola.proyectoJPA.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ClienteService {
    // autowired (inyeccion de dependencias) levanta una sola instancia para toda la aplicacion
    @Autowired
    private ClienteRepository clienteRepository;

    // Guardar un cliente
    public ClienteModel create(ClienteModel newCliente) throws ClienteAlreadyExistsException {
        Optional<ClienteModel> clienteOp = this.clienteRepository.findByDni(newCliente.getDni());

        if (clienteOp.isPresent()){ //si ya existe el cliente
            log.info("El cliente que intenta agregar ya existe en la base de datos : " + newCliente);
            throw new ClienteAlreadyExistsException("El cliente ya existe en la base de datos"); // lanza excepcion declarada en el paquete exception
        }else { //si el cliente no existe
            return this.clienteRepository.save(newCliente); // ingresa un nuevo cliente
        }
    }
    // Actualizar cliente
    public ClienteModel update(ClienteModel newCliente, Integer id) throws Exception {
        log.info("ID INGRESANDO : " + id);
        if (id <= 0){
            throw new Exception("El id brindado no es valido");
        }

        Optional<ClienteModel> clienteOp = this.clienteRepository.findById(id); // buscar  por id

        if (clienteOp.isEmpty()){ // si no existe

            log.info("El cliente que intenta modificar no existe en la base de datos : " + newCliente);
            throw new ClienteNotFoundException("El producto que intenta modificar no existe en la base de datos");
        }else {
            log.info("el producto fue encontrado");
            ClienteModel clienteBd = clienteOp.get();
            clienteBd.setNombre(newCliente.getNombre());
            clienteBd.setApellido(newCliente.getApellido());
            clienteBd.setDni(newCliente.getDni());


            log.info("producto actualizado : " + clienteBd);

            return this.clienteRepository.save(clienteBd);// guardar el producto actualizado
        }
    }

    public ClienteModel findById(Integer id) throws Exception {
        if (id <= 0){
            throw new Exception("El id brindado no es valido.");
        }

        Optional<ClienteModel> clienteOp = this.clienteRepository.findById(id);

        if (clienteOp.isEmpty()){
            log.info("El cliente id brindado no existe en la base de datos : " + id);
            throw new ClienteNotFoundException("El producto que intenta solicitar no existe");
        }else {
            return clienteOp.get();
        }
    }
    public List<ClienteModel> findAll() {
        return this.clienteRepository.findAll();
    }
}
