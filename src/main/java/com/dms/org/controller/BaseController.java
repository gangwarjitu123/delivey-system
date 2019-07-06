package com.dms.org.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

public class BaseController {

    public <T> ResponseEntity<T> sendResponse(T t, HttpStatus httpStatus, HttpHeaders httpHeaders){
         if(ObjectUtils.isEmpty(t)){
             throw new RuntimeException();
         }
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(t);
    }


    public <T> ResponseEntity<T> sendResponse(T t, HttpStatus httpStatus){

       HttpHeaders httpHeaders = new HttpHeaders();
       httpHeaders.setContentType(MediaType.APPLICATION_JSON);
       return sendResponse(t,httpStatus,httpHeaders);
    }

    public <T> ResponseEntity<T> sendResponse(T t){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return sendResponse(t,HttpStatus.OK,httpHeaders);
    }
}
