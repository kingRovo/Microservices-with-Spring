package com.rovo.userService.services;

import com.rovo.userService.model.User;
import com.rovo.userService.userRepo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepo userRepo;

    public List<User> displayUsers(){
        return userRepo.findAll();
    }
    public void addUser(User user){

        userRepo.save(user);
    }

    public User findUser(Long id){

        return userRepo.findById(id).orElseThrow();

    }

    public void editUser(Long id, User user){

        User usr = userRepo.findById(id).orElseThrow();

        usr = user;
        userRepo.save(usr);
    }

    public void deleteUser(Long id){

        userRepo.deleteById(id);
    }
}
