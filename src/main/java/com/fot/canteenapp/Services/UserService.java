package com.fot.canteenapp.Services;

import com.fot.canteenapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fot.canteenapp.Entity.User;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;

    public void insert(User user){
        repo.save(user);
    }

    public User login(String email , String password ){
        User u = repo.findByEmailAndPassword(email,password);
        return u;
    }

}
