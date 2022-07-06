package demo.rpc.api.model;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
public class User {

    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
