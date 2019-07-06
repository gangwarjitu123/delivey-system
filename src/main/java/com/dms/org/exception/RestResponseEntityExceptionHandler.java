package com.dms.org.exception;

import com.dms.org.dto.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The type Rest response entity exception handler.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors=ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
       ApiError apiError= ApiError.builder()
                .errorMessage(errors)
                .debugMessage(ex.getLocalizedMessage())
                .build();
        Response response=Response.builder()
                .error(apiError)
                .status(0)
                .build();
        return new ResponseEntity<>(response,status);
    }


    /**
     * Custom exception response entity.
     *
     *
     * @return the response entity
     */
    @ExceptionHandler(CustomDeliveryException.class)
    public ResponseEntity<Object> customException(CustomDeliveryException ex){
        ApiError apiError= ApiError.builder()
                .errorMessage(Arrays.asList(ex.getMessage()))
                .debugMessage(ex.getMessage())
                .build();
        Response response=Response.builder()
                .error(apiError)
                .status(ex.getHttpStatus().value())
                .build();
        return new ResponseEntity<>(response,ex.getHttpStatus());
    }
}