package com.fot.canteenapp.Repository;

import com.fot.canteenapp.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders,Integer> {
}
