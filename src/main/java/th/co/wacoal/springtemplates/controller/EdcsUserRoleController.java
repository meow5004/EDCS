/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserAuthTypeDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserTypeDAO;
import th.co.wacoal.springtemplates.dao.EdcsUserRoleDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasDepartmentDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserAuthTypeDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserTypeDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsUserRoleDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;
import th.co.wacoal.springtemplates.domain.EdcsUserRole;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/userRole")
public class EdcsUserRoleController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView userRoleCRUD(Model model, HttpSession session) {
        return new ModelAndView("master/userRole/index");
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateFormat.setLenient(false);
        //for attachHeader
        binder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/getUsersForRoleChange.htm")
    public void getUsersForRoleChange(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);

            List<EdcsMasUser> user = userDAO.findAll();

            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> userMap = new ArrayList<Map>();
            // Do something
            for (EdcsMasUser row : user) {
                Map p = new HashMap();
                p.put("empId", row.getEmpId());
                p.put("userName", row.getUserName());
                p.put("sysName", row.getSysName());
                p.put("phone", row.getPhone());
                String showRoleRow = "<button class='showRow btn btn-primary btn-sm' data-value='" + row.getEmpId() + "'></i> <i class=\"fa fa-cog\"></i></button> ";
                p.put("rowChangeActionColumn", showRoleRow);
                userMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(userMap);
            responseMessage = "{" + "\"size\":\"" + user.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responseMessage);
        }
        //Go to view
    }

    @RequestMapping("/showUserAuthorization.htm")
    public ModelAndView showUserAuthorization(@RequestParam("empId") String empId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        ModelAndView mv = new ModelAndView("master/userRole/editRole");
        try {
            EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
            EdcsMasUserAuthTypeDAO authDAO = new EdcsMasUserAuthTypeDAOImpI(db);
            EdcsMasUserTypeDAO typeDAO = new EdcsMasUserTypeDAOImpI(db);
            EdcsMasDepartmentDAO deptDAO = new EdcsMasDepartmentDAOImpl(db);
            userDAO = new EdcsMasUserDAOImpI(db);

            EdcsMasUser user = userDAO.find(empId);
//user.set
            //checkboxes
            mv.addObject("deps", deptDAO.findByFlag(0));
            mv.addObject("auths", authDAO.findAll());
            mv.addObject("types", typeDAO.findAll());
            //end
            mv.addObject("user", user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }

    }

    @RequestMapping(value = "/editUserAuthorization.htm", method = RequestMethod.POST)
    public void editAuthByempId(@ModelAttribute("user") EdcsMasUser userEdited, Model model, HttpSession session, HttpServletResponse response) throws IOException, SQLException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsUserRoleDAO userRoleDAO = new EdcsUserRoleDAOImpI(db);
            String userId = (String) session.getAttribute("userId");

            userRoleDAO = new EdcsUserRoleDAOImpI(db);
            List<EdcsUserRole> roles = new ArrayList<>();
            for (Integer typeId : userEdited.getUserTypeIds()) {
                for (Integer authId : userEdited.getUserAuthTypeIds()) {
                    for (String depId : userEdited.getViewableDepartmentIds()) {
                        if (typeId != null && depId != null && authId != null) {
                            EdcsUserRole role = new EdcsUserRole();
                            role.setUserTypeId(typeId);
                            role.setEmpId(userEdited.getEmpId());
                            role.setDepId(depId);
                            role.setAuthTypeId(authId);
                            role.setChangeBy(userId);
                            role.setCreateBy(userId);
                            roles.add(role);
                        }
                    }
                }
            }
            userRoleDAO.recreateUserRole(roles);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.write("เปลี่ยนแปลงสิทธิ์การเข้าถึงสำเร็จ");
        }

    }

}
