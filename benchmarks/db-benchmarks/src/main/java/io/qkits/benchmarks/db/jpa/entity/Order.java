package io.qkits.benchmarks.db.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity()
@Table(name="orders")
@Data
public class Order {
	@Id
	private Integer id;
	private String name;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cust_id")
	private Customer customer;

}
