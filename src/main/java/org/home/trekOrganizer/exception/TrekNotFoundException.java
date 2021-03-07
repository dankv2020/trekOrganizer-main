package org.home.trekOrganizer.exception;

public class TrekNotFoundException extends RuntimeException{

    public TrekNotFoundException(Long id) {
        super(String.format("Trek with id = %s not found", id));
    }

}
