package com.fot.canteenapp.Repository;



import com.fot.canteenapp.Entity.ViewCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface ViewCartRepository extends JpaRepository<com.fot.canteenapp.Entity.ViewCart,Integer> {

    @Modifying
    @Query(value = "SELECT * FROM `view_cart` WHERE `user_id` = :userId",nativeQuery = true)
    @Transactional
    List<ViewCart> veiwMyCart(@Param(value = "userId")Integer userId);

}
