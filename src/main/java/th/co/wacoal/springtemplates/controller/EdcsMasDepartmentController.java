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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/departments")
public class EdcsMasDepartmentController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView departmentCRUD(Model model, HttpSession session) {
        return new ModelAndView("master/departments/index");
    }

    //add Data
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model, HttpSession session) {
        EdcsMasDepartment department = new EdcsMasDepartment();
        ModelAndView mv = new ModelAndView("master/departments/add");
        Database db = new Database("sqlServer");

        try {
            //list branch
            EdcsMasBranchDAO branchDAO = new EdcsMasBranchDAOImpl(db);
            List<EdcsMasBranch> branchs = branchDAO.findByFlag(0);
            mv.addObject("department", department);
            mv.addObject("branchs", branchs);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("department") EdcsMasDepartment department, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasBranchDAO branchDAO = new EdcsMasBranchDAOImpl(db);
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);

            EdcsMasBranch branch = branchDAO.find(department.getBranchId());

            if (validateInput(department) >= 1) {
                String userId = (String) session.getAttribute("userId");
                department.setCreateBy(userId);
                department.setChangeBy(userId);

                departmentDAO.add(department);
                responseMessage = "add DEPARTMENT " + department.getDepNameTh() + "(" + department.getDepNameEn() + ") " + "in to BRANCH " + branch.getFullName();
            } else {
                responseMessage = "<span style='color:red'>repeated ID please enter different ID<span>";
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {

            db.close();
            out.print(responseMessage);
        }
    }
    //edit Data

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("id") String id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        ModelAndView mv = new ModelAndView("master/departments/edit");
        try {
            EdcsMasBranchDAO branchDAO = new EdcsMasBranchDAOImpl(db);
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            EdcsMasDepartment editingDepartment = departmentDAO.find(id);

            mv.addObject("department", editingDepartment);

            //list branch
            List<EdcsMasBranch> branchs = branchDAO.findByFlag(0);
            // add empty branch in case of deleted branch
            //branchs.add(0, new EdcsMasBranch()); (comment out because validation check before delete branch)
            mv.addObject("branchs", branchs);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("department") EdcsMasDepartment department, @RequestParam("oldDepId") String oldDepId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        try {
            EdcsMasBranchDAO branchDAO = new EdcsMasBranchDAOImpl(db);
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);

            EdcsMasBranch branch = branchDAO.find(department.getBranchId());
            if (validateInput(department, oldDepId) >= 1) {

                String userId = (String) session.getAttribute("userId");
                department.setChangeBy(userId);

                departmentDAO.update(department, oldDepId);
                responseMessage = "edit DEPARTMENT " + department.getDepNameTh() + "(" + department.getDepNameEn() + ") " + "in  BRANCH " + branch.getFullName();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }
    //edit Data

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("id") String id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        ModelAndView mv = new ModelAndView("master/departments/delete");
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            EdcsMasDepartment deletingDepartment = departmentDAO.find(id);
            mv.addObject("department", deletingDepartment);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("depId") String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            String userId = (String) session.getAttribute("userId");
            departmentDAO.delete(id, userId);
            responseMessage = "succesfully delete DEPARTMENT ID " + id;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/mutipleDelete", method = RequestMethod.POST)
    public void mutipleDeleteConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = departmentDAO.deleteMutiple(ids, userId);
            responseMessage = "succesfully delete " + result + " departments";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.printf(responseMessage);
        }
    }

    @RequestMapping(value = "/mutipleRealDelete", method = RequestMethod.POST)
    public void mutipleRealDeleteConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            String[] ids = idArray.split(",");
            int result = departmentDAO.realDeleteMutiple(ids);
            responseMessage = "succesfully permanantly delete " + result + " departments";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.GET)
    public ModelAndView reuse(@RequestParam("id") String id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        ModelAndView mv = new ModelAndView("master/departments/reuse");
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            EdcsMasDepartment reusingDepartment = departmentDAO.find(id);

            mv.addObject("department", reusingDepartment);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("depId") String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            String userId = (String) session.getAttribute("userId");
            departmentDAO.reuse(id, userId);
            responseMessage = "succesfully reuse DEPARTMENT ID " + id;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            out.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/mutipleReuse", method = RequestMethod.POST)
    public void mutipleReuseConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            responseMessage = "";
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = departmentDAO.reuseMutiple(ids, userId);
            responseMessage = "succesfully reuse " + result + " departments";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.printf(ex.getMessage());
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/checkRepeatId")
    public void checkRepeatId(@RequestParam("id") String id, @RequestParam("oldDepId") String oldDepId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);

            EdcsMasDepartment inDbDepartment = null;
            if (!(oldDepId.equals(id))) {
                inDbDepartment = departmentDAO.find(id);
            }
            responseMessage = "";
            if (inDbDepartment != null) {
                if (inDbDepartment.getDepId() != null) {
                    responseMessage = "repeated ID. please change ID";
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.write(responseMessage);
        }
    }

    //for add
    private int validateInput(EdcsMasDepartment department) {
        Database db = new Database("sqlServer");
        EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
        int valid = 1;
        try {
            EdcsMasDepartment inDbDepartment = departmentDAO.find(department.getDepId());

            // check if at least one name if filled in
            if (department.getDepNameEn() == null && department.getDepNameEn().trim().length() <= 0
                    && department.getDepNameTh() == null && department.getDepNameTh().trim().length() <= 0) {
                valid = 0;
            }

            //check if ID repeated or no branch entered
            if (department.getBranchId() == null || department.getBranchId() == null) {
                valid = 0;
            }

            if (inDbDepartment != null) {
                if (inDbDepartment.getDepId() != null) {
                    valid = 0;
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            //return 1 if valid
            return valid;
        }
    }

    //for Edit
    private int validateInput(EdcsMasDepartment department, String oldDepId) {
        Database db = new Database("sqlServer");
        int valid = 1;
        try {
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);
            EdcsMasDepartment inDbDepartment = null;
            if (!(oldDepId.equals(department.getDepId()))) {
                inDbDepartment = departmentDAO.find(department.getDepId());
            }

            // check if at least one name if filled in
            if (department.getDepNameEn() == null && department.getDepNameEn().trim().length() <= 0
                    && department.getDepNameTh() == null && department.getDepNameTh().trim().length() <= 0) {
                valid = 0;
            }

            //check if ID repeated or no branch entered
            if (department.getBranchId() == null || department.getBranchId() == null) {
                valid = 0;
            }

            if (inDbDepartment != null) {
                if (inDbDepartment.getDepId() != null) {
                    valid = 0;
                }
            }

            //return 1 if valid
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return valid;
        }
    }

    @RequestMapping("/getAvailableDepartment.htm")
    public void getAvailableDepartment(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasBranchDAO branchDAO = new EdcsMasBranchDAOImpl(db);
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);

            List<EdcsMasDepartment> departments = departmentDAO.findByFlag(0);
            String edit = messageSource.getMessage("branch.edit", null, thaiLocale);
            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> departmentMap = new ArrayList<Map>();
            for (EdcsMasDepartment row : departments) {
                Map p = new HashMap();
                EdcsMasBranch branch = branchDAO.find(row.getBranchId());
                p.put("associateBranchName", branch.getFullName());
                p.put("depId", row.getDepId());
                p.put("depNameTh", row.getDepNameTh());
                p.put("depNameEn", row.getDepNameEn());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getCreateBy());
                p.put("changeOn", df.format(row.getChangeOn()));
                String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getDepId() + "'></i>" + edit + "</button> ";
                //+ "<button class='deleteData' value='./delete.htm?id=" + row.getBranchId() + "'>"+delete+"</button>";
                p.put("actionLink", actionLink);

                String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedDep\" value=\"" + row.getDepId() + "\">";
                p.put("deleteCheck", deleteCheckbox);
                departmentMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(departmentMap);

            //Go to view
            out.print("{" + "\"size\":\"" + departments.size() + "\",\"data\":" + jsonString + "}");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.write(responseMessage);
        }
    }

    @RequestMapping("/getUnavailableDepartment.htm")
    public void getUnavailableDepartment(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasBranchDAO branchDAO = new EdcsMasBranchDAOImpl(db);
            EdcsMasDepartmentDAO departmentDAO = new EdcsMasDepartmentDAOImpl(db);

            List<EdcsMasDepartment> departments = departmentDAO.findByFlag(1);
            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> departmentMap = new ArrayList<Map>();
            for (EdcsMasDepartment row : departments) {
                Map p = new HashMap();
                EdcsMasBranch branch = branchDAO.find(row.getBranchId());
                p.put("associateBranchName", branch.getFullName());
                p.put("depId", row.getDepId());
                p.put("depNameTh", row.getDepNameTh());
                p.put("depNameEn", row.getDepNameEn());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getCreateBy());
                p.put("changeOn", df.format(row.getChangeOn()));
                String reuseCheckbox = "<font color='red'>สาขาของแผนกถูกลบ <br/>กรุณากู้คืนสาขาก่อนแผนก</font>";
                if ("0".equals(branch.getFlagDel())) {
                    reuseCheckbox = " <input type=\"checkbox\" name=\"reuseDep\" value=\"" + row.getDepId() + "\">";
                }
                p.put("reuseCheck", reuseCheckbox);

                String realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedDep\" value=\"" + row.getDepId() + "\">";
                p.put("realDeleteCheck", realDeleteCheckbox);

                departmentMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(departmentMap);

            //Go to view
            out.print("{" + "\"size\":\"" + departments.size() + "\",\"data\":" + jsonString + "}");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.write(responseMessage);
        }
    }
}
