package io.qkits.benchmarks.db.jpa.entity;
import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="users")
@Data
public class User implements Serializable{
	@Id
	@GeneratedValue
	private Integer id ;
	@Column()
	private String code ;
}
