package com.fot.canteenapp.Controller;


import com.fot.canteenapp.Entity.Orders;
import com.fot.canteenapp.Services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class OrdersController {

    @Autowired
    private OrdersService orderser;


    @RequestMapping(value = "/placeorder", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute("neworder") Orders order){
        orderser.saveOrder(order);
        return "redirect:/";
    }


}
