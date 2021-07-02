package com.fot.canteenapp.Repository;

import com.fot.canteenapp.Entity.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders,Integer> {

    @Query(value = "Select * from orders limit 1",nativeQuery = true)
    List<Orders> findLastOrder();

}
