package com.paola.proyectoJPA.service;


import com.paola.proyectoJPA.excepcion.ProductoNotFoundException;
import com.paola.proyectoJPA.excepcion.ProductoAlreadyExistsException;
import com.paola.proyectoJPA.model.ProductoModel;
import com.paola.proyectoJPA.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductoService {
    // autowired (inyeccion de dependencias) levanta una sola instancia para toda la aplicacion
    @Autowired
    private ProductoRepository productoRepository;

    // Guardar un producto
    public ProductoModel create(ProductoModel newProducto) throws ProductoAlreadyExistsException {
        Optional<ProductoModel> productoOp = this.productoRepository.findByCodigo(newProducto.getCodigo());

        if (productoOp.isPresent()){ //si ya existe el producto
            log.info("El producto que intenta agregar ya existe en la base de datos : " + newProducto);
            throw new ProductoAlreadyExistsException("El producto que intenta agregar ya existe en la base de datos"); // lanza excepcion declarada en el paquete exception
        }else { //si el producto no existe
            return this.productoRepository.save(newProducto); // ingresa un nuevo producto
        }
    }
    // Actualizar producto
    public ProductoModel update(ProductoModel newProducto, Integer id) throws Exception {
        log.info("ID INGRESANDO : " + id);
        if (id <= 0){
            throw new Exception("El id brindado no es valido");
        }

        Optional<ProductoModel> productoOp = this.productoRepository.findById(id);

        if (productoOp.isEmpty()){ // si el producto no existe
            log.info("El producto que intenta modificar no existe en la base de datos : " + newProducto);
            throw new ProductoNotFoundException("El producto que intenta modificar no existe en la base de datos");
        }else {
            log.info("el producto fue encontrado");
            ProductoModel productBd = productoOp.get();
            productBd.setCodigo(newProducto.getCodigo());
            productBd.setDescripcion(newProducto.getDescripcion());
            productBd.setPrecio(newProducto.getPrecio());
            productBd.setStock(newProducto.getStock());

            log.info("producto actualizado : " + productBd);

            return this.productoRepository.save(productBd);// guardar el producto actualizado
        }
    }

    public ProductoModel findById(Integer id) throws Exception {
        if (id <= 0){
            throw new Exception("El id brindado no es valido.");
        }

        Optional<ProductoModel> productoOp = this.productoRepository.findById(id);

        if (productoOp.isEmpty()){
            log.info("El producto con el id brindado no existe en la base de datos : " + id);
            throw new ProductoNotFoundException("El producto que intenta solicitar no existe");
        }else {
            return productoOp.get();
        }
    }

    public List<ProductoModel> findAll(){
        return this.productoRepository.findAll();
    }

    public void actualizarProducto(ProductoModel producto) {
    }
}
