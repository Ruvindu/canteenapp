package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.Cart;
import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Entity.User;
import com.fot.canteenapp.Services.InventoryService;
import com.fot.canteenapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService invser;

    //Save items in inventory
    @PostMapping("/saveitem")
    public String insertItem(@RequestBody Inventory inv){
        invser.saveItem(inv);
        return "Item added";
    }

    //Request home page
    @RequestMapping("/")
    public String viewHomePage(Model model, HttpSession session){
        List<Inventory> listProducts = invser.getAllItems();
        model.addAttribute("listProducts",listProducts);


        @SuppressWarnings("unchecked")
        List<String> user_s = (List<String>) session.getAttribute("USER_SESSION");

        if (user_s == null) {
//            user_s = new ArrayList<>();
            model.addAttribute("sess", null);
        }else{
            model.addAttribute("sess", user_s.get(0));
        }

        return "home";
    }

    //proceed order when user pressed order now
    @RequestMapping(method=RequestMethod.GET, value="/proceedorder")
    public String proceedorder(Model model,@RequestParam(value = "item") Integer itemid){
        Optional<Inventory> Productdata = invser.getItemData(itemid);
        model.addAttribute("productdata",Productdata);
        return "proceedorder";
//        return  Productdata;
    }



}
