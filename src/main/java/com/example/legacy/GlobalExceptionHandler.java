package com.example.legacy.exception;

import com.example.legacy.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst().orElse(ex.getMessage());
        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.BAD_REQUEST.value());
        er.setError("Validation Failed");
        er.setMessage(message);
        er.setPath(request.getRequestURI());
        logger.warn("Validation error: {}", message);
        return ResponseEntity.badRequest().body(er);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex, HttpServletRequest request) {
        logger.error("Unhandled exception", ex);
        ErrorResponse er = new ErrorResponse();
        er.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        er.setError("Internal Server Error");
        er.setMessage(ex.getMessage());
        er.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
    }
}
