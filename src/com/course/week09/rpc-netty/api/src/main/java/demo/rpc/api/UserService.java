package demo.rpc.api;

import demo.rpc.api.model.User;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
public interface UserService {

    /**
     * find user by id
     *
     * @param id
     * @return
     */
    User findById(Integer id);
}