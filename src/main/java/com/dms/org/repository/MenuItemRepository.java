package com.dms.org.repository;

import com.dms.org.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    List<MenuItem> findByIdIn(List<Long> inventoryIdList);
}
