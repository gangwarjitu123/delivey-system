package com.dms.org.dto.response;

import com.dms.org.exception.ApiError;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String message;
    private Integer status;
    private String requestId;
    private T data;
    private ApiError error;
}
