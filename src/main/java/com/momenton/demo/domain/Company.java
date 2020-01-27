package com.momenton.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Company {

	private List<Employee> employees = new ArrayList<>();
	private Employee ceo;

	public Company(Employee ceo) {
		// Assumption: Company must have a CEO
		if (ceo == null) {
			throw new RuntimeException("Company must have a CEO");
		}
		if (ceo.getManagerId() != null) {
			throw new RuntimeException("CEO cannot have a manager");
		}
		this.ceo = ceo;
		employees.add(ceo);
	}

	/**
	 * Adds a new employee to the company.
	 * 
	 * @param emp
	 */
	public void addNewEmployee(Employee emp) throws Exception {
		if (emp == null) {
			throw new Exception("Employee cannot be null");
		}
		if (validateEmployeeId(emp.getId())) {
			throw new Exception("Employee ID must be unique");
		}
		if (emp.getManagerId() == null) {
			throw new Exception("Employee's manager cannot be null" + emp.getName());
		}
		Optional<Employee> manager = employees.stream().filter((e) -> emp.getManagerId().equals(e.getId())).findAny();
		if (!manager.isPresent()) {
			throw new Exception("Invalid manager Id");
		}
		employees.add(emp);
	}

	private boolean validateEmployeeId(Integer id) {
		Optional<Employee> emp = employees.stream().filter((e) -> e.getId().equals(id)).findAny();
		return emp.isPresent();
	}

	/**
	 * Finds direct reportees of an Employee.
	 *
	 * @param emp the Employee.
	 * @return The list of direct reportees.
	 */
	public List<Employee> findAllDirectReportees(Employee emp) {
		return employees.stream().filter((e) -> {
			if (e.getManagerId() == null)
				return false;
			return e.getManagerId().equals(emp.getId());
		}).collect(Collectors.toList());
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public Employee getCeo() {
		return ceo;
	}

}
