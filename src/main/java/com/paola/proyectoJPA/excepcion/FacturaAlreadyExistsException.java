package com.paola.proyectoJPA.excepcion;

public class FacturaAlreadyExistsException extends Exception {
    public FacturaAlreadyExistsException(String msg){
        super(msg);
    }
}
