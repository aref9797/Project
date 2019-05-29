package com.bonabu.Project.Service.ServiceInterfaces;

import com.bonabu.Project.Entity.Problem;
import com.bonabu.Project.Entity.enduser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProblemService {
    @Transactional
    Problem delete(Problem po);

    @Transactional
    Problem getById(Long Id);


    @Transactional
    List<Problem> allproblem(enduser entity);

    @Transactional
    Problem chekok(Problem entity);

    @Transactional
    Problem edit(Problem entity);

    @Transactional
    List<Problem> getAll();

    @Transactional
    Problem add(Problem Problem);

}
