package com.dms.org.controller;

import com.dms.org.dto.response.Response;
import com.dms.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @GetMapping("/get-list")
    public ResponseEntity<Response> getUserStatusByUserType(@RequestParam(value = "userType",required = true)
                                                            String userType){
        Response response=userService.getUserByUserType(userType);
        return sendResponse(response);
    }
}
