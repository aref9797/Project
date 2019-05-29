package com.bonabu.Project.Controller;


import com.bonabu.Project.Controller.Model.ResponseStatuseModel;
import com.bonabu.Project.Controller.Model.SingupModel;
import com.bonabu.Project.Controller.Model.UserModel;
import com.bonabu.Project.Controller.Model.loginModel;
import com.bonabu.Project.Entity.*;
import com.bonabu.Project.Service.Repository.PersonRepasitory;
import com.bonabu.Project.Service.Repository.StudentRepasitory;
import com.bonabu.Project.Service.Repository.StuffRepsitory;
import com.bonabu.Project.Service.ServiceInterfaces.EnduserService;
import com.bonabu.Project.Service.ServiceInterfaces.StudentService;
import com.bonabu.Project.Service.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/AAA")
public class UserController {
    @Autowired
    private UserService userUc;

    @Autowired
    private EnduserService enduserUc;

    @GetMapping("hi")
    public void  hiiii(HttpServletRequest re)
    {

        System.out.println(re.getSession().getAttribute("userId"));
    }


    @PostMapping("/changepass")
    public ResponseStatuseModel change(@RequestBody SingupModel user,HttpServletRequest request)
    {
        ResponseStatuseModel rs=new ResponseStatuseModel();
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = userUc.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo == null) {
                rs.setStatuse(505);
                rs.setMassege("user not found");
            }
            else {
                if(null!=userUc.changepass(user.getPass(),uo.get()))
                {
                    rs.setStatuse(200);
                    rs.setMassege("ok");

                }
                else {
                    rs.setStatuse(404);
                    rs.setMassege("fail");

                }
            }
        }
        else {
            rs.setStatuse(504);
            rs.setMassege("no access");
        }
        return rs;

    }

    @Autowired
    private PersonRepasitory PersEe;

    @PostMapping("/chekname")
    public SingupModel chekname(@RequestParam String code)
    {

        SingupModel sin=new SingupModel();
        if (code.length()==10)
        {
            Person st=PersEe.findByCodemelli(code);
            if(st!=null&&st.getIsuse())
            {
                sin.setName(st.getName());
                sin.setFamily(st.getFamily());
                sin.setCodemelli(st.getCodemelli());
                if (stdRe.findByCodemelli(code)!=null)
                    sin.setType("دانشجو");
                else if (stfRe.findByCodemelli(code)!=null)
                    sin.setType("کارمند");
                sin.setEmail("");
                sin.setPass("");
                return sin;
            }
        }
        sin.setName("");
        sin.setFamily("");
        sin.setCodemelli("");
        sin.setEmail("");
        sin.setPass("");
        return sin;
    }

    @Autowired
    private StudentRepasitory stdRe;

    @Autowired
    private StuffRepsitory stfRe;

    @Transactional
    @PostMapping("/sing-up")
    public ResponseStatuseModel singup(@RequestBody SingupModel user)
    {
        enduser end=new enduser();

        ResponseStatuseModel rs=new ResponseStatuseModel();

        if (user.getCodemelli().length()!=10)
        {
            rs.setStatuse(405);
            rs.setMassege("کد ملی اشتباه");
            return rs;
        }
        end.setEmail(user.getEmail());
        end.setPassword(user.getPass());
        Person person=PersEe.findByCodemelli(user.getCodemelli());
        if(person!=null&&person.getIsuse())
        {
            end.setName(person.getName());
            end.setFamily(person.getFamily());
            if (stdRe.findById(person.getId())!=null)
                end.setType(1);
            else if (stdRe.findById(person.getId())!=null)
                end.setType(2);
            person.setIsuse(false);
            PersEe.save(person);

        }
        else {
            rs.setStatuse(405);
            rs.setMassege("کد ملی اشتباه");
            return rs;
        }

        end.setRole(3);
        user us=enduserUc.add(end);
        if (us!=null)
        {
            rs.setStatuse(200);
            rs.setMassege("ok");
            return rs;
        }
        rs.setStatuse(404);
        rs.setMassege("ایمیل تکراری");
        return rs;
    }
    @PostMapping(path = "/sing-in")
    public ResponseStatuseModel singin(@RequestBody loginModel log,HttpServletRequest request)
    {

        ResponseStatuseModel rs=new ResponseStatuseModel();
        Long us= userUc.login(log.getEmail(),log.getPass());

        if(us==Long.valueOf(-2))
        {
            rs.setStatuse(404);
            rs.setMassege("user not found");
        }
        else if(us==Long.valueOf(-1))
        {
            rs.setStatuse(403);
            rs.setMassege("pass wronge");
        }
        else{
            request.getSession().setAttribute("userId",us);
            rs.setStatuse(200);
            rs.setMassege("user found");
        }
        return rs;
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
            request.getSession().setAttribute("userId",null);
    }

}
