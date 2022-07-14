package com.tenpo.adder.exception;

import com.tenpo.adder.exception.dto.DetailErrors;
import com.tenpo.adder.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private HistoryService historyService;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetailErrors> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        final String uri = webRequest.getDescription(false);
        DetailErrors detailErrors = new DetailErrors(new Date(),exception.getMessage(), uri);

        this.historyService.createHistory(uri);
        return new ResponseEntity<>(detailErrors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<DetailErrors> handleAuthenticationException(AuthenticationException exception, WebRequest webRequest){
        final String uri = webRequest.getDescription(false);
        DetailErrors detailErrors = new DetailErrors(new Date(),exception.getMessage(), uri);

        this.historyService.createHistory(uri);
        return new ResponseEntity<>(detailErrors, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<DetailErrors> handleAccessDeniedException(AccessDeniedException exception, WebRequest webRequest){
        final String uri = webRequest.getDescription(false);
        DetailErrors detailErrors = new DetailErrors(new Date(),exception.getMessage(), uri);

        this.historyService.createHistory(uri);
        return new ResponseEntity<>(detailErrors, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetailErrors> handleGlobalException(Exception exception, WebRequest webRequest){
        final String uri = webRequest.getDescription(false);
        DetailErrors detailErrors = new DetailErrors(new Date(),exception.getMessage(), uri);

        this.historyService.createHistory(uri);
        return new ResponseEntity<>(detailErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        this.historyService.createHistory(request.getDescription(false));

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
