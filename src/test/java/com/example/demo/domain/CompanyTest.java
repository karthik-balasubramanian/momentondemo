package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.momenton.demo.domain.Company;
import com.momenton.demo.domain.Employee;

public class CompanyTest {

	private Company company;

	@BeforeEach
	public void setUp() throws Exception {
		Employee emp = new Employee(1, null, "ceo");
		company = new Company(emp);
		company.addNewEmployee(new Employee(2, 1, "John Doe"));
		company.addNewEmployee(new Employee(3, 2, "Jane Doe"));
	}
	
	
	@Test
	public void shouldThrowExceptionWhenCEOIsNull() {
		assertThrows(Exception.class, () -> {
			company = new Company(null);
		});

	}
	
	@Test
	public void shouldThrowExceptionWhenCEOHasAManager() {
		assertThrows(Exception.class, () -> {
			Employee ceo = new Employee(1, 2, "ceo");
			Employee emp= new Employee(2, 1, "emp");
			company = new Company(ceo);
		});

	}
	
	
	@Test
	public void shouldThrowExceptionWhenEmployeeIdAlreadyExists() {
		assertThrows(Exception.class, () -> {
			company.addNewEmployee(new Employee(2, 1, "Michael Finch"));
		});

	}
	
	@Test()
	public void shouldThrowExceptionWhenManagerIsNull()
	{	
		assertThrows(Exception.class, () -> {
			company.addNewEmployee(new Employee(4, null, "Michael Finch"));
		});
	}
	
	@Test
	public void shouldAddValidNewEmployee() throws Exception {
		Employee emp = new Employee(4,2,"Michael Finch");
		company.addNewEmployee(emp);
		
		List<Employee> employees = company.getEmployees();
		assertTrue(employees.contains(emp));

	}
	
	@Test
	public void shouldReturnDirectReportees()
	{
		List<Employee> reportees= company.findAllDirectReportees(company.getCeo());
		assertEquals(reportees.size(), 1);
		assertEquals(reportees.get(0).getId(),2);
	}
	
}
