package com.example.restBasic.handler;

import com.example.restBasic.exceptions.ResourceNotFoundException;
import com.example.restBasic.exceptions.details.ResourceValidationDetails;
import com.example.restBasic.exceptions.details.ResourcesDetails;
import com.example.restBasic.exceptions.details.ResourcesNotFoundDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        final ResourcesNotFoundDetails build = new ResourcesNotFoundDetails();
        build.setDetail(resourceNotFoundException.getMessage());
        build.setTimeStamp(LocalDateTime.now());
        build.setDeveloperMessage(resourceNotFoundException.getClass().getName());
        build.setTitle("Resource Not Found !");
        build.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ResourceValidationDetails build = new ResourceValidationDetails();
        build.setDetail(methodArgumentNotValidException.getMessage());
        build.setTimeStamp(LocalDateTime.now());
        build.setDeveloperMessage(methodArgumentNotValidException.getClass().getName());
        build.setTitle("Bad Request !");
        build.setStatus(HttpStatus.BAD_REQUEST.value());
        build.setErros(getErros(methodArgumentNotValidException.getBindingResult()));

        return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        final ResourcesDetails build = new ResourcesDetails();
        build.setDetail(ex.getMessage());
        build.setTimeStamp(LocalDateTime.now());
        build.setDeveloperMessage(ex.getClass().getName());
        build.setTitle("Internal Exception !");
        build.setStatus(status.value());

        return new ResponseEntity<>(build, status);
    }

    private Map<String, String> getErros(BindingResult bindingResult) {
        Map<String, String> erros = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return erros;
    }


}
