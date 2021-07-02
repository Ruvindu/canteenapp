package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Entity.Orders;
import com.fot.canteenapp.Entity.Sales;
import com.fot.canteenapp.Services.InventoryService;
import com.fot.canteenapp.Services.OrdersService;
import com.fot.canteenapp.Services.SalesServices;
import com.fot.canteenapp.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private InventoryService invser;

    @Autowired
    private SalesServices salesServices;

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/response")
    @ResponseBody
    public List<Orders>  postResponseController(){
        List<Orders> listOrders = ordersService.getAllOrdersWithItemName();
        return listOrders;
    }

    @RequestMapping("/dashboard")
    public String viewHomePage(Model model, HttpSession session){

//        user authenticate
        if (!auth.getAuth().is_admin(session))
            return "redirect:/signin";


        List<Inventory> listProducts = invser.getAllItems();
        List<Sales> listSales = salesServices.getAllSales();
        List<Orders> listOrders = ordersService.getAllOrders();

        Inventory item = new Inventory();
        model.addAttribute("item", item);
        model.addAttribute("listSales", listSales);
        model.addAttribute("listProducts",listProducts);
        model.addAttribute("listOrders",listOrders);
        List<String> user_s = (List<String>) session.getAttribute("USER_SESSION");

        if (user_s == null) {
//            user_s = new ArrayList<>();
            model.addAttribute("sess", null);
        }else{
            model.addAttribute("sess", user_s);
        }
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
