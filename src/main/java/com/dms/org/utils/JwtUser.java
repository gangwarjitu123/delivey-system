package com.dms.org.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtUser{

        private String userName;
        private Long userId;
        private String userType;
    }