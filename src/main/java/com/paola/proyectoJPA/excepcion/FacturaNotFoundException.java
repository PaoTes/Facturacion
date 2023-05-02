package com.paola.proyectoJPA.excepcion;

public class FacturaNotFoundException extends Exception{
    public FacturaNotFoundException(String msg){
        super(msg);
    }
}
