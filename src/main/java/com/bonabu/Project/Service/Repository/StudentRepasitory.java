package com.bonabu.Project.Service.Repository;

import com.bonabu.Project.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepasitory extends JpaRepository<Student,Long> {

    Student findByCodemelli(String codemelli);

}
