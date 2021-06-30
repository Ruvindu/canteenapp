package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private InventoryService invser;


    @RequestMapping("/dashboard")
    public String viewHomePage(Model model){
        List<Inventory> listProducts = invser.getAllItems();
        Inventory item = new Inventory();
        model.addAttribute("item", item);
        model.addAttribute("listProducts",listProducts);
        return "admin_template/admin_dashboard";
    }

    @RequestMapping("/new")
    public void addItemForm(Model model){
        Inventory item = new Inventory();
        model.addAttribute("item", item);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute("item") Inventory item){
        invser.saveItem(item);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteItem(@PathVariable(name = "id") Integer id){
        invser.deleteItem(id);
        return "redirect:/dashboard";
    }
}