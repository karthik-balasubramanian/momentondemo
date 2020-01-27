package com.momenton.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.momenton.demo.domain.Company;
import com.momenton.demo.domain.Employee;

@SpringBootApplication
public class MomentonDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomentonDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			Employee ceo = new Employee(150, null, "Jamie");
			Company company = new Company(ceo);
			company.addNewEmployee(new Employee(100, 150, "Alan"));
			company.addNewEmployee(new Employee(220, 100, "Martin"));
			company.addNewEmployee(new Employee(275, 100, "Alex"));
			company.addNewEmployee(new Employee(400, 150, "Steve"));
			company.addNewEmployee(new Employee(190, 400, "David"));

			System.out.println("****************************** RESULT *********************************");

			printSubordinates(company, company.getCeo(), 0);
		};
	}

	/**
	 * Prints result.
	 */
	private void printSubordinates(Company company, Employee emp, int level) {
		System.out.println(getLevelSeparator(level) + emp.getName());
		List<Employee> directReportees = company.findAllDirectReportees(emp);
		if (!directReportees.isEmpty()) {
			for (Employee subordinate : directReportees) {
				printSubordinates(company, subordinate, level + 1);
			}
		}

	}

	/**
	 * Calculates the number of spaces to print
	 */
	private String getLevelSeparator(int level) {
		if (level == 0)
			return "";
		StringBuilder separator = new StringBuilder();
		for (int i = 0; i < level; i++) {
			separator.append("\t");
		}
		return separator.toString();
	}
}
