package com.bonabu.Project.Controller;

import com.bonabu.Project.Controller.Model.DashbordModel;
import com.bonabu.Project.Controller.Model.UserModel;
import com.bonabu.Project.Entity.Problem;
import com.bonabu.Project.Entity.enduser;
import com.bonabu.Project.Entity.user;
import com.bonabu.Project.Service.ServiceInterfaces.AdminService;
import com.bonabu.Project.Service.ServiceInterfaces.EnduserService;
import com.bonabu.Project.Service.ServiceInterfaces.ProblemService;
import com.bonabu.Project.Service.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enduser")
public class EnduserController {

    @Autowired
    private ProblemService proUC;

    @Autowired
    private EnduserService enduserUC;

    @Autowired
    private UserService usUC;


    @GetMapping("dashbord")
    public DashbordModel dash(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo == null)
                return null;
            else if (uo.get().getRole() == 3)
            {
                DashbordModel re=new DashbordModel();
                re.setAllmassege(proUC.getAll().size());

                enduser user=enduserUC.findbyId(uo.get().getId());
                re.setSelfmassege(user.getProblems().size());
                int c=0;
                for (Problem x:user.getProblems())
                {
                    if (x.getStatuse()==1)
                        c++;
                }
                re.setSelfnnread(c);
                return re;
            }

        }
        return null;
    }

    @GetMapping("all")
    public Iterable<UserModel> all(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo==null)
                return null;
            else if (uo.get().getRole()==1)
            {

                ArrayList<UserModel> usmli=new ArrayList<UserModel>();
                List<enduser> enlist=enduserUC.getAll();
                for(enduser x:enlist)
                {
                    UserModel um=new UserModel();
                    um.setName(x.getName());
                    um.setEmail(x.getName());
                    um.setAllmassege(x.getProblems().size());
                    um.setAllnoreadmassege(enduserUC.getNoRead(x));
                    usmli.add(um);
                }
                return usmli;
            }
        }
        return null;

    }
}
