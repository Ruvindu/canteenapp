package com.fot.canteenapp.Repository;
import org.springframework.data.repository.CrudRepository;
import com.fot.canteenapp.Entity.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory,Integer> {

}
