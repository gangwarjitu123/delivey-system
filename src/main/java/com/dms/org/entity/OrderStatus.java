package com.dms.org.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "order_status")
@Getter
@Setter
public class OrderStatus extends BaseEntity {

   @Column(name = "status")
   private String orderStatus;
}
