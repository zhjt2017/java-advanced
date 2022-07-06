package demo.rpc.api.model;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
public class Order {

    private Integer id;
    private String name;
    private Integer userId;

    public Order(Integer id, String name, Integer userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getUserId() {
        return userId;
    }
}
