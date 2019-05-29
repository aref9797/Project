package com.bonabu.Project.Service.ServiceImpel;

import com.bonabu.Project.Entity.user;
import com.bonabu.Project.Service.Repository.UserRepository;
import com.bonabu.Project.Service.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpel implements UserService {

    @Autowired
    private UserRepository UserUC;



    @Override
    @Transactional
    public Optional<user> findbyid(Long id)
    {

        Optional<user> uo= null;

        return UserUC.findById(id);
    }
    @Override
    @Transactional
    public user add(user user)
    {
        Iterable<user> us=UserUC.findAll();
        Iterator<user> Itus=us.iterator();
        while (Itus.hasNext())
        {
            user use=Itus.next();
            if (use.getEmail().equals(user.getEmail()))
                return null;
        }
        return UserUC.save(user);
    }

    @Override
    @Transactional
    public Long login(String email, String pass) {
        Iterable<user> us=UserUC.findAll();
        Iterator<user> Itus=us.iterator();
        while (Itus.hasNext())
        {
            user use=Itus.next();
            if (use.getEmail().equals(email))
            {
                if (use.getPassword().equals(pass))
                    return use.getId();
                else
                    return Long.valueOf(-1);
            }
        }
        return Long.valueOf(-2);
    }

    @Override
    @Transactional
    public user changepass(String pass,user entity) {
        entity.setPassword(pass);
        return UserUC.save(entity);
    }
//
//    @Override
//    @Transactional
//    public void logout() {
//
//        SessionManager.Invalidate();
//    }


}
