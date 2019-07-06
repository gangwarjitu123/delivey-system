package com.dms.org.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity {

    @OneToMany(mappedBy = "customerId",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> order;

    @Column(name = "name")
    private String name;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @JoinColumn(name = "user_type_id")
    @OneToOne
    private UserType userType;

    @JoinColumn(name = "is_active")
    private Boolean isActive;


}
