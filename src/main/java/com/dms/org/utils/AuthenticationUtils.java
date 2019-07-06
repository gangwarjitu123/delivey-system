package com.dms.org.utils;

import com.dms.org.exception.CustomDeliveryException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class AuthenticationUtils {

    public JwtUser getJwtUserFromCurrentLogin(String authToken){
        if(authToken==null){
            throw new CustomDeliveryException("token can not be null", HttpStatus.UNAUTHORIZED);
        }
       return  JwtUtils.getJwtUser(authToken);
    }
}
