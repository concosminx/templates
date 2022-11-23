package com.nimsoc.os.api.repository;

import com.nimsoc.os.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
