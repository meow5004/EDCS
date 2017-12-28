/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import th.co.wacoal.springtemplates.dao.EdcsMasBranchDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasBranchDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasDepartmentDAOImpl;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasBranch;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/branchs")
public class EdcsMasBranchController {

    Database db = new Database("sqlServer");
    EdcsMasBranchDAO branchDAO = new EdcsMasBranchDAOImpl(db);
    EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
    String responseMessage = "please enter at least one name";
    PrintWriter out;

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

   DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy",Locale.US);

    @RequestMapping("/index")
    public ModelAndView branchCRUD(Model model, HttpSession session) {
        try {
            // Model
            return new ModelAndView("master/branchs/index");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ModelAndView("error", "error", ex.getMessage());
        } finally {
            //db.close();
        }
    }

    //add Data
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model, HttpSession session) {
        EdcsMasBranch branch = new EdcsMasBranch();
        ModelAndView mv = new ModelAndView("master/branchs/add");
        mv.addObject("branch", branch);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("branch") EdcsMasBranch branch, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(branch) >= 1) {

                String userId = (String) session.getAttribute("userId");
                branch.setCreateBy(userId);
                branch.setChangeBy(userId);

                branchDAO.add(branch);
                responseMessage = "succesfully add BRANCH " + branchDAO.formatFullName(branch.getBranchNameTh(), branch.getBranchNameEn());
            }

            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }
    //edit Data

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(value = "id") int id, Model model, HttpSession session) {
        EdcsMasBranch editingBranch = branchDAO.find(id);
        ModelAndView mv = new ModelAndView("master/branchs/edit");
        mv.addObject("branch", editingBranch);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("branch") EdcsMasBranch branch, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(branch) >= 1) {

                String userId = (String) session.getAttribute("userId");
                branch.setChangeBy(userId);

                branchDAO.update(branch);
                responseMessage = "succesfully edit BRANCH " + branchDAO.formatFullName(branch.getBranchNameTh(), branch.getBranchNameEn());
            }

            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }
    //edit Data

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("id") int id, Model model, HttpSession session) {
        EdcsMasBranch deletingBranch = branchDAO.find(id);
        ModelAndView mv = new ModelAndView("master/branchs/delete");
        mv.addObject("branch", deletingBranch);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("branchId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            branchDAO.delete(id, userId);
            responseMessage = "succesfully delete BRANCH ID " + id;
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    @RequestMapping(value = "/mutipleDelete", method = RequestMethod.POST)
    public void mutipleDeleteConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = branchDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " branchs";
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    @RequestMapping(value = "/mutipleRealDelete", method = RequestMethod.POST)
    public void mutipleRealDeleteConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String[] ids = idArray.split(",");
            int valid = 1;
            for (String id : ids) {
                int idNum = Integer.parseInt(id);
                if (departmentDAO.findByBranchId(idNum).size() > 0) {
                    valid = 0;
                }
            }
            responseMessage = "<font color='red'>ยังมีแผนกอยู่ในระบบ <br/>ไม่สามารถลบถาวรได้</font>";
            if (valid == 1) {
                int result = branchDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
                responseMessage = "succesfully permanantly delete " + result + " branchs";
            }
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.GET)
    public ModelAndView reuse(@RequestParam("id") int id, Model model, HttpSession session) {
        EdcsMasBranch reuseBranch = branchDAO.find(id);
        ModelAndView mv = new ModelAndView("master/branchs/reuse");
        mv.addObject("branch", reuseBranch);
        return mv;
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("branchId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            branchDAO.reuse(id, userId);
            responseMessage = "succesfully reuse BRANCH ID " + id;
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    @RequestMapping(value = "/mutipleReuse", method = RequestMethod.POST)
    public void mutipleReuseConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = branchDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " branchs";
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    private int validateInput(EdcsMasBranch branch) {
        responseMessage = "";
        int valid = 1;
        if (branch.getBranchNameEn() == null && branch.getBranchNameEn().trim().length() <= 0
                && branch.getBranchNameTh() == null && branch.getBranchNameTh().trim().length() <= 0) {
            valid = 0;
            responseMessage = "please enter at least one name";
        }

        Integer id = branch.getBranchId();
        if (id == null) {
            id = 0;
        }

        if ((branchDAO.isExistCount(branch.getBranchNameTh(), branch.getBranchNameEn(), id.toString())) > 0) {
            valid = 0;
        }
        //return 1 if valid
        return valid;
    }

    @RequestMapping("/getAvailableBranch.htm")
    public void getAvailableBranch(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasBranch> branchs = branchDAO.findByFlag(0);

        // Create JSON
        JSONObject json = new JSONObject();
        json.put("total", branchs.size());
        List<Map> branchMap = new ArrayList<Map>();
        String edit = messageSource.getMessage("branch.edit", null, thaiLocale);
        String delete = messageSource.getMessage("branch.delete", null, thaiLocale);
        // Do something
        for (EdcsMasBranch row : branchs) {
            Map p = new HashMap();
            p.put("branchId", row.getBranchId());
            p.put("branchNameTh", row.getBranchNameTh());
            p.put("branchNameEn", row.getBranchNameEn());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

            String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getBranchId() + "'></i>" + edit + "</button> ";
            //+ "<button class='deleteData' value='./delete.htm?id=" + row.getBranchId() + "'>"+delete+"</button>";
            p.put("actionLink", actionLink);
            String deleteCheckbox = "<font color='red'>มีแผนกใช้งานอยู่ <br/>ไม่สามารถลบได้</font>";
            if (departmentDAO.findByBranchIdByFlag(row.getBranchId(), "0").size() <= 0) {
                deleteCheckbox = " <input type=\"checkbox\" name=\"deletedBranch\" value=\"" + row.getBranchId() + "\">";
            }
            p.put("deleteCheck", deleteCheckbox);
            branchMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(branchMap);
        json.put("data", jsonString);
        //Go to view
        out.print("{" + "\"size\":\"" + branchs.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getUnavailableBranch.htm")
    public void getUnavailableBranch(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        List<EdcsMasBranch> branchs = branchDAO.findByFlag(1);
        String reuse = messageSource.getMessage("branch.reuse", null, thaiLocale);
        // Create JSON
        JSONObject json = new JSONObject();
        json.put("total", branchs.size());
        List<Map> branchMap = new ArrayList<Map>();
        for (EdcsMasBranch row : branchs) {
            Map p = new HashMap();
            p.put("branchId", row.getBranchId());
            p.put("branchNameTh", row.getBranchNameTh());
            p.put("branchNameEn", row.getBranchNameEn());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getBranchId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
            String reuseCheckbox = " <input type=\"checkbox\" name=\"reuseBranch\" value=\"" + row.getBranchId() + "\">";
            p.put("reuseCheck", reuseCheckbox);

            String realDeleteCheckbox = "<font color='red'>ยังมีแผนกอยู่ในระบบ <br/>ไม่สามารถลบถาวรได้</font>";
            if (departmentDAO.findByBranchId(row.getBranchId()).size() <= 0) {
                realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedBranch\" value=\"" + row.getBranchId() + "\">";
            }

            p.put("realDeleteCheck", realDeleteCheckbox);
            branchMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(branchMap);
        json.put("data", jsonString);
        //Go to view
        out.print("{" + "\"size\":\"" + branchs.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("nameTh") String nameTh, @RequestParam("nameEn") String nameEn, @RequestParam(value = "id", required = false) String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            int count = branchDAO.isExistCount(nameTh.trim(), nameEn.trim(), id);
            responseMessage = "";
            if (count > 0) {
                responseMessage = "มีสาขา " + nameTh + " ( " + nameEn + " ) ในระบบแล้ว";
            }

            out.write(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.write(ex.getMessage());
        } finally {
            //db.close();
        }
    }
}
