package io.qkits.bootatisplus.demo.user.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

public class Order extends Model<Order> {

    private Long id;
    private String detail;
    private Double price;
    private Double amount;
    private int quantity;

}
