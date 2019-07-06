package com.dms.org.repository;

import com.dms.org.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<User, Long> {

    User findByUserNameAndPassword(String userName,String password);
}
