package com.bonabu.Project.Service.ServiceImpel;

import com.bonabu.Project.Entity.*;
import com.bonabu.Project.Service.Repository.AnswerRepository;
import com.bonabu.Project.Service.Repository.ProblemRepository;
import com.bonabu.Project.Service.ServiceInterfaces.EnduserService;
import com.bonabu.Project.Service.ServiceInterfaces.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemServiceImpel implements ProblemService {

    @Autowired
    private ProblemRepository prbUC;
    @Autowired
    private EnduserService endUC;

    @Autowired
    private AnswerRepository ansUC;

    @Override
    @Transactional
    public Problem delete(Problem po)
    {
        prbUC.delete(po);
        return po;
    }


    @Override
    @Transactional
    public Problem getById(Long Id)
    {
        return prbUC.findById(Id).get();
    }

    @Override
    @Transactional
    public List<Problem> allproblem(enduser entity)
    {
        List<Problem> lik=entity.getProblems();
        return lik;
    }



    @Override
    @Transactional
    public Problem chekok(Problem entity)
    {
        entity.setStatuse(3);
        return this.prbUC.save(entity);
    }

    @Override
    @Transactional
    public Problem edit(Problem entity)
    {
        Answer ans=new Answer();
        ans=entity.getAmswer();
        ans.setProblem(entity);
        ansUC.save(ans);
        return this.prbUC.save(entity);
    }

    @Override
    @Transactional
    public List<Problem> getAll()
    {
        return (List<Problem>) this.prbUC.findAll();
    }

    @Override
    @Transactional
    public Problem add(Problem Problem)
    {
        java.util.Date date=new java.util.Date();
        Problem.setDate(date);
        Problem.setStatuse(1);
        return prbUC.save(Problem);
    }
}
