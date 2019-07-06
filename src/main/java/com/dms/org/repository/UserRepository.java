package com.dms.org.repository;


import com.dms.org.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByIsActiveAndUserTypeUserType(Boolean isActive,String userType);
}
