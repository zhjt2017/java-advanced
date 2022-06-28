package app.sharding.po;

import lombok.Data;

import java.util.Date;

/**
 * po - Order
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2022-06-26 10:34:18
 */
@Data
public class Order {
    private Integer id;

    private String orderNumber;

    private Integer userId;

    private Short status;

    private Short deliverStatus;

    private Integer totalPrice;

    private Date createTime;

    private Date updateTime;
}
