package com.example.sucursalapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BranchOfficeException extends Exception {

    public BranchOfficeException(String message){
        super(message);
    }
}
