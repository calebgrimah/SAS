package com.fproject.sas.repository;

import com.fproject.sas.domain.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,Long> {

    Department findFirstByDepartmentId(String departmentId);
}
