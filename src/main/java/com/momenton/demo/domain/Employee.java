package com.momenton.demo.domain;

public class Employee {

	private Integer id;
	private Integer managerId;
	private String name;
	
	public Employee(Integer id, Integer managerId, String name) {
		this.id = id;
		this.managerId = managerId;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public String getName() {
		return name;
	}

}
