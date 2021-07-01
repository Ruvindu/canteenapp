package com.fot.canteenapp.Repository;

import com.fot.canteenapp.Entity.InventoryAndCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface InventoryAndCartRepository extends JpaRepository<InventoryAndCart,Integer> {

    @Modifying
    @Query(value = "{call get_my_cart(:uid)}", nativeQuery = true)
    @Transactional
    List<InventoryAndCart> get_my_cart(@Param(value = "uid") Integer uid);

}
