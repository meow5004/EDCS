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
import th.co.wacoal.springtemplates.dao.EdcsMasUserAuthTypeDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserAuthTypeDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasUserAuthType;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/userAuthType")
public class EdcsMasUserAuthTypeController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView userCRUD(Model model, HttpSession session) {
        return new ModelAndView("master/userAuthType/index");
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
        EdcsMasUserAuthType userAuthType = new EdcsMasUserAuthType();
        ModelAndView mv = new ModelAndView("master/userAuthType/add");
        mv.addObject("userAuthType", userAuthType);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("userAuthType") EdcsMasUserAuthType userAuthType, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            if (validateInput(userAuthType) >= 1) {

                String userId = (String) session.getAttribute("userId");
                userAuthType.setCreateBy(userId);
                userAuthType.setChangeBy(userId);

                userAuthTypeDAO.add(userAuthType);
                responseMessage = "เพิ่มสิทธิ์ " + userAuthType.getAuthTypeNameTh() + " " + userAuthType.getAuthTypeNameEn();
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
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        ModelAndView mv = new ModelAndView("master/userAuthType/edit");
        try {
            EdcsMasUserAuthType editingUserId = userAuthTypeDAO.find(id);

            mv.addObject("userAuthType", editingUserId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("userAuthType") EdcsMasUserAuthType userAuthType, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            if (validateInput(userAuthType) >= 1) {

                String userId = (String) session.getAttribute("userId");
                userAuthType.setChangeBy(userId);

                userAuthTypeDAO.update(userAuthType);
                responseMessage = "แก้ไขสิทธิ์ " + userAuthType.getAuthTypeNameTh() + " " + userAuthType.getAuthTypeNameEn();
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
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        ModelAndView mv = new ModelAndView("master/userAuthType/delete");
        try {
            EdcsMasUserAuthType deletingUserId = userAuthTypeDAO.find(id);

            mv.addObject("userAuthType", deletingUserId);

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
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String[] ids = idArray.split(",");
            int result = userAuthTypeDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "ลบสิทธิ์รหัส " + idArray;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("authTypeId") Integer id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            userAuthTypeDAO.delete(id);
            responseMessage = "ลบสิทธิ์รหัส " + id;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    private int validateInput(EdcsMasUserAuthType userAuthType) {
        int valid = 1;
        Database db = new Database("sqlServer");
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        try {
            if (userAuthTypeDAO.isExistCount(userAuthType) > 0) {
                valid = 0;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return valid;
        }
    }

    @RequestMapping("/getAvailableUserAuthType.htm")
    public void getAvailableUserAuthType(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {

            List<EdcsMasUserAuthType> userAuthType = userAuthTypeDAO.findAll();

            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> userMap = new ArrayList<Map>();
            String edit = messageSource.getMessage("userAuthType.edit", null, thaiLocale);
            String delete = messageSource.getMessage("userAuthType.delete", null, thaiLocale);
            // Do something
            for (EdcsMasUserAuthType row : userAuthType) {
                Map p = new HashMap();
                p.put("authTypeId", row.getAuthTypeId());
                p.put("authTypeNameTh", row.getAuthTypeNameTh());
                p.put("authTypeNameEn", row.getAuthTypeNameEn());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));

                String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getAuthTypeId() + "'></i>" + edit + "</button> ";
                //+ "<button class='deleteData' value='./delete.htm?id=" + row.getUserId() + "'>"+delete+"</button>";
                p.put("actionLink", actionLink);
                String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedUserAuthTypeId\" value=\"" + row.getAuthTypeId() + "\">";

                p.put("deleteCheck", deleteCheckbox);
                userMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(userMap);

            //Go to view
            responseMessage = "{" + "\"size\":\"" + userAuthType.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(EdcsMasUserAuthType userAuthType, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasUserAuthTypeDAO userAuthTypeDAO = new EdcsMasUserAuthTypeDAOImpI(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            responseMessage = "";
            if (userAuthTypeDAO.isExistCount(userAuthType) > 0) {
                responseMessage = " ชื่อซ้ำกับ auth type รหัส " + userAuthType.getAuthTypeId() + "แล้ว";
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
