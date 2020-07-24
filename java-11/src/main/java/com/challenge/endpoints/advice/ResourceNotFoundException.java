package com.challenge.endpoints.advice;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String ResourceName) {
        super("Resource: " + ResourceName + " not found!");
    }
}