package com.bonabu.Project.Controller;


import com.bonabu.Project.Entity.user;
import com.bonabu.Project.Service.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    private UserService usUC;

    @RequestMapping("/")
    public String goIndex()
    {
        return "index";
    }

    @RequestMapping("/login")
    public String godashbord(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo.get().getRole()==1)
                return "superadmin/dashbord";
            else if (uo.get().getRole()==2)
                return "manger/dashbord";
            return "user/dashbord";
        }
        return "index";

    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
            request.getSession().setAttribute("userId",null);
        return "index";
    }

    ////////////////////////////////////////////////superadmin



    @RequestMapping("/superadmin/alluser")
    public String superadmin_alluser(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo.get().getRole()==1)
                return "superadmin/allusers";
            return "404";
        }
        return "index";
    }

    @RequestMapping("/superadmin/changepass")
    public String superadmin_changepass(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo.get().getRole()==1)
                return "superadmin/chpass";
            return "404";
        }
        return "index";
    }
    @RequestMapping("/superadmin/addadmin")
    public String addadmin(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo.get().getRole()==1)
                return "superadmin/addadmin";
            return "404";
        }
        return "index";
    }

    ///////////////////////////////////////////////manger

    @RequestMapping("/manger/allnoreadmassege")
    public String Maneger_allnoreadmassege(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo==null)
                return "404";
            else if (uo.get().getRole()==2)
                return "manger/new-masseges";
        }
        return "index";
    }

    @RequestMapping("/manger/allmassege")
    public String Maneger_allmassege(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo==null)
                return "404";
            else if (uo.get().getRole()==2)
                return "manger/allmasseges";
        }
        return "index";
    }

    @RequestMapping("/manger/feedback")
    public String Maneger_feedback(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo==null)
                return "404";
            if (uo.get().getRole()==2)
                return "manger/feedback";
        }
        return "index";
    }
    @RequestMapping("/manger/changepass")
    public String Maneger_changepass(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo==null)
                return "404";
            if (uo.get().getRole()==2)
                return "manger/chpass";
        }
        return "index";
    }
    ///////////////////////////////////////////////user

    @RequestMapping("/user/show")
    public String showprob(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo==null)
                return "404";
            if (uo.get().getRole()==3)
                return "user/show";
        }
        return "index";
    }

    @RequestMapping("user/changepass")
    public String changepass(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo==null)
                return "404";
            if (uo.get().getRole()==3)
                return "user/changepass";
        }
        return"index";
    }

    @RequestMapping("/user/allmassege")
    public String allmassege(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo==null)
                return "404";
            if (uo.get().getRole()==3)
                return "user/allmassege";
        }
        return "index";
    }


    @RequestMapping("user/sendmassege")
    public String goMassege(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo==null)
                return "404";
            if (uo.get().getRole()==3)
                return "user/new";
        }
       return "index";
    }
}
