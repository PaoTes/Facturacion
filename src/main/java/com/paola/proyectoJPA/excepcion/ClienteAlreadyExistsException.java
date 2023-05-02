package com.paola.proyectoJPA.excepcion;

public class ClienteAlreadyExistsException extends Exception{
    public ClienteAlreadyExistsException(String msg){
        super(msg);
    }
}
