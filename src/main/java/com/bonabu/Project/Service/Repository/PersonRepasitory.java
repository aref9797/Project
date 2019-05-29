package com.bonabu.Project.Service.Repository;

import com.bonabu.Project.Entity.Person;
import com.bonabu.Project.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepasitory extends JpaRepository<Person,Long> {

    Person findByCodemelli(String codemelli);
}
