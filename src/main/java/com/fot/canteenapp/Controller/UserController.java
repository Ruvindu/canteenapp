package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.User;
import com.fot.canteenapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userser;

    @RequestMapping(value = "signup",method = RequestMethod.GET)
    public String signup(Model model){
        User user=new User();
        model.addAttribute("newuser",user);
        return "signup";
    }

    @RequestMapping(value = "/saveuser",method = RequestMethod.POST)
    public String save(@ModelAttribute("newuser")User user){
        userser.insert(user);
        return "Redirect:signin";
    }

}
