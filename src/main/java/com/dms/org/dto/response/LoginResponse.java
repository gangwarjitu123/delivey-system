package com.dms.org.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class LoginResponse {
    private String userName;
    private String token;
    private LocalDateTime ts;
}
