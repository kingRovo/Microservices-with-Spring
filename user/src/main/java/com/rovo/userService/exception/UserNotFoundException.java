package com.rovo.userService.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class UserNotFoundException extends RuntimeException{
    private String  message;
}
