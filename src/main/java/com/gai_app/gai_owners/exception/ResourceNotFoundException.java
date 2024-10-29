package com.gai_app.gai_owners.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String description) {
        super(description);
    }
}
