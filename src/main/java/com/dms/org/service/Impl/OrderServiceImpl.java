package com.dms.org.service.Impl;
import com.dms.org.dto.request.OrderItems;
import com.dms.org.dto.request.OrderRequest;
import com.dms.org.dto.response.OrderCreationResponse;
import com.dms.org.dto.response.Response;
import com.dms.org.entity.*;
import com.dms.org.exception.CustomDeliveryException;
import com.dms.org.repository.MenuItemRepository;
import com.dms.org.repository.OrderRepository;
import com.dms.org.repository.OrderStatusRepository;
import com.dms.org.service.OrderService;
import com.dms.org.utils.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
   @Autowired
   private  MenuItemRepository menuItemRepository;
   @Autowired
   private OrderRepository orderRepository;
   @Autowired
   private OrderStatusRepository orderStatusRespository;
    @Override
    @Transactional
    public Response createOrder(OrderRequest orderRequest, JwtUser jwtUser) {
        List<Long> menuIds=orderRequest.getOrderItemsList().stream().map(orderItems -> orderItems.getMenuItemId()).collect(Collectors.toList());
        List<MenuItem> menuItemList=menuItemRepository.findByIdIn(menuIds);
        Order order=createOrderWithItem(orderRequest,menuItemList,jwtUser);
        try{
            order=orderRepository.save(order);
        }catch (Exception e){
            e.printStackTrace();
            log.error("order creation failed for user ",jwtUser.getUserId());
            throw  new CustomDeliveryException("order creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        OrderCreationResponse orderCreationResponse= OrderCreationResponse.builder()
                .orderId(order.getId())
                .orderPrice(order.getPrice())
                .orderAddress(order.getDeliveryAddress())
                .build();
        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("order created successfully . a delivery executive  will be assigned shortly to your order ")
                .data(orderCreationResponse)
                .requestId(orderRequest.getRequestId())
                .build();
    }

    @Override
    public Response updateOrderStatus(String status, Long orderId, JwtUser jwtUser) {
        Optional<Order> orderOptional=orderRepository.findById(orderId);
        Order order=orderOptional.orElseThrow(()-> new CustomDeliveryException("invalid order id",HttpStatus.BAD_REQUEST));
        OrderStatus orderStatus=orderStatusRespository.findByOrderStatus(status);
        if(orderStatus==null){
            throw new CustomDeliveryException("invalid order status",HttpStatus.BAD_REQUEST);
        }
        order.setOrderStatus(orderStatus);
        try{
            order=orderRepository.save(order);
        }catch (Exception e){
            e.printStackTrace();
            log.error("order creation failed for user ",jwtUser.getUserId());
            throw  new CustomDeliveryException("order creation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        OrderCreationResponse orderCreationResponse= OrderCreationResponse.builder()
                .orderId(order.getId())
                .orderPrice(order.getPrice())
                .orderAddress(order.getDeliveryAddress())
                .orderStatus(order.getOrderStatus().getOrderStatus())
                .build();
        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("order status updated successfully")
                .data(orderCreationResponse)
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @Override
    public Response getOrderStatus(Long orderId, JwtUser jwtUser) {
        Optional<Order> orderOptional=orderRepository.findById(orderId);
        Order order=orderOptional.orElseThrow(()-> new CustomDeliveryException("invalid order id",HttpStatus.BAD_REQUEST));
        Optional<OrderStatus> orderStatusOptional=orderStatusRespository.findById(order.getOrderStatus().getId());
        OrderCreationResponse orderCreationResponse= OrderCreationResponse.builder()
                .orderStatus(orderStatusOptional.get().getOrderStatus())
                .orderId(orderId)
                .build();
        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("order status fetched successfully")
                .data(orderCreationResponse)
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    private Order createOrderWithItem(OrderRequest orderRequest,List<MenuItem> menuItemList,JwtUser jwtUser){
       Order order = new Order();
       order.setComment(orderRequest.getComment());
       order.setDeliveryAddress(orderRequest.getDeliverAddress());
       User user = new User();
       user.setId(jwtUser.getUserId());
       order.setCustomerId(user);
       order.setDiscount(0f);
       order.setDeliveryAddressNote(orderRequest.getDeliveryAddressNote());
       order.setCreatedAt(LocalDateTime.now());
       addItemToOrder(order,menuItemList,orderRequest.getOrderItemsList());
       Optional<OrderStatus> orderStatus = orderStatusRespository.findById(1l);
       order.setOrderStatus(orderStatus.get());
       return order;
    }

    private void addItemToOrder(Order order, List<MenuItem> menuItemList, List<OrderItems> orderItemsList){
        Map<Long, MenuItem> menuItemMap = menuItemList.stream()
                .collect( Collectors.toMap(MenuItem::getId,
                        Function.identity()));

        List<OrderItem> orderItemList = new ArrayList<>();
        Float finalPrice=0f;
        for (OrderItems orderItem:orderItemsList) {
             OrderItem item= new OrderItem();
             item.setCreatedAt(LocalDateTime.now());
             MenuItem menuItem= menuItemMap.get(orderItem.getMenuItemId());
             item.setMenuItemId(menuItem.getId());
             item.setPrice(orderItem.getQuantity()*menuItem.getPrice());
             item.setOrder(order);
             item.setQuantity(orderItem.getQuantity());
             orderItemList.add(item);
             finalPrice=finalPrice+orderItem.getQuantity()*menuItem.getPrice();
        }
        order.setPrice(finalPrice);
        order.setOrderItem(orderItemList);
    }
}
