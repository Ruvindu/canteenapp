package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService orderser;
}
