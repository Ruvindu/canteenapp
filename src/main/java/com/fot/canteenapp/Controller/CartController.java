package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.Cart;
import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Services.CartService;
import com.fot.canteenapp.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private CartService cartser;

    @RequestMapping(method=RequestMethod.GET, value="/addcart")
    public String addCart(@RequestParam(value = "user") Integer userid, @RequestParam(value = "item") Integer itemid,HttpSession session){
        //user authenticate
        if (!auth.getAuth().is_user(session))
            return "redirect:/signin";

        Cart newcartitem = new Cart();
        newcartitem.setUserId(userid);
        newcartitem.setItemId(itemid);
        cartser.saveItem(newcartitem);
        return "redirect:/cart";
    }

    @GetMapping(path = "/cart")
    public String navigatetocart(HttpSession session) {
        //user authenticate
        if (!auth.getAuth().is_user(session))
            return "redirect:/signin";

        return "cart";
    }



}
