package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DepartmentResponseBody {
    List<Department> departments;
}
