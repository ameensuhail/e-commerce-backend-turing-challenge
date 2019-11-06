package com.shopping.service;

import java.util.List;

import com.shopping.exceptionhandling.ServiceException;
import com.shopping.model.Department;


public interface DepartmentService {
	Department getDepartmentById(int id);
	List<Department> getAllDepartments();

}
