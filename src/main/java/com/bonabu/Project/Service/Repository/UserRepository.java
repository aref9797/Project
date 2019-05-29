package com.bonabu.Project.Service.Repository;

import com.bonabu.Project.Entity.user;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<user, Long> {

}
