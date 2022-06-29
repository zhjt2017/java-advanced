package app.sharding.mapper;

import app.sharding.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Mapping of Order
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-26 10:34:18
 */
@Repository
public class OrderMapper {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //    @PostConstruct
//    private void init() {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    public int insert(Order order) {
        return jdbcTemplate.update(insertSql(), ps -> setOrderValues(ps, order));
    }

    public int[] batchInsert(final List<Order> orders) {
        return jdbcTemplate.batchUpdate(insertSql(), new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                setOrderValues(ps, orders.get(i));
            }

            @Override
            public int getBatchSize() {
                return orders.size();
            }
        });
    }

    public Order selectById(int id) {
        final String selectSql = "select * from t_order_master where id = ?";
        return jdbcTemplate.queryForObject(selectSql, this::getOrderValues, id);
    }

    public List<Order> listAllOrders() {
        final String selectSql = "select * from t_order_master";
        return jdbcTemplate.query(selectSql, this::getOrderValues);
    }

    /**
     * 查询全部订单数量
     *
     * @return 订单列表
     */
    public Integer countAllOrders() {
        String sql = "select count(*) from t_order_master";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 根据订单 ID 删除 订单
     *
     * @param id 订单 ID
     * @return int
     */
    public int deleteById(int id) {
        String deleteSql = "delete from t_order_master where id = ?";
        return jdbcTemplate.update(deleteSql, id);
    }

    private void setOrderValues(PreparedStatement ps, Order order) throws SQLException {
        int index = 0;
        ps.setString(++index, order.getOrderNumber());
        ps.setInt(++index, order.getUserId());
        ps.setShort(++index, order.getStatus());
        ps.setShort(++index, order.getDeliverStatus());
        ps.setInt(++index, order.getTotalPrice());
    }

    /**
     * 读取时，获取订单值
     */
    private Order getOrderValues(ResultSet rs, int rowNum) throws SQLException {
        final Order order = new Order();
        int index = 0;
        order.setId(rs.getInt(++index));
        order.setOrderNumber(rs.getString(++index));
        order.setUserId(rs.getInt(++index));
        order.setStatus(rs.getShort(++index));
        order.setDeliverStatus(rs.getShort(++index));
        order.setTotalPrice(rs.getInt(++index));
        order.setCreateTime(rs.getTimestamp(++index));
        order.setUpdateTime(rs.getTimestamp(++index));
        return order;
    }

    private String insertSql() {
        return "insert into t_order_master(order_number, user_id, status, deliver_status, total_price) values (?, ?, ?, ?, ?)";
    }
}
