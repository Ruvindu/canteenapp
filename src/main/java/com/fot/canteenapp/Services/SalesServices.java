package com.fot.canteenapp.Services;

import com.fot.canteenapp.Entity.Sales;
import com.fot.canteenapp.Repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServices {

    @Autowired
    private SalesRepository salesRepository;

    public List<Sales> getAllSales(){
        return salesRepository.findAll();
    }
}
