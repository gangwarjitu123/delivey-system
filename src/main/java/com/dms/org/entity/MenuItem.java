package com.dms.org.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "menu_item")
@Getter
@Setter
public class MenuItem extends BaseEntity {

    @Column(name = "item_name")
    private String itemName;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "active")
    private boolean active;
}
