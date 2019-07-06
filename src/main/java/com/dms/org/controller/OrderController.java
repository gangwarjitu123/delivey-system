package com.dms.org.controller;

import com.dms.org.dto.request.OrderRequest;
import com.dms.org.dto.response.Response;
import com.dms.org.exception.CustomDeliveryException;
import com.dms.org.service.OrderService;
import com.dms.org.utils.AuthenticationUtils;
import com.dms.org.utils.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<Response> createOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("auth-token") String authToken){
        JwtUser jwtUser=AuthenticationUtils.getJwtUserFromCurrentLogin(authToken);
        Response response=orderService.createOrder(orderRequest,jwtUser);
        return sendResponse(response);
    }
    @PatchMapping("/update")
    public ResponseEntity<Response> updateOrderStatus(@RequestParam(value = "orderStatus",required = true) String orderStatus,
                                                      @RequestParam(value = "orderId",required = true) Long orderId,
                                                      @RequestHeader("auth-token") String authToken){
        if(orderStatus==null || orderId==null)
        {
            throw new CustomDeliveryException("orderId or orderStatus is missing", HttpStatus.BAD_REQUEST);
        }
        JwtUser jwtUser=AuthenticationUtils.getJwtUserFromCurrentLogin(authToken);
        return sendResponse(orderService.updateOrderStatus(orderStatus,orderId,jwtUser));
    }

    @GetMapping("/getOrderStatus")
    public ResponseEntity<Response> getOrderStatus(@RequestParam(value = "orderId",required = true) Long orderId,
                                                   @RequestHeader("auth-token") String authToken){
        if(authToken==null || orderId==null)
        {
            throw new CustomDeliveryException("orderId or authToken is missing", HttpStatus.BAD_REQUEST);
        }
        JwtUser jwtUser=AuthenticationUtils.getJwtUserFromCurrentLogin(authToken);

        return sendResponse(orderService.getOrderStatus(orderId,jwtUser));
    }

}
