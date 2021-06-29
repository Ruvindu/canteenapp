package com.fot.canteenapp.Repository;

import com.fot.canteenapp.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
