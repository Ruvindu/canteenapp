package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.Cart;
import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CartController {

    @Autowired
    private CartService cartser;

    @PostMapping("/addcart")
    public String addCart(@RequestBody Cart cart){
        cartser.saveItem(cart);
        return "Item added to cart";
    }

}
