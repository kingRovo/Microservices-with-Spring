package com.rovo.userService.controller;

import com.rovo.userService.model.Order;
import com.rovo.userService.model.User;
import com.rovo.userService.services.OrderService;
import com.rovo.userService.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    private final OrderService orderService;



    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody User user){

        if (user == null) {
            log.error("Invalid user");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }else{

            userService.addUser(user);
            log.info("User added ...");
            return new ResponseEntity<>(HttpStatus.CREATED);

        }

    }

    @GetMapping("/")
    public List<User> displayAllUser(){

        try {
            return userService.displayUsers();

        }
        catch (Exception exception){
            log.info("Fetched User Details");
            return userService.displayUsers();

        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findUserByID(@PathVariable("id") Long id){

            try{

            return new ResponseEntity<>(userService.findUser(id),HttpStatus.OK);
        }
        catch (Exception exception)
        {

            log.error("Invalid Request");
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,@RequestBody User user){


        try{

            userService.editUser(id,user);
            log.info("User Details Updated");
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception exception)
        {

            log.error("Invalid Request");
            return  new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);

        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Long id){


        try{

            userService.deleteUser(id);
            log.info("User Details Updated");
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception exception)
        {

            log.error("Invalid Request");
            return  new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrders(@PathVariable("id") Long id) {

        try{
            Order order = orderService.getOrder(id);
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrder() throws Exception{


        try{

            return new ResponseEntity(orderService.getAllOrders(), HttpStatus.OK);
        }

        catch(Exception exception){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }


    @PostMapping("/{id}/order")
    public ResponseEntity<?> AddUserOrder(@PathVariable("id") Long id, @RequestBody Order order){

        try {
            order.setUser_id(id);
            orderService.AddOrderToUser(order);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id,@RequestBody Order order){

        try{
            orderService.updateOrder(id,order);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
