package com.dms.org.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Request {
    private String requestId;
    public Request(){
        requestId= UUID.randomUUID().toString();
    }
}
