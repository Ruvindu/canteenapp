package com.fot.canteenapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping(path = "/")
    public String navigatetohome() {
        return "home";
    }

    @GetMapping(path = "/dashboard")
    public String navigatetodashboard() {
        return "admin_template/admin_dashboard";
    }
}