package com.savindu.fullstackbackend.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could Not found the user with id "+id);
    }

}
