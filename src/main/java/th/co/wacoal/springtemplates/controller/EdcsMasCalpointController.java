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
import th.co.wacoal.springtemplates.dao.EdcsMasCalpointDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasCalpointDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasCalpoint;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/calpoints")
public class EdcsMasCalpointController {

    Database db = new Database("sqlServer");
    EdcsMasCalpointDAO calpointDAO = new EdcsMasCalpointDAOImpl(db);
    String responseMessage = "";
    PrintWriter out;

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

   DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy",Locale.US);

    EdcsMasMeasureDAO measureDAO = new EdcsMasMeasureDAOImpI(db);

    @RequestMapping("/index")
    public ModelAndView calpointCRUD(Model model, HttpSession session) {
        try {
            // Model
            return new ModelAndView("master/calpoints/index");
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
        EdcsMasCalpoint calpoint = new EdcsMasCalpoint();
        ModelAndView mv = new ModelAndView("master/calpoints/add");
        mv.addObject("calpoint", calpoint);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("calpoint") EdcsMasCalpoint calpoint, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(calpoint) >= 1) {

                String userId = (String) session.getAttribute("userId");
                calpoint.setCreateBy(userId);
                calpoint.setChangeBy(userId);

                calpointDAO.add(calpoint);
                responseMessage = "succesfully add CALCULATION POINT " + calpoint.getCalpointMin() + " - " + calpoint.getCalpointMax();
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
        EdcsMasCalpoint editingCalpoint = calpointDAO.find(id);
        ModelAndView mv = new ModelAndView("master/calpoints/edit");
        mv.addObject("calpoint", editingCalpoint);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("calpoint") EdcsMasCalpoint calpoint, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(calpoint) >= 1) {

                String userId = (String) session.getAttribute("userId");
                calpoint.setChangeBy(userId);

                calpointDAO.update(calpoint);
                responseMessage = responseMessage = "succesfully edit CALCULATION POINT " + calpoint.getCalpointMin() + " - " + calpoint.getCalpointMax();
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
        EdcsMasCalpoint deletingCalpoint = calpointDAO.find(id);
        ModelAndView mv = new ModelAndView("master/calpoints/delete");
        mv.addObject("calpoint", deletingCalpoint);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("calpointId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            calpointDAO.delete(id, userId);
            responseMessage = "succesfully delete CALCULATION POINT ID " + id;
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
            int result = calpointDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " calpoints";
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
            int result = calpointDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " calpoints";
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
        EdcsMasCalpoint reuseCalpoint = calpointDAO.find(id);
        ModelAndView mv = new ModelAndView("master/calpoints/reuse");
        mv.addObject("calpoint", reuseCalpoint);
        return mv;
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("calpointId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            calpointDAO.reuse(id, userId);
            responseMessage = "succesfully reuse CALCULATION POINT ID " + id;
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
            int result = calpointDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " calpoints";
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    private int validateInput(EdcsMasCalpoint calpoint) {
        responseMessage = "";
        int valid = 1;
        if (calpoint.getCalpointMax() == null
                || calpoint.getCalpointMin() == null) {
            valid = 0;
            responseMessage = "please enter calculation point";
        }
        //return 1 if valid
        return valid;
    }

    @RequestMapping("/getAvailableCalpoint.htm")
    public void getAvailableCalpoint(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasCalpoint> calpoints = calpointDAO.findByFlag(0);

        // Create JSON
        JSONObject json = new JSONObject();
        json.put("total", calpoints.size());
        List<Map> calpointMap = new ArrayList<Map>();
//        String edit = messageSource.getMessage("calpoint.edit", null, thaiLocale);
        String edit = messageSource.getMessage("calpoint.edit", null, thaiLocale);
        //String delete = messageSource.getMessage("calpoint.delete", null, thaiLocale);
        // Do something
        for (EdcsMasCalpoint row : calpoints) {
            Map p = new HashMap();
            p.put("calpointId", row.getCalpointId());
            p.put("calpointMax", row.getCalpointMax());
            p.put("calpointMin", row.getCalpointMin());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

            String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getCalpointId() + "'></i>" + edit + "</button> ";
            //+ "<button class='deleteData' value='./delete.htm?id=" + row.getCalpointId() + "'>"+delete+"</button>";
            p.put("actionLink", actionLink);

            String deleteCheckbox = "<font color='red'>มีเครื่องวัดใช้งานอยู่ <br/>ไม่สามารถลบได้</font>";
            if (measureDAO.findByCalpointIdByFlag(row.getCalpointId(), "0").size() <= 0) {
                deleteCheckbox = " <input type=\"checkbox\" name=\"deletedCalpoint\" value=\"" + row.getCalpointId() + "\">";
            }
            p.put("deleteCheck", deleteCheckbox);

            calpointMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(calpointMap);
        json.put("data", jsonString);
        //Go to view
        out.print("{" + "\"size\":\"" + calpoints.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getUnavailableCalpoint.htm")
    public void getUnavailableCalpoint(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasCalpoint> calpoints = calpointDAO.findByFlag(1);
        //String reuse = messageSource.getMessage("calpoint.reuse", null, thaiLocale);
        // Create JSON
        JSONObject json = new JSONObject();
        json.put("total", calpoints.size());
        List<Map> calpointMap = new ArrayList<Map>();
        for (EdcsMasCalpoint row : calpoints) {
            Map p = new HashMap();
            p.put("calpointId", row.getCalpointId());
            p.put("calpointMax", row.getCalpointMax());
            p.put("calpointMin", row.getCalpointMin());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getCalpointId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
            String reuseCheckbox = " <input type=\"checkbox\" name=\"reuseCalpoint\" value=\"" + row.getCalpointId() + "\">";
            p.put("reuseCheck", reuseCheckbox);

            String realDeleteCheckbox = "<font color='red'>มีเครื่องวัดอยู่ในระบบ <br/>ไม่สามารถลบได้</font>";
            if (measureDAO.findByCalpointIdByFlag(row.getCalpointId(), "1").size() <= 0) {
                realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedCalpoint\" value=\"" + row.getCalpointId() + "\">";
            }

            p.put("realDeleteCheck", realDeleteCheckbox);
            calpointMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(calpointMap);
        json.put("data", jsonString);
        //Go to view
        out.print("{" + "\"size\":\"" + calpoints.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("min") String min, @RequestParam("max") String max, @RequestParam(value = "id", required = false) String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            int count = calpointDAO.isExistCount(min, max, id);
            responseMessage = "";
            if (count > 0) {
                responseMessage = "มีค่าช่วงจุดสอบเทียบ " + min + " - " + max + " ในระบบแล้ว";
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
