package com.bonabu.Project.Service.ServiceInterfaces;

import com.bonabu.Project.Entity.Problem;
import com.bonabu.Project.Entity.admin;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {

//
//    @Transactional
//    List<Problem> getall();

    @Transactional
    admin getbyId(Long Id);

    @Transactional
    List<Problem> getNoRead(admin entity);

    admin add(admin admin);

    @Transactional
    Iterable<admin> all();

    @Transactional
    admin getByname(String admintitle);
//
//    @Transactional
//    admin getadmin();
}
