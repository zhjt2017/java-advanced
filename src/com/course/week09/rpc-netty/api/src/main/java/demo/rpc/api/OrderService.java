package demo.rpc.api;

import demo.rpc.api.model.Order;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
public interface OrderService {

    /**
     * find order by id
     *
     * @param id
     * @return
     */
    Order findById(Integer id);
}

