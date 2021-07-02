package com.fot.canteenapp.Controller;


import com.fot.canteenapp.Entity.Orders;
import com.fot.canteenapp.Services.OrdersService;
import com.fot.canteenapp.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


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
        return "redirect:/?success";
    }


    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public String payments(Model model, @RequestParam(value = "itemid") Integer itemid,@RequestParam(value = "itemname") String itemname, @RequestParam(value = "qty") Integer qty, @RequestParam(value = "amount") Float amount, HttpSession session){
        //user authenticate
        if (!auth.getAuth().is_user(session))
            return "redirect:/signin";



        List<String> user_s = (List<String>) session.getAttribute("USER_SESSION");
        model.addAttribute("User",user_s);
        model.addAttribute("OrderId",orderser.getLastOrder().get(0).getOrderId()+1);
        model.addAttribute("ItemName",itemname);
        model.addAttribute("Qty",qty);
        model.addAttribute("Amount",amount);

        return "payment_summary";

    }



}
