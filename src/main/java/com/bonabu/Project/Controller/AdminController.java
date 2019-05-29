package com.bonabu.Project.Controller;


import com.bonabu.Project.Controller.Model.*;
import com.bonabu.Project.Entity.Problem;
import com.bonabu.Project.Entity.admin;
import com.bonabu.Project.Entity.user;
import com.bonabu.Project.Service.ServiceInterfaces.AdminService;
import com.bonabu.Project.Service.ServiceInterfaces.ProblemService;
import com.bonabu.Project.Service.ServiceInterfaces.UserService;
import com.bonabu.Project.common.calc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminUC;

    @Autowired
    private UserService usUC;

    @Autowired
    private ProblemService proUC;

    private Problem SelectProblem;



    @GetMapping("dashbord")
    public DashbordModel getchart(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null) {
            admin uo = adminUC.getbyId((Long) request.getSession().getAttribute("userId"));
            DashbordModel dash=new DashbordModel();
            dash.setAllmassege(proUC.getAll().size());
            dash.setSelfmassege(uo.getProblems().size());
            dash.setSelfnnread(adminUC.getNoRead(uo).size());
            return dash;
        }
        return null;
    }

    @GetMapping("allmassege")
    public Iterable<AdminProblemModel> allprob(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo=usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo==null)
                return null;
            if (uo.get().getRole()==2)
            {
                ArrayList<AdminProblemModel> adpli=new ArrayList<AdminProblemModel>();
                List<Problem> prolist=adminUC.getbyId(uo.get().getId()).getProblems();
                prolist.sort(new Comparator<Problem>() {
                    @Override
                    public int compare(Problem o1, Problem o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });
                for (Problem x:prolist)
                {
                    AdminProblemModel adr=new AdminProblemModel();
                    adr.setId(x.getId());
                    adr.setTitle(x.getTitle());
                    adr.setDesc(x.getDescription());
                    adr.setDate(calc.getCurrentShamsidate(x.getDate()));
                    adr.setName(x.getEnduser().getName());
                    adr.setEmail(x.getEnduser().getEmail());
                    adr.setStatuse(x.getStatuse());
                    adr.setAnswer(new Res_AnswerModel());
                    if (x.getAmswer()!=null)
                    {
                        adr.getAnswer().setAnswer(x.getAmswer().getAnswer());
                        adr.getAnswer().setTime(x.getAmswer().getTime());
                    }
                    else
                    {

                        adr.getAnswer().setAnswer("");
                        adr.getAnswer().setTime("");
                    }

                    adpli.add(adr);
                }
                return adpli;
            }
        }
        return null;
    }

    @GetMapping("/all")
    public Iterable<AdminModel> all(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null)
        {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo == null)
                return null;
            if (uo.get().getRole() == 3) {
                ArrayList<AdminModel> adl = new ArrayList<AdminModel>();
                ArrayList<admin> adminlist = (ArrayList<admin>) adminUC.all();
                for (int x = 0; x < adminlist.size(); x++) {
                    AdminModel ad = new AdminModel();
                    ad.setAdmintitle(adminlist.get(x).getAdminTitle());
                    ad.setEmail(adminlist.get(x).getEmail());
                    ad.setPass("");
                    adl.add(ad);
                }
                return adl;
            }
        }
        return null;

    }



    @GetMapping("/allList")
    public Iterable<AdminListModel> allAdmin(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo == null)
                return null;
            if (uo.get().getRole() == 1) {
                ArrayList<AdminListModel> adl=new ArrayList<AdminListModel>();
                ArrayList<admin> adminlist= (ArrayList<admin>) adminUC.all();
                for (admin x:adminlist)
                {
                    AdminListModel ad=new AdminListModel();
                    ad.setAdmintitle(x.getAdminTitle());
                    ad.setEmail(x.getEmail());
                    if(adminUC.getNoRead(x)!=null)
                        ad.setAddnoreadmassege(adminUC.getNoRead(x).size());
                    else
                        ad.setAddnoreadmassege(0);
                    if (x.getProblems()!=null)
                        ad.setAllmassege(x.getProblems().size());
                    else
                        ad.setAllmassege(0);
                    adl.add(ad);
                }
                return adl;
            }
        }
        return null;
    }

    @PostMapping("/add")
    public ResponseStatuseModel add(@RequestBody AdminModel adminmodel,HttpServletRequest request)
    {

        ResponseStatuseModel rs = new ResponseStatuseModel();
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo == null)
            {
                rs.setStatuse(404);
                rs.setMassege("no access");
            }
            if (uo.get().getRole() == 1) {
                admin admin = new admin();
                admin.setAdminTitle(adminmodel.getAdmintitle());
                admin.setPassword(adminmodel.getPass());
                admin.setEmail(adminmodel.getEmail());
                admin.setRole(2);
                adminUC.add(admin);
                rs.setStatuse(200);
                rs.setMassege("ok");

            }
        }
        else
        {
            rs.setStatuse(504);
            rs.setMassege("no access");
        }
        return rs;


    }
}
