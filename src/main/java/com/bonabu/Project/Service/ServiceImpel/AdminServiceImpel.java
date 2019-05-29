package com.bonabu.Project.Service.ServiceImpel;


import com.bonabu.Project.Entity.Problem;
import com.bonabu.Project.Entity.admin;
import com.bonabu.Project.Entity.user;
import com.bonabu.Project.Service.Repository.AdminRepository;
import com.bonabu.Project.Service.ServiceInterfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpel implements AdminService {

    @Autowired
    private AdminRepository adminUC;

//
//    @Override
//    @Transactional
//    public List<Problem> getall()
//    {
//        return getadmin().getProblems();
//    }

    @Override
    @Transactional
    public admin getbyId(Long Id)
    {
        return adminUC.findById(Id).get();
    }

    @Override
    @Transactional
    public List<Problem> getNoRead(admin entity)
    {
        List<Problem> allpro=entity.getProblems();
        if (allpro==null)
            return null;
        for (int x=0;x<allpro.size();x++)
            if (allpro.get(x).getStatuse()!=1) {
                allpro.remove(x);
                x--;
            }

         return allpro;
    }

    @Override
    @Transactional
    public admin add(admin admin) {
        return adminUC.save(admin);
    }
    @Override
    @Transactional
    public Iterable<admin> all()
    {
        return adminUC.findAll();
    }

    @Override
    @Transactional
    public admin getByname(String admintitle)
    {
        ArrayList<admin> adminlist= (ArrayList<admin>) this.all();
        for (int x=0;x<adminlist.size();x++) {
            if (adminlist.get(x).getEmail().equals(admintitle))
                return adminlist.get(x);
        }
        return null;
    }
//    @Override
//    @Transactional
//    public admin getadmin()
//    {
//        return adminUC.findById(SessionManager.getUserId()).get();
//    }
}
