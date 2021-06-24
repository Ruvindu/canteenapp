package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Services.InventoryService;
import com.fot.canteenapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService invser;

    @PostMapping(value = "/saveitem")
    public String insertItem(@RequestBody Inventory inv){
        invser.save(inv);

        return "Item added";
    }

    @GetMapping(value = "/getitems")
    public List<Inventory> getItems(){
        return invser.getAllItems();
    }

}
