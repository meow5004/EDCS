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
import th.co.wacoal.springtemplates.dao.EdcsMasUserTypeDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserTypeDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasUserType;

/**
 *
 * @useror admin
 */
@Controller
@RequestMapping("/userType")
public class EdcsMasUserTypeController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView userCRUD(Model model, HttpSession session) {
        return new ModelAndView("master/userType/index");
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
        EdcsMasUserType userType = new EdcsMasUserType();
        ModelAndView mv = new ModelAndView("master/userType/add");
        mv.addObject("userType", userType);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("userType") EdcsMasUserType userType, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            if (validateInput(userType) >= 1) {

                String userId = (String) session.getAttribute("userId");
                userType.setCreateBy(userId);
                userType.setChangeBy(userId);

                userTypeDAO.add(userType);
                responseMessage = "เพิ่มประเภทผู้ใช้ " + userType.getUserTypeNameTh() + " " + userType.getUserTypeNameEn();
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
    public ModelAndView edit(@RequestParam(value = "id") Integer id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        ModelAndView mv = new ModelAndView("master/userType/edit");
        try {
            EdcsMasUserType editingUserTypeId = userTypeDAO.find(id);

            mv.addObject("userType", editingUserTypeId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("userType") EdcsMasUserType userType, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            if (validateInput(userType) >= 1) {

                String userId = (String) session.getAttribute("userId");
                userType.setChangeBy(userId);

                userTypeDAO.update(userType);
                responseMessage = "แก้ไขประเภทผู้ใช้รหัส" + userType.getUserTypeId();
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
    public ModelAndView delete(@RequestParam("id") Integer id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        ModelAndView mv = new ModelAndView("master/userType/delete");
        try {
            EdcsMasUserType deletingUserId = userTypeDAO.find(id);
            mv.addObject("userType", deletingUserId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/mutipleDelete", method = RequestMethod.POST)
    public void mutipleDeleteConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String[] ids = idArray.split(",");
            int result = userTypeDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "ลบประเภทผู้ใช้รหัส " + idArray;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("userTypeId") Integer id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            userTypeDAO.delete(id);
            responseMessage = "ลบประเภทผู้ใช้รหัส " + id;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    private int validateInput(EdcsMasUserType userType) {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        int valid = 1;
        try {
            if (userTypeDAO.isExistCount(userType) > 0) {
                valid = 0;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return valid;
        }
    }

    @RequestMapping("/getAvailableUserType.htm")
    public void getAvailableUserType(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();

        try {
            List<EdcsMasUserType> userType = userTypeDAO.findAll();

            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> userMap = new ArrayList<Map>();
            String edit = messageSource.getMessage("userType.edit", null, thaiLocale);
            String delete = messageSource.getMessage("userType.delete", null, thaiLocale);
            // Do something
            for (EdcsMasUserType row : userType) {
                Map p = new HashMap();
                p.put("userTypeId", row.getUserTypeId());
                p.put("userTypeNameTh", row.getUserTypeNameTh());
                p.put("userTypeNameEn", row.getUserTypeNameEn());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));

                String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getUserTypeId() + "'></i>" + edit + "</button> ";
                //+ "<button class='deleteData' value='./delete.htm?id=" + row.getUserId() + "'>"+delete+"</button>";
                p.put("actionLink", actionLink);
                String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedUserTypeId\" value=\"" + row.getUserTypeId() + "\">";

                p.put("deleteCheck", deleteCheckbox);
                userMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(userMap);

            //Go to view
            responseMessage = "{" + "\"size\":\"" + userType.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(EdcsMasUserType userType, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserTypeDAO userTypeDAO = new EdcsMasUserTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            responseMessage = "";
            if (userTypeDAO.isExistCount(userType) > 0) {
                responseMessage = " มีชื่อซ้ำกับ user type " + userType.getUserTypeNameTh() + " " + userType.getUserTypeNameEn() + "แล้ว";
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
