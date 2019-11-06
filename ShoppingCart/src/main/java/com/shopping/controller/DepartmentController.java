package com.shopping.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.Department;
import com.shopping.service.DepartmentService;
@RestController
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/departments")
    public List<Department> getDepartments() {

		List<Department> departmentList=departmentService.getAllDepartments();
		
        
        return departmentList;
    }
	@GetMapping("/departments/{deptid}")
	public ResponseEntity<Department> getDepartment(@PathVariable("deptid") int id) {

			//List<Department> departmentList=departmentService.getAllDepartments();
	        Department department=departmentService.getDepartmentById(id);
	        return ResponseEntity.ok().body(department);
	   }
	
}
