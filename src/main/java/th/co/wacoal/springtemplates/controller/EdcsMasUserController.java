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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasDepartmentDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;
import th.co.wacoal.vehiclebooking.ldap.LdapEmployeeProfile;
import th.co.wacoal.vehiclebooking.ldap.LdapEmployeeProfileDAO;
import th.co.wacoal.vehiclebooking.ldap.LdapEmployeeProfileDAOImpl;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/user")
public class EdcsMasUserController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView userCRUD(Model model, HttpSession session) {
        return new ModelAndView("master/user/index");
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateFormat.setLenient(false);
        //for attachHeader
        binder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }

    //add Data
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model, HttpSession session) {
        Database db = new Database("sqlServer");

        EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
        ModelAndView mv = new ModelAndView("master/user/add");
        try {
            depDAO = new EdcsMasDepartmentDAOImpl(db);
            EdcsMasUser user = new EdcsMasUser();

            List<EdcsMasDepartment> departments = depDAO.findByFlag(0);
            mv.addObject("user", user);
            mv.addObject("departments", departments);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("user") EdcsMasUser user, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            if (validateInput(user) >= 1) {

                String userId = (String) session.getAttribute("userId");
                user.setCreateBy(userId);
                user.setChangeBy(userId);

                userDAO.add(user);
                responseMessage = "เพิ่มผู้ใช้รหัส" + user.getEmpId();
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
    public ModelAndView edit(@RequestParam(value = "id") String id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        ModelAndView mv = new ModelAndView("master/user/edit");
        EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
        try {
            EdcsMasUser editingUserId = userDAO.find(id);
            depDAO = new EdcsMasDepartmentDAOImpl(db);
            List<EdcsMasDepartment> departments = depDAO.findByFlag(0);

            mv.addObject("departments", departments);
            mv.addObject("user", editingUserId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("user") EdcsMasUser user, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {

            String userId = (String) session.getAttribute("userId");
            user.setChangeBy(userId);

            userDAO.update(user);
            responseMessage = "แก้ไขผู้ใช้รหัส" + user.getEmpId();

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
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        ModelAndView mv = new ModelAndView("master/user/delete");
        try {
            EdcsMasUser deletingUserId = userDAO.find(id);
            mv.addObject("user", deletingUserId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("empId") String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            userDAO.delete(id);
            responseMessage = "ลบผู้ใช้รหัส " + id;

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
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = userDAO.deleteMutiple(ids);
            responseMessage = "ลบผู้ใช้รหัส " + idArray;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    private int validateInput(EdcsMasUser user) {
        Database db = new Database("sqlServer");
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        int valid = 1;
        try {

            if (userDAO.isExistCount(user) > 0) {
                valid = 0;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            db.close();
            return valid;
        }

    }

    @RequestMapping("/getAvailableUser.htm")
    public void getAvailableUserId(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {

            List<EdcsMasUser> user = userDAO.findAll();

            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> userMap = new ArrayList<Map>();
            String edit = messageSource.getMessage("user.edit", null, thaiLocale);
            String delete = messageSource.getMessage("user.delete", null, thaiLocale);
            depDAO = new EdcsMasDepartmentDAOImpl(db);
            // Do something
            for (EdcsMasUser row : user) {
                Map p = new HashMap();
                p.put("empId", row.getEmpId());
                p.put("depId", row.getDepId());
                p.put("depName", depDAO.find(row.getDepId()).getFullName());
                p.put("userName", row.getUserName());
                p.put("sysName", row.getSysName());
                p.put("phone", row.getPhone());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));

                String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getEmpId() + "'></i>" + edit + "</button> ";
                //+ "<button class='deleteData' value='./delete.htm?id=" + row.getUserId() + "'>"+delete+"</button>";
                p.put("actionLink", actionLink);
                String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedUserId\" value=\"" + row.getEmpId() + "\">";

                p.put("deleteCheck", deleteCheckbox);
                userMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(userMap);

            //Go to view
            responseMessage = "{" + "\"size\":\"" + user.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping("/getUsersFromLdapByWildCardEmpId.htm")
    public void getNearDueAndNewCalibration(@RequestParam("wildcardEmpId") String wildcardEmpId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responResult = null;
        try {
            LdapEmployeeProfileDAO ldapEmployeeProfileDAO = new LdapEmployeeProfileDAOImpl();
            List<LdapEmployeeProfile> profiles = new ArrayList<>();
            List<Map> userMap = new ArrayList<Map>();
            profiles = ldapEmployeeProfileDAO.findUserStartsWith(wildcardEmpId);

            //match profile with for select input
            for (LdapEmployeeProfile profile : profiles) {
                Map p = new HashMap();
                String userName = profile.getEmpNameTh() + " " + profile.getEmpSurnameTh();
                p.put("label", userName);
                p.put("value", profile.getUid());
                userMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(userMap);
            //Go to view
            responResult = jsonString.toString();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(EdcsMasUser user, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            responseMessage = "";
            if (userDAO.isExistCount(user) > 0 && user != null && user.getEmpId() != null) {
                responseMessage = " มีผู้ใช้รหัส " + user.getEmpId() + "แล้ว";
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }
}
