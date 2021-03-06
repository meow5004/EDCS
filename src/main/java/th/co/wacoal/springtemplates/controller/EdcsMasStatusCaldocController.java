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
import th.co.wacoal.springtemplates.dao.EdcsMasStatusCaldocDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasStatusCaldocDAOImpl;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasStatusCaldoc;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/statusCaldocs")
public class EdcsMasStatusCaldocController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView statusCaldocCRUD(Model model, HttpSession session) {
        return new ModelAndView("master/statusCaldocs/index");
    }

    //add Data
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model, HttpSession session) {
        EdcsMasStatusCaldoc statusCaldoc = new EdcsMasStatusCaldoc();
        ModelAndView mv = new ModelAndView("master/statusCaldocs/add");
        mv.addObject("statusCaldoc", statusCaldoc);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("statusCaldoc") EdcsMasStatusCaldoc statusCaldoc, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            if (validateInput(statusCaldoc) >= 1) {

                String userId = (String) session.getAttribute("userId");
                statusCaldoc.setCreateBy(userId);
                statusCaldoc.setChangeBy(userId);

                statusCaldocDAO.add(statusCaldoc);
                responseMessage = "succesfully add CALCULATION DOCUMENT STATUS " + statusCaldoc.getStatusCaldocName();
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
    public ModelAndView edit(@RequestParam(value = "id") int id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        ModelAndView mv = new ModelAndView("master/statusCaldocs/edit");
        try {
            EdcsMasStatusCaldoc editingStatusCaldoc = statusCaldocDAO.find(id);

            mv.addObject("statusCaldoc", editingStatusCaldoc);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("statusCaldoc") EdcsMasStatusCaldoc statusCaldoc, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            if (validateInput(statusCaldoc) >= 1) {

                String userId = (String) session.getAttribute("userId");
                statusCaldoc.setChangeBy(userId);

                statusCaldocDAO.update(statusCaldoc);
                responseMessage = "succesfully edit CALCULATION DOCUMENT STATUS " + statusCaldoc.getStatusCaldocName();
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
    public ModelAndView delete(@RequestParam("id") int id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        ModelAndView mv = new ModelAndView("master/statusCaldocs/delete");
        try {
            EdcsMasStatusCaldoc deletingStatusCaldoc = statusCaldocDAO.find(id);

            mv.addObject("statusCaldoc", deletingStatusCaldoc);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("statusCaldocId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String userId = (String) session.getAttribute("userId");
            statusCaldocDAO.delete(id, userId);
            responseMessage = "succesfully delete CALCULATION DOCUMENT STATUS ID " + id;

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
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = statusCaldocDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " statusCaldocs";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/mutipleRealDelete", method = RequestMethod.POST)
    public void mutipleRealDeleteConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String[] ids = idArray.split(",");

            int result = statusCaldocDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " statusCaldocs";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.GET)
    public ModelAndView reuse(@RequestParam("id") int id, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        ModelAndView mv = new ModelAndView("master/statusCaldocs/reuse");
        try {
            EdcsMasStatusCaldoc reuseStatusCaldoc = statusCaldocDAO.find(id);
            mv.addObject("statusCaldoc", reuseStatusCaldoc);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("statusCaldocId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String userId = (String) session.getAttribute("userId");
            statusCaldocDAO.reuse(id, userId);
            responseMessage = "succesfully reuse CALCULATION DOCUMENT STATUS ID " + id;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/mutipleReuse", method = RequestMethod.POST)
    public void mutipleReuseConfirm(@RequestParam("idArray") String idArray, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = statusCaldocDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " statusCaldocs";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    private int validateInput(EdcsMasStatusCaldoc statusCaldoc) {

        int valid = 1;
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        try {
            if (statusCaldoc.getStatusCaldocName() == null && statusCaldoc.getStatusCaldocName().trim().length() <= 0) {
                valid = 0;
            }

            Integer id = statusCaldoc.getStatusCaldocId();
            if (id == null) {
                id = 0;
            }

            if ((statusCaldocDAO.isExistCount(statusCaldoc.getStatusCaldocName(), id.toString())) > 0) {
                valid = 0;
            }
            //return 1 if valid
            return valid;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return valid;
        }
    }

    @RequestMapping("/getAvailableStatusCaldoc.htm")
    public void getAvailableStatusCaldoc(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            List<EdcsMasStatusCaldoc> statusCaldocs = statusCaldocDAO.findByFlag(0);

            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> statusCaldocMap = new ArrayList<Map>();
            String edit = messageSource.getMessage("statusCaldoc.edit", null, thaiLocale);
            String delete = messageSource.getMessage("statusCaldoc.delete", null, thaiLocale);
            // Do something
            for (EdcsMasStatusCaldoc row : statusCaldocs) {
                Map p = new HashMap();
                p.put("statusCaldocId", row.getStatusCaldocId());
                p.put("statusCaldocName", row.getStatusCaldocName());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));

                String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getStatusCaldocId() + "'></i>" + edit + "</button> ";
                //+ "<button class='deleteData' value='./delete.htm?id=" + row.getStatusCaldocId() + "'>"+delete+"</button>";
                p.put("actionLink", actionLink);
                String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedStatusCaldoc\" value=\"" + row.getStatusCaldocId() + "\">";

                p.put("deleteCheck", deleteCheckbox);
                statusCaldocMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(statusCaldocMap);

            //Go to view
            responseMessage = "{" + "\"size\":\"" + statusCaldocs.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping("/getUnavailableStatusCaldoc.htm")
    public void getUnavailableStatusCaldoc(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            List<EdcsMasStatusCaldoc> statusCaldocs = statusCaldocDAO.findByFlag(1);
            String reuse = messageSource.getMessage("statusCaldoc.reuse", null, thaiLocale);
            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> statusCaldocMap = new ArrayList<Map>();
            for (EdcsMasStatusCaldoc row : statusCaldocs) {
                Map p = new HashMap();
                p.put("statusCaldocId", row.getStatusCaldocId());
                p.put("statusCaldocName", row.getStatusCaldocName());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));

//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getStatusCaldocId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
                String reuseCheckbox = " <input type=\"checkbox\" name=\"reuseStatusCaldoc\" value=\"" + row.getStatusCaldocId() + "\">";
                p.put("reuseCheck", reuseCheckbox);

                String realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedStatusCaldoc\" value=\"" + row.getStatusCaldocId() + "\">";

                p.put("realDeleteCheck", realDeleteCheckbox);
                statusCaldocMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(statusCaldocMap);

            //Go to view
            responseMessage = "{" + "\"size\":\"" + statusCaldocs.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("name") String name, @RequestParam(value = "id", required = false) String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        EdcsMasStatusCaldocDAO statusCaldocDAO = new EdcsMasStatusCaldocDAOImpl(db);
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            int count = statusCaldocDAO.isExistCount(name.trim(), id);
            responseMessage = "";
            if (count > 0) {
                responseMessage = "สถานะเอกสาร " + name + "ในระบบแล้ว";
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
