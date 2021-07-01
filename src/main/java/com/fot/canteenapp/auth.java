package com.fot.canteenapp;

import javax.servlet.http.HttpSession;
import java.util.List;

public class auth {

    private static auth auth_instance = null;

    private auth(){
    }

    public static auth getAuth()
    {
        if (auth_instance == null)
            auth_instance = new auth();
        return auth_instance;
    }

    public Boolean is_admin(HttpSession session){
        List<String> user_s = (List<String>) session.getAttribute("USER_SESSION");

        try {
            user_s.get(0);
            System.out.println(user_s.get(3));

            if (user_s.get(3).equals("admin")){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

}
