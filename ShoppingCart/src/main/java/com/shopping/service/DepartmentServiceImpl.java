package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.dao.DepartmentDao;
import com.shopping.exceptionhandling.ServiceException;
import com.shopping.model.Department;
import com.shopping.repository.DepartmentRepository;
@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService{
	
//	@Autowired
//	private DepartmentDao departmentDao;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department getDepartmentById(int id){
		
		return departmentRepository.findByDepartmentId(id);
		
	}

	@Override
	public List<Department> getAllDepartments() {
		
		return departmentRepository.findAll();
	}
	
	
}
