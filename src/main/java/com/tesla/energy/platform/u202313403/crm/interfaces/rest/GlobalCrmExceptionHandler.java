package com.tesla.energy.platform.u202313403.crm.interfaces.rest;

import com.tesla.energy.platform.u202313403.crm.domain.model.exceptions.DuplicateCustomConfigException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.tesla.energy.platform.u202313403.crm")
public class GlobalCrmExceptionHandler {

    @ExceptionHandler(DuplicateCustomConfigException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateCustomConfigException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

