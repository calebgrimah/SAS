package com.fproject.sas.controller.model;

import com.fproject.sas.domain.User;
import com.fproject.sas.domain.UserType;
import lombok.Data;

@Data
public class UserRequestBody {
    private String username;
    private User user;

}
