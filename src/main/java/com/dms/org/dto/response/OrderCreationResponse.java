package com.dms.org.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrderCreationResponse {
    private Long orderId;
    private Float orderPrice;
    private String orderAddress;
    private String orderStatus;
}
