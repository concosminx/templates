package com.nimsoc.ps.api.repository;


import com.nimsoc.ps.api.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

  Payment findByOrderId(int orderId);
}
