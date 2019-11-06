package com.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Department;
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
	List<Department> findAll();
	Department findByDepartmentId(int deptId);
	

}
