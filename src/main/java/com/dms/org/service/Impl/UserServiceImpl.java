package com.dms.org.service.Impl;

import com.dms.org.dto.response.Response;
import com.dms.org.entity.User;
import com.dms.org.exception.CustomDeliveryException;
import com.dms.org.repository.UserRepository;
import com.dms.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Response getUserByUserType(String userType) {
        List<User> userList=userRepository.findByIsActiveAndUserTypeUserType(true,userType);
        if(CollectionUtils.isEmpty(userList)){
            throw new CustomDeliveryException("no active user found for type :"+ userType, HttpStatus.NOT_FOUND);
        }
        return Response.builder()
                .data(userList)
                .message("user fetch successfully")
                .status(HttpStatus.OK.value())
                .build();

    }
}
