package com.dms.org.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LoginRequest extends Request {
    @NotBlank(message = "userName can not be null or blank")
    private String userName;
    @NotBlank(message = "password can not be blank or null")
    private String password;
}
