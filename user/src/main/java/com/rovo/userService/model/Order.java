package com.rovo.userService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private long id;
    private String productName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date Orderdate;


}
