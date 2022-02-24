package com.rovo.orderService.services;

import com.rovo.orderService.model.Order;
import com.rovo.orderService.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private OrderRepo orderRepo;

    public void addOrder(Order order){
        orderRepo.save(order);
    }

    public List<Order> DisplayOrders(){
        return orderRepo.findAll();
    }

    public Order GetOrder(Long id) {
        return orderRepo.findById(id).orElseThrow();
    }
}
