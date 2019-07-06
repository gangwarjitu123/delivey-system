package com.dms.org.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ApiError  {
    private  String debugMessage;
    private List<String> errorMessage;
}
