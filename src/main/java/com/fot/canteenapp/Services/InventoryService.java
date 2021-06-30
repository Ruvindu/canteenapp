package com.fot.canteenapp.Services;

import com.fot.canteenapp.Entity.Inventory;
import com.fot.canteenapp.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
   private InventoryRepository invRepo;

   public void saveItem(Inventory inv){
        invRepo.save(inv);
   }

   public List<Inventory> getAllItems(){
       return invRepo.findAll();
   }

   public void deleteItem(Integer id){
       invRepo.deleteById(id);
   }

   public Optional<Inventory> getItemData(Integer itemId){
        return invRepo.findById(itemId);
   }

}
