package org.home.trekOrganizer.exception;

public class TrekkerNotFoundException extends RuntimeException{

    public TrekkerNotFoundException(Long id) {
        super(String.format("Trekker with id = %s not found", id));

    }
}
