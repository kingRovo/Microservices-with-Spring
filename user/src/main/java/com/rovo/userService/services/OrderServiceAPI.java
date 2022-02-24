package com.rovo.userService.services;

import com.rovo.userService.model.Order;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import retrofit2.http.*;

import java.util.List;

@Service
public interface OrderServiceAPI {

    @Headers({ "Accept: application/json" })
    @GET("api/v1/orders/")
    Call<List<Order>> getAllOrders();

    @PUT("api/v1/orders/{id}")
    Call<Order> updateOrder(@Path("id") Long id, @Body Order order);
}
