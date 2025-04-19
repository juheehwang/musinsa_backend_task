package com.musinsa.task.exception;

import io.swagger.v3.oas.annotations.Hidden;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Hidden
@RestControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument( final HttpServletRequest request, IllegalArgumentException ex){
        LOGGER.error("IllegalArgumentException {}\n", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,String>> handleNotFoundException(
        final HttpServletRequest request, final NotFoundException ex) {
        LOGGER.error("NotFoundException {}\n", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<?> handleAllException(
        final HttpServletRequest request, final DuplicatedException ex){
        LOGGER.error("Exception {}\n", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
        final HttpServletRequest request, final MethodArgumentNotValidException ex) {
        LOGGER.error("Exception {}\n", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Map.of("error",
            ex.getBindingResult().getAllErrors().stream().map(
                DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining())
            )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException( final HttpServletRequest request, final Exception ex){
        LOGGER.error("Exception {}\n", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error","서버 오류가 발생했습니다."));
    }

}

