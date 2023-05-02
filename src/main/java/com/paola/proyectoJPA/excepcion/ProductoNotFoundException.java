package com.paola.proyectoJPA.excepcion;

public class ProductoNotFoundException extends Exception{

    public ProductoNotFoundException(String msg){
        super(msg);
    }
}