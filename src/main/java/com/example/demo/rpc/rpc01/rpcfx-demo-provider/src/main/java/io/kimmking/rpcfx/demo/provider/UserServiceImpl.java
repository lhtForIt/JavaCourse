package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.MyServer;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;

@MyServer(group = "peipei",version = "2.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
