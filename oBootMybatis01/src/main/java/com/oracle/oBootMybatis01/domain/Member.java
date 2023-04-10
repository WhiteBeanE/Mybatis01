package com.oracle.oBootMybatis01.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "member3")
public class Member {
	@Id
	private Long id;
	private String name;
	private String password;
	private Date reg_date;
}
