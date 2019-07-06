package com.dms.org.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
public class CustomDeliveryException extends RuntimeException {

    private List<String> fieldNames;
    private HttpStatus httpStatus;
    private String message;

    public CustomDeliveryException(List<String> fieldNames, String message,HttpStatus httpStatus) {
        this.fieldNames = fieldNames;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public CustomDeliveryException(String message,HttpStatus httpStatus){
        this(Collections.emptyList(),message,httpStatus);
    }

    public CustomDeliveryException(String message){
        this(message,HttpStatus.BAD_REQUEST);
    }

}
