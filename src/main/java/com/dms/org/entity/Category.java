package com.dms.org.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

     @Column(name = "category_name")
     private String name;
}
