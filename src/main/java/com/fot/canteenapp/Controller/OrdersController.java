package com.fot.canteenapp.Controller;


import com.fot.canteenapp.Entity.Orders;
import com.fot.canteenapp.Services.OrdersService;
import com.fot.canteenapp.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class OrdersController {

    @Autowired
    private OrdersService orderser;


    @RequestMapping(value = "/placeorder", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute("neworder") Orders order, HttpSession session){
        //user authenticate
        if (!auth.getAuth().is_user(session))
            return "redirect:/signin";

        orderser.saveOrder(order);
        return "redirect:/";
    }


}
