package com.dms.org.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrderItems {
    private String comment;
    @NotBlank(message = "menuItemId can not be blank or null")
    private Long menuItemId;
    @NotBlank(message = "quantity can not be blank or null")
    @Min(value = 1,message = "at least select one quantity for selected item ")
    private Integer quantity;
}
