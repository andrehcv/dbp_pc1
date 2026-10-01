package com.utec.pc1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String msg) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", msg);
        return ResponseEntity.status(status).body(body);
    }
    //PARA STORE
    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity <Map<String, Object>> handleNotFound (StoreNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    //PARA PRODUCT
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity <Map<String, Object>> handleNotFound (ProductNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity <Map<String, Object>> handleConflict(InsufficientStockException ex) {
        return error(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity <Map<String, Object>> handleConflict(UserAlreadyExistsException ex) {
        return error(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    public ResponseEntity <Map<String, Object>> handleConflict(ProductNotAvailableException ex) {
        return error(HttpStatus.CONFLICT, ex.getMessage());
    }


    @ExceptionHandler(ForbiddenStoreActionException.class)
    public ResponseEntity <Map<String, Object>>hanfleForbidden(ForbiddenStoreActionException ex) {
        return error(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler (InvalidCredentialsException.class)
    public ResponseEntity <Map<String, Object>>handleGeneral(Exception ex) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

}

