package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.Cart;
import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Entity.ViewCart;
import com.fot.canteenapp.Services.CartService;
import com.fot.canteenapp.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String navigatetocart(Model model, HttpSession session) {
        //user authenticate
        if (!auth.getAuth().is_user(session))
            return "redirect:/signin";

        List<String> user_s = (List<String>) session.getAttribute("USER_SESSION");
        model.addAttribute("cartlist",  cartser.viewCart(Integer.parseInt(user_s.get(0))));

        return "cart";
    }


    @RequestMapping(method=RequestMethod.GET, value="/remove")
    public String removeCart(@RequestParam(value = "id") Integer cartid ,HttpSession session){
        //user authenticate
        if (!auth.getAuth().is_user(session))
            return "redirect:/signin";

        cartser.deleteCart(cartid);
        return "redirect:/cart";
    }


}
