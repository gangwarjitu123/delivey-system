package com.dms.org.service;

import com.dms.org.dto.request.LoginRequest;
import com.dms.org.dto.response.Response;

public interface LoginService {

     Response authenticate(LoginRequest loginRequest);
}
