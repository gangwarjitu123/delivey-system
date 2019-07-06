package com.dms.org.controller;

import com.dms.org.dto.request.LoginRequest;
import com.dms.org.dto.response.Response;
import com.dms.org.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
public class LoginController  extends BaseController  {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Response> authenticate(@Valid  @RequestBody LoginRequest loginRequest){

         return sendResponse(loginService.authenticate(loginRequest));
    }
}
