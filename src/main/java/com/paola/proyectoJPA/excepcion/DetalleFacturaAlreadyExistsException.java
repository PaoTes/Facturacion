package com.paola.proyectoJPA.excepcion;

public class DetalleFacturaAlreadyExistsException extends Exception{
    public DetalleFacturaAlreadyExistsException(String msg){
        super(msg);
    }
}
