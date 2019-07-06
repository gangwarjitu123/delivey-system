package com.dms.org.repository;

import com.dms.org.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository  extends JpaRepository<OrderStatus,Long> {

    OrderStatus findByOrderStatus(String orderStatus);

}
