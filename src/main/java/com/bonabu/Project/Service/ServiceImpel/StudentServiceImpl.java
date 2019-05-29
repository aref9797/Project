package com.bonabu.Project.Service.ServiceImpel;


import com.bonabu.Project.Entity.Student;
import com.bonabu.Project.Service.Repository.StudentRepasitory;
import com.bonabu.Project.Service.ServiceInterfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepasitory stdRep;

    @Override
    public Student findbucode(String code)
    {
        return stdRep.findByCodemelli(code);
    }

}
