package com.fproject.sas.controller.model;

import com.fproject.sas.domain.Department;
import lombok.Data;

@Data
public class DepartmentRequestBody {

    private String username;
    private Department department;

}
