package com.rovo.userService.controller;

import com.rovo.userService.model.Order;
import com.rovo.userService.model.User;
import com.rovo.userService.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
@Slf4j
public class UserController {

    private UserService userService;
    private RestTemplate restTemplate;

    @PostMapping("/")
    public ResponseEntity<?> dUser(@RequestBody User user){

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
    public ResponseEntity<?> findUserByID(@PathVariable("id") long id){

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
    public ResponseEntity<?> updateUser(@PathVariable("id") long id,@RequestBody User user){


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
    public ResponseEntity<?> deleteUser(@PathVariable("id")long id){


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
    public Order getOrders(@PathVariable("id") long id) {
        Order orders = restTemplate.getForObject("http://localhost:8081/"+"api/v1/orders/"+id, Order.class);
        return orders;
    }
}
