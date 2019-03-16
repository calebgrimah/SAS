package com.fproject.sas.repository;

import com.fproject.sas.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findFirstByUsername(String username);
    User findFirstByUserId(String userId);
}
