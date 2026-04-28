package com.example.project.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
public ResponseEntity<?> handleNotFound(NotFoundException ex) {
    return ResponseEntity.status(404).body(ex.getMessage());
}

@ExceptionHandler(ForbiddenException.class)
public ResponseEntity<?> handleForbidden(ForbiddenException ex) {
    return ResponseEntity.status(403).body(ex.getMessage());
}


}