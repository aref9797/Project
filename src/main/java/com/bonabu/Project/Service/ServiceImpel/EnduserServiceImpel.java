package com.bonabu.Project.Service.ServiceImpel;

import com.bonabu.Project.Entity.Problem;
import com.bonabu.Project.Entity.enduser;
import com.bonabu.Project.Service.Repository.EnduserRepository;
import com.bonabu.Project.Service.ServiceInterfaces.EnduserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class EnduserServiceImpel implements EnduserService {

    @Autowired
    private EnduserRepository EndUC;

    @Override
    @Transactional
    public List<enduser> getAll()
    {
        return (List<enduser>) EndUC.findAll();
    }

    @Override
    @Transactional
    public enduser add(enduser enduser) {
        Iterable<enduser> us=EndUC.findAll();
        Iterator<enduser> Itus=us.iterator();
        while (Itus.hasNext())
        {
            enduser use=Itus.next();
            if (use.getEmail().equals(enduser.getEmail()))
                return null;
        }
        return EndUC.save(enduser);
    }

    @Override
    @Transactional
    public enduser findbyId(Long id)
    {
        return EndUC.findById(id).get();
    }

    @Override
    @Transactional
    public Integer getNoRead(enduser entity)
    {
        List<Problem> pol=entity.getProblems();
        int c=0;
        for (Problem x:pol)
            if(x.getStatuse()==1)
                c++;
        return c;
    }

}
