package com.bonabu.Project.Controller;


import com.bonabu.Project.Controller.Model.*;
import com.bonabu.Project.Entity.*;
import com.bonabu.Project.Service.ServiceInterfaces.AdminService;
import com.bonabu.Project.Service.ServiceInterfaces.EnduserService;
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
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService proUC;

    @Autowired
    private AdminService adminUC;

    @Autowired
    private EnduserService enUC;

    @Autowired
    private UserService usUC;

    private Problem SelectProblem;


    @GetMapping("index_chart")
    public DashbordModel index_chart()
    {
        DashbordModel dsh=new DashbordModel();
        List<Problem> pl=proUC.getAll();
        int r=0,y=0,g=0;
        for (Problem x:pl)
        {
            if (x.getStatuse()==1)
                r++;
            else if (x.getStatuse()==2)
                y++;
            else
                g++;
        }
        dsh.setAllmassege(g);
        dsh.setSelfmassege(y);
        dsh.setSelfnnread(r);
        return dsh;
    }

    @GetMapping("/delete")
    public ResponseStatuseModel deleteselected() {
        ResponseStatuseModel rs = new ResponseStatuseModel();
        if(proUC.delete(SelectProblem)==null)
        {
            rs.setStatuse(404);
            rs.setMassege("failed");
            return rs;
        }
        SelectProblem=null;
        rs.setStatuse(200);
        rs.setMassege("ok");
        return rs;
    }

    @GetMapping("/getProblemSelected")
    public AdminProblemModel getse()
    {
        AdminProblemModel rs=new AdminProblemModel();
        if (this.SelectProblem==null)
        {
            return null;
        }
        rs.setId(this.SelectProblem.getId());
        rs.setEmail(this.SelectProblem.getEnduser().getName());
        rs.setDate(calc.getCurrentShamsidate(this.SelectProblem.getDate()));
        rs.setDesc(this.SelectProblem.getDescription());
        rs.setTitle(this.SelectProblem.getTitle());
        rs.setStatuse(this.SelectProblem.getStatuse());
        Res_AnswerModel reans=new Res_AnswerModel();
        if (this.SelectProblem.getAmswer()!=null)
        {
            reans.setAnswer(this.SelectProblem.getAmswer().getAnswer());
            reans.setTime(this.SelectProblem.getAmswer().getTime());
        }
        else {
            reans.setTime("");
            reans.setAnswer("");
        }
        rs.setAnswer(reans);
        return rs;

    }




    @PostMapping("/ProblemSelect")
    public ResponseStatuseModel SelectProblem(@RequestBody Res_ProblemModel Re_prob, HttpServletRequest request)
    {

        ResponseStatuseModel re = new ResponseStatuseModel();
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo == null) {
                re.setStatuse(504);
                re.setMassege("no found user");
            }
            else if (uo.get().getRole() == 2) {
                Problem po = proUC.getById(Re_prob.getId());
                if (po == null) {
                    re.setStatuse(404);
                    re.setMassege("پیدا نشد");
                }
                else {

                    re.setStatuse(200);
                    re.setMassege("ok");
                    this.SelectProblem = po;
                }
            }
        }
        else {
            re.setStatuse(505);
            re.setMassege("no access");
        }
        return re;
    }


    @GetMapping("/getselected")
    public Res_ProblemModel getselected()
    {
        Res_ProblemModel rs=new Res_ProblemModel();
        if (this.SelectProblem==null)
        {
            return null;
        }
        rs.setId(this.SelectProblem.getId());
        rs.setAdminTitle(this.SelectProblem.getAdmin().getAdminTitle());
        rs.setDate(calc.getCurrentShamsidate(this.SelectProblem.getDate()));
        rs.setDesc(this.SelectProblem.getDescription());
        rs.setTitle(this.SelectProblem.getTitle());
        rs.setStatuse(this.SelectProblem.getStatuse());
        Res_AnswerModel reans=new Res_AnswerModel();
        if (this.SelectProblem.getAmswer()!=null)
        {
            reans.setAnswer(this.SelectProblem.getAmswer().getAnswer());
            reans.setTime(this.SelectProblem.getAmswer().getTime());
        }
        else {
            reans.setTime("");
            reans.setAnswer("");
        }
        rs.setAnswer(reans);
        return rs;


    }

    @PostMapping("/select")
    public ResponseStatuseModel SelectProb(@RequestBody Res_ProblemModel Re_prob,HttpServletRequest request)
    {
        ResponseStatuseModel re = new ResponseStatuseModel();
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));

            if (uo == null) {
                re.setStatuse(504);
                re.setMassege("no found user");
            }
            else if (uo.get().getRole() == 3) {
                Problem po = proUC.getById(Re_prob.getId());
                if (po == null) {
                    re.setStatuse(404);
                    re.setMassege("پیدا نشد");
                }
                else {

                    re.setStatuse(200);
                    re.setMassege("ok");
                    this.SelectProblem = po;
                }
            }
        }
        else {
            re.setStatuse(505);
            re.setMassege("no access");
        }
        return re;
    }

    @GetMapping("/allProblem")
    public Iterable<Res_ProblemModel> allProblem(HttpServletRequest request)
    {
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo.get().getRole() == 1) {
                List<Problem> poli= proUC.getAll();
                ArrayList<Res_ProblemModel> res_proli=new ArrayList<Res_ProblemModel>();
                for (Problem x:poli)
                {

                    Res_ProblemModel respo=new Res_ProblemModel();
                    respo.setStatuse(x.getStatuse());
                    res_proli.add(respo);
                }
                return res_proli;
            }
        }
        return null;
    }

    @GetMapping("/allend")
    public Iterable<Res_ProblemModel> allprobend(HttpServletRequest request)
    {

        if (request.getSession().getAttribute("userId")!=null) {
            enduser uo = enUC.findbyId((Long) request.getSession().getAttribute("userId"));
            if (uo.getRole()==3)
            {
                if(proUC.allproblem(uo)==null)
                {
                    return null;
                }
                List<Problem> poli= proUC.allproblem(uo);
                ArrayList<Res_ProblemModel> res_proli=new ArrayList<Res_ProblemModel>();
                poli.sort(new Comparator<Problem>() {
                    @Override
                    public int compare(Problem o1, Problem o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });
                for (Problem x:poli)
                {
                    Res_ProblemModel respo=new Res_ProblemModel();
                    respo.setId(x.getId());
                    respo.setAdminTitle(x.getAdmin().getAdminTitle());
                    respo.setDate(calc.getCurrentShamsidate(x.getDate()));
                    respo.setDesc(x.getDescription());
                    respo.setTitle(x.getTitle());
                    respo.setStatuse(x.getStatuse());
                    Res_AnswerModel reans=new Res_AnswerModel();
                    if (x.getAmswer()!=null)
                    {
                        reans.setAnswer(x.getAmswer().getAnswer());
                        reans.setTime(x.getAmswer().getTime());
                    }
                    else {
                        reans.setTime("");
                        reans.setAnswer("");
                    }
                    respo.setAnswer(reans);
                    res_proli.add(respo);
                }
                return res_proli;
            }
        }
        return null;


    }
    @PostMapping("/edit")
    public ResponseStatuseModel edit_po(@RequestBody AdminProblemModel Re_prob,HttpServletRequest request)
    {
        ResponseStatuseModel re=new ResponseStatuseModel();
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo==null)
            {
                re.setStatuse(505);
                re.setMassege("user found");
            }
            else if (uo.get().getRole()==2)
            {
                Problem entity=this.proUC.getById(Re_prob.getId());
                if (entity!=null&&entity.getAdmin().getId()==uo.get().getId())
                {
                    if (Re_prob.getAnswer().getAnswer().equals("")&&Re_prob.getAnswer().getTime().equals(""))
                    {
                        re.setStatuse(403);
                        re.setMassege("emty error");
                        return re;
                    }
                    entity.setAmswer(new Answer());
                    entity.getAmswer().setAnswer(Re_prob.getAnswer().getAnswer());

                    entity.getAmswer().setTime(Re_prob.getAnswer().getTime());
                    entity.setStatuse(2);
                    if(proUC.edit(entity)!=null)
                    {
                        re.setStatuse(200);
                        re.setMassege("ok");
                        this.SelectProblem=entity;
                    }
                    else
                    {
                        re.setStatuse(404);
                        re.setMassege("failed");
                    }
                }
                else {
                    re.setStatuse(506);
                    re.setMassege("no found");
                }
            }
        }
        else {
            re.setStatuse(504);
            re.setMassege("no access");
        }
        return re;
    }

    @PostMapping("/chekok")
    public ResponseStatuseModel chekok(@RequestBody AdminProblemModel Re_prob,HttpServletRequest request)
    {
        ResponseStatuseModel re=new ResponseStatuseModel();
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo==null)
            {
                re.setStatuse(505);
                re.setMassege("user found");
            }
            else if (uo.get().getRole()==2)
            {
                Problem entity=this.proUC.getById(Re_prob.getId());
                if (entity!=null&&entity.getAdmin().getId()==uo.get().getId())
                {
                    Problem resentity=proUC.chekok(entity);
                    if(resentity!=null)
                    {
                        re.setStatuse(200);
                        re.setMassege("ok");
                        this.SelectProblem=resentity;
                    }
                    else
                    {
                        re.setStatuse(404);
                        re.setMassege("failed");
                    }
                }
                else {
                    re.setStatuse(506);
                    re.setMassege("no found");
                }
            }
        }
        else {
            re.setStatuse(504);
            re.setMassege("no access");
        }
        return re;
    }

    @PostMapping("/add")
    public ResponseStatuseModel add(@RequestBody ProblemModel Prob,HttpServletRequest request)
    {
        ResponseStatuseModel re=new ResponseStatuseModel();
        if (request.getSession().getAttribute("userId")!=null) {
            Optional<user> uo = usUC.findbyid((Long) request.getSession().getAttribute("userId"));
            if (uo == null) {
                re.setStatuse(505);
                re.setMassege("user found");
            } else if (uo.get().getRole() == 3) {
                Problem pro=new Problem();
                pro.setDescription(Prob.getDesc());
                pro.setTitle(Prob.getTitle());
                pro.setEnduser(enUC.findbyId((Long) request.getSession().getAttribute("userId")));
                pro.setAdmin(adminUC.getByname(Prob.getAdmin()));
                proUC.add(pro);
                re.setStatuse(200);
                re.setMassege("ok");
            }
        }
        return re;
    }

}
