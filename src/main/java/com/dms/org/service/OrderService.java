package com.dms.org.service;

import com.dms.org.dto.request.OrderRequest;
import com.dms.org.dto.response.Response;
import com.dms.org.utils.JwtUser;

public interface OrderService {
    Response createOrder(OrderRequest orderRequest, JwtUser jwtUser);
    Response updateOrderStatus(String status,Long orderId,JwtUser jwtUser);
    Response getOrderStatus(Long orderId,JwtUser jwtUser);
}
