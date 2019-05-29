package com.bonabu.Project.Service.ServiceInterfaces;

import com.bonabu.Project.Entity.user;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {


    @Transactional
    Optional<user> findbyid(Long id);

    user add(user user);
    Long login(String email, String pass);


    @Transactional
    user changepass(String pass, user entity);

}
