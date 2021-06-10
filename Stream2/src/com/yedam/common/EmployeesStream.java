package com.yedam.common;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.yedam.Employees;
import com.yedam.EmployeesDAO;

public class EmployeesStream {

	public static void main(String[] args) {
		
		EmployeesDAO edao = new EmployeesDAO();
		ArrayList<Employees> employee = edao.getEmployeesList();
		Stream<Employees> eStream = employee.stream();
		eStream.filter((t) -> Integer.parseInt(t.getBirthDate().substring(0, 4)) > 1970)
			   .forEach((t) -> System.out.println(t.toString()));		
	}
}
