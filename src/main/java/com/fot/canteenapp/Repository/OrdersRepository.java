package com.fot.canteenapp.Repository;

import com.fot.canteenapp.Entity.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.query.Procedure;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT * FROM `orders` ORDER BY `order_id` DESC LIMIT 1",nativeQuery = true)
    List<Orders> findLastOrder();

    @Query(value = "{call GET_ORDERS_WITH_ITEM}", nativeQuery = true)
    public List<Orders> findAllWithItemName();


}
