package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.MyServer;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;

@MyServer(group = "leo",version = "1.0.0")
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
