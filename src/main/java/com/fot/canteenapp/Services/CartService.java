package com.fot.canteenapp.Services;

import com.fot.canteenapp.Entity.Cart;

import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Entity.ViewCart;
import com.fot.canteenapp.Repository.CartRepository;
import com.fot.canteenapp.Repository.ViewCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private ViewCartRepository viewcartRepo;

    public void saveItem(Cart cart){
        cartRepo.save(cart);
    }

    public List<ViewCart> viewCart(Integer user_id){
        return viewcartRepo.veiwMyCart(user_id);
    }

    public void deleteCart(Integer id){
        cartRepo.deleteById(id);
    }

}
