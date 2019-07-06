package com.dms.org.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderRequest extends Request {

    @NotBlank(message ="delivery address can not blank or null" )
    private String deliverAddress;
    private String comment;
    private String deliveryAddressNote;

    @NotBlank(message ="orderItemsList can not blank or null" )
    private List<OrderItems> orderItemsList;

}
