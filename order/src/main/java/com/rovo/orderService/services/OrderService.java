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

    public List<Order> displayOrders(){
        return orderRepo.findAll();
    }

    public Order GetOrder(Long id) {
        return orderRepo.findById(id).orElseThrow();
    }

    public void editOrder(Long id,Order order){

        Order localorder = orderRepo.findById(id).orElseThrow();

        localorder.setProductName(order.getProductName());
        localorder.setUser_id(order.getUser_id());
        localorder.setOrderdate(order.getOrderdate());

        orderRepo.save(localorder);
    }

}
