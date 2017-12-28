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
import th.co.wacoal.springtemplates.dao.EdcsMasCalageDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasCalageDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasCalage;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/calage")
public class EdcsMasCalageController {

    Database db = new Database("sqlServer");
    EdcsMasCalageDAO calageDAO = new EdcsMasCalageDAOImpI(db);
    String responseMessage = "please enter at least one name";
    PrintWriter out;

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy",Locale.US);

    @RequestMapping("/index")
    public ModelAndView calageCRUD(Model model, HttpSession session) {
        try {
            // Model
            return new ModelAndView("master/calage/index");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ModelAndView("error", "error", ex.getMessage());
        } finally {
            //db.close();
        }
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.sql.Date.class, "startDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(java.sql.Date.class, "endDate", new CustomDateEditor(dateFormat, true));
    }

    //add Data
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model, HttpSession session) {
        EdcsMasCalage calage = new EdcsMasCalage();
        ModelAndView mv = new ModelAndView("master/calage/add");
        mv.addObject("calage", calage);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("calage") EdcsMasCalage calage, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(calage) >= 1) {

                String userId = (String) session.getAttribute("userId");
                calage.setCreateBy(userId);
                calage.setChangeBy(userId);

                calageDAO.add(calage);
                responseMessage = "succesfully add " + calage.toString();
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
        EdcsMasCalage editingCalageId = calageDAO.find(id);
        ModelAndView mv = new ModelAndView("master/calage/edit");
        mv.addObject("calage", editingCalageId);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("calage") EdcsMasCalage calage, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(calage) >= 1) {

                String userId = (String) session.getAttribute("userId");
                calage.setChangeBy(userId);

                calageDAO.update(calage);
                responseMessage = "succesfully edit" + calage.toString();
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
        EdcsMasCalage deletingCalageId = calageDAO.find(id);
        ModelAndView mv = new ModelAndView("master/calage/delete");
        mv.addObject("calage", deletingCalageId);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("calageId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            calageDAO.delete(id, userId);
            responseMessage = "succesfully delete CALAGE ID " + id;
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
            int result = calageDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " calage";
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

            int result = calageDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " calage";

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
        EdcsMasCalage reuseCalageId = calageDAO.find(id);
        ModelAndView mv = new ModelAndView("master/calage/reuse");
        mv.addObject("calage", reuseCalageId);
        return mv;
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("calageId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            calageDAO.reuse(id, userId);
            responseMessage = "succesfully reuse CALAGE ID " + id;
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
            int result = calageDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " calage";
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    private int validateInput(EdcsMasCalage calage) {
        responseMessage = "";
        int valid = 1;
        if (calageDAO.isExistCount(calage) > 0) {
            responseMessage = " มีเวลาคำนวณที่มีการใช้งาน " + calage.getCalAge() + " เริ่มวันที่ " + calage.getStartDate() + " ถึง " + calage.getEndDate() + " แล้ว";
            valid = 0;
        }

        return valid;
    }

    @RequestMapping("/getAvailableCalage.htm")
    public void getAvailableCalageId(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasCalage> calage = calageDAO.findByFlag(0);

        // Create JSON
        JSONObject json = new JSONObject();
        json.put("total", calage.size());
        List<Map> calageMap = new ArrayList<Map>();
        String edit = messageSource.getMessage("calage.edit", null, thaiLocale);
        String delete = messageSource.getMessage("calage.delete", null, thaiLocale);
        // Do something
        for (EdcsMasCalage row : calage) {
            Map p = new HashMap();
            p.put("calageId", row.getCalAgeId());
            p.put("calage", row.getCalAge());
            p.put("startDate", dfnt.format(row.getStartDate()));
            p.put("endDate", dfnt.format(row.getEndDate()));
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

            String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getCalAgeId() + "'></i>" + edit + "</button> ";
            //+ "<button class='deleteData' value='./delete.htm?id=" + row.getCalageId() + "'>"+delete+"</button>";
            p.put("actionLink", actionLink);
            String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedCalageId\" value=\"" + row.getCalAgeId() + "\">";

            p.put("deleteCheck", deleteCheckbox);
            calageMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(calageMap);
        json.put("data", jsonString);
        //Go to view
        out.print("{" + "\"size\":\"" + calage.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getUnavailableCalage.htm")
    public void getUnavailableCalageId(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
       DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.US);
        List<EdcsMasCalage> calage = calageDAO.findByFlag(1);
        String reuse = messageSource.getMessage("calage.reuse", null, thaiLocale);
        // Create JSON
        JSONObject json = new JSONObject();
        json.put("total", calage.size());
        List<Map> calageMap = new ArrayList<Map>();
        for (EdcsMasCalage row : calage) {
            Map p = new HashMap();
            p.put("calageId", row.getCalAgeId());
            p.put("calage", row.getCalAge());
            p.put("startDate", dfnt.format(row.getStartDate()));
            p.put("endDate", dfnt.format(row.getEndDate()));
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getCalageId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
            String reuseCheckbox = " <input type=\"checkbox\" name=\"reuseCalageId\" value=\"" + row.getCalAgeId() + "\">";
            p.put("reuseCheck", reuseCheckbox);

            String realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedCalageId\" value=\"" + row.getCalAgeId() + "\">";

            p.put("realDeleteCheck", realDeleteCheckbox);
            calageMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(calageMap);
        json.put("data", jsonString);
        //Go to view
        out.print("{" + "\"size\":\"" + calage.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(EdcsMasCalage calage, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            responseMessage = "";
            if (calageDAO.isExistCount(calage) > 0) {
                responseMessage = " มีเวลาคำนวณที่มีการใช้งาน " + calage.getCalAge() + " เริ่มวันที่ " + calage.getStartDate() + " ถึง " + calage.getEndDate() + " แล้ว";
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
