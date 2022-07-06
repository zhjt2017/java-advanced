package demo.rpc.server.service.impl;

import demo.rpc.api.UserService;
import demo.rpc.api.model.User;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer id) {
        return new User(id, "RPC");
    }
}
