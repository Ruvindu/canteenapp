package com.fot.canteenapp.Services;

import com.fot.canteenapp.Entity.InventoryAndCart;
import com.fot.canteenapp.Entity.Orders;
import com.fot.canteenapp.Repository.OrdersRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository orderRepo;

    public void saveOrder(Orders order){
        orderRepo.save(order);
    }
    public List<Orders> getLastOrder(){ return orderRepo.findLastOrder(); }
    public List<Orders> getAllOrders(){return orderRepo.findAll();}

    public void UpdatePayedOrder(Integer orderid){
        orderRepo.updatePayedOrder(orderid);
    }

    public void issueOrder(Integer id) {
        Orders order = orderRepo.findById(id).get();
        order.setStatus(2);
        orderRepo.save(order);
    }

    public void cancelOrder(Integer id) {
        Orders order = orderRepo.findById(id).get();
        order.setStatus(0);
        orderRepo.save(order);
    }
}
