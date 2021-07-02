package com.fot.canteenapp.Repository;

import com.fot.canteenapp.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders,Integer> {

    @Modifying
    @Query(value = "UPDATE `orders` SET `status`=2 WHERE `order_id` = :orderid",nativeQuery = true)
    @Transactional
    void updatePayedOrder(@Param(value = "orderid")Integer orderid);

    @Query(value = "SELECT * FROM `orders` ORDER BY `order_id` DESC LIMIT 1",nativeQuery = true)
    List<Orders> findLastOrder();

}
