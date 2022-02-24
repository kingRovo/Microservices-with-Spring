package com.rovo.userService.services;

import com.rovo.userService.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

@Service

public class OrderService {

    private static final String URI_ORDER = "http://localhost:8081/";

    private final RestTemplate restTemplate;



    @Autowired
    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public List<Order> getAllOrders() throws Exception {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URI_ORDER)
                .addConverterFactory(GsonConverterFactory.create()).build();

        OrderServiceAPI OrderResource = retrofit.create(OrderServiceAPI.class);
        Call<List<Order>> orders = OrderResource.getAllOrders();
        return orders.execute().body();
    }


    public Order updateOrder(Long id,Order order) throws Exception {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URI_ORDER)
                .addConverterFactory(GsonConverterFactory.create()).build();

        OrderServiceAPI service = retrofit.create(OrderServiceAPI.class);

        return service.updateOrder(id, order).execute().body();
    }


    public Order getOrder(Long id){

        Order orders = restTemplate.getForObject("http://ORDER-SERVICE/"+"api/v1/orders/"+id, Order.class);
        return orders;
    }

    public void AddOrderToUser(Order order){

        restTemplate.postForObject("http://ORDER-SERVICE/"+"api/v1/orders/",order, Order.class);

    }
}
