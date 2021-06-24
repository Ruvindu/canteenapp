package com.fot.canteenapp.Services;

import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Repository.InventoryRepository;
import com.fot.canteenapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepository invrepo;

    public void save(Inventory inv){
        invrepo.save(inv);
    }

    public List<Inventory> getAllItems() {
        return (List<Inventory>) invrepo.findAll();
    }

}
