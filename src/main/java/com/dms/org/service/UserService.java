package com.dms.org.service;

import com.dms.org.dto.response.Response;

public interface UserService {
    Response getUserByUserType(String userType);
}
