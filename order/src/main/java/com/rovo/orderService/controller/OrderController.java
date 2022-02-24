package com.rovo.orderService.controller;

import com.rovo.orderService.model.Order;
import com.rovo.orderService.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {

    OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<?> addNewOrder(@RequestBody Order order){

        try{
            orderService.addOrder(order);
            log.info("new Order created");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception exception){
            log.error("Invalid Request");
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Order>> displayAllOrder(){
        try {

             return new ResponseEntity<>(orderService.DisplayOrders(),HttpStatus.OK);

        }
        catch (Exception exception){
            log.error("invalid Request..");
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> displayOrder(@PathVariable("id") Long id){

        try{
        Order order  = orderService.GetOrder(id);
        log.info("Order Display..");

        return new ResponseEntity<>(order,HttpStatus.OK);

        }
        catch (Exception exception){
            log.error("Invalid Request");
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
