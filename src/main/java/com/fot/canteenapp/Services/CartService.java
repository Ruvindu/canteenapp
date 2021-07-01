package com.fot.canteenapp.Services;

import com.fot.canteenapp.Entity.Cart;

import com.fot.canteenapp.Entity.InventoryAndCart;
import com.fot.canteenapp.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepo;

    public void saveItem(Cart cart){
        cartRepo.save(cart);
    }

}
