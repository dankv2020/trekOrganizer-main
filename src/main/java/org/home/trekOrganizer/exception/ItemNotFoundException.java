package org.home.trekOrganizer.exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String item, Long id) {
        super(item + String.format(" with id = %s not found", id));
    }
}
