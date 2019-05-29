package com.bonabu.Project.Service.Repository;

import com.bonabu.Project.Entity.Problem;
import com.bonabu.Project.Entity.user;
import org.springframework.data.repository.CrudRepository;

public interface ProblemRepository extends CrudRepository<Problem, Long> {

}
