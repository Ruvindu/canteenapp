package com.fot.canteenapp.Repository;

import com.fot.canteenapp.Entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales,Integer> {
}
