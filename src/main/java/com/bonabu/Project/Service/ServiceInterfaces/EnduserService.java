package com.bonabu.Project.Service.ServiceInterfaces;

import com.bonabu.Project.Entity.enduser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EnduserService {

    @Transactional
    List<enduser> getAll();

    enduser add(enduser enduser);

    @Transactional
    enduser findbyId(Long id);

    @Transactional
    Integer getNoRead(enduser entity);
}
