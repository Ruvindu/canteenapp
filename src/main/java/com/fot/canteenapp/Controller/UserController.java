package com.fot.canteenapp.Controller;

import com.fot.canteenapp.Entity.User;
import com.fot.canteenapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userser;

//    Sigin up
    @RequestMapping(value = "signup",method = RequestMethod.GET)
    public String signup(Model model){
        User user=new User();
        model.addAttribute("newuser",user);
        return "signup";
    }
    @RequestMapping(value = "/saveuser",method = RequestMethod.POST)
    public String save(@ModelAttribute("newuser")User user){
        userser.insert(user);
        return "redirect:/signin";
    }

// Sigin in
    @GetMapping(path = "/signin")
    public String navigatetosignin(Model model) {
        User user=new User();
        model.addAttribute("signinuser",user);
        return "signin";
    }


    @RequestMapping(value = "/signinuser",method = RequestMethod.POST)
    public String siginin(@ModelAttribute("signinuser")User user){

        User logeduser = userser.login(user.getEmail(),user.getPassword());

        if (Objects.nonNull(logeduser)){
            System.out.println(logeduser.getRole());
            if (logeduser.getRole().equals("admin")){
                return "redirect:/dashboard";
            }else{
                return "redirect:/";
            }
        }else{
            return "redirect:/signin?loginerr";
        }
    }

}
