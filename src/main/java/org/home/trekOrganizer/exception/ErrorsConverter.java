package org.home.trekOrganizer.exception;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class ErrorsConverter {
    public static String getMessage(Errors errors){
        StringBuilder message = new StringBuilder();
        errors.getAllErrors().stream()
            .forEach(error ->{
//                       message.append( ((FieldError)error).getField() +": " );
                        message.append(error.getDefaultMessage()+ "| ");
                    });
        return message.toString();
    }

}
