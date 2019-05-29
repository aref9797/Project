package com.bonabu.Project.Service.Repository;

import com.bonabu.Project.Entity.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StuffRepsitory extends JpaRepository<Stuff,Long> {

    Stuff findByCodemelli(String codemelli);
}
