package com.paola.proyectoJPA.excepcion;

public class ProductoAlreadyExistsException extends Exception{

    public ProductoAlreadyExistsException(String msg){
        super(msg);
    }
}
