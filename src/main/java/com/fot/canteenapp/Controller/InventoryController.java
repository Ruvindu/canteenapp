package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Services.InventoryService;
import com.fot.canteenapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService invser;

    @PostMapping("/saveitem")
    public String insertItem(@RequestBody Inventory inv){
        invser.saveItem(inv);
        return "Item added";
    }

    @RequestMapping("/all")
    public String viewHomePage(Model model){
        List<Inventory> listProducts = invser.getAllItems();
        model.addAttribute("listProducts",listProducts);
        return "home";
    }

}
