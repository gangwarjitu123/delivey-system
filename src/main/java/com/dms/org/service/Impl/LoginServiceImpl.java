package com.dms.org.service.Impl;

import com.dms.org.dto.request.LoginRequest;
import com.dms.org.dto.response.LoginResponse;
import com.dms.org.dto.response.Response;
import com.dms.org.entity.User;
import com.dms.org.exception.CustomDeliveryException;
import com.dms.org.repository.LoginRepository;
import com.dms.org.service.LoginService;
import com.dms.org.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public  Response authenticate(LoginRequest loginRequest) {
         User user= loginRepository.findByUserNameAndPassword(loginRequest.getUserName(),loginRequest.getPassword());
         if (ObjectUtils.isEmpty(user)){
             throw new CustomDeliveryException("invalid userName or password",HttpStatus.UNAUTHORIZED);
         }
       LoginResponse loginResponse= LoginResponse.builder()
                .token(createJwtToken(user))
                .userName(user.getUserName())
                .ts(LocalDateTime.now())
                .build();

         return  Response.builder()
                .message("login successfully")
                 .status(HttpStatus.OK.value())
                 .data(loginResponse)
                 .requestId(loginRequest.getRequestId())
                 .build();


    }
    private String createJwtToken(User user){
        try {
            return JwtUtils.createJWT(user.getName(),user.getUserType().getUserType(),user.getId());
        } catch (Exception e){
            throw new CustomDeliveryException("request peocessing failed ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
