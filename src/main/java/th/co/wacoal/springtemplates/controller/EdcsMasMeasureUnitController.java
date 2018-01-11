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
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureUnitDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureUnitDAOImpl;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasureUnit;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/measureUnits")
public class EdcsMasMeasureUnitController {

    Database db = new Database("sqlServer");
    EdcsMasMeasureUnitDAO measureUnitDAO = new EdcsMasMeasureUnitDAOImpl(db);
    String responseMessage = "please enter at least one name";
    PrintWriter out;

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

   DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy",Locale.US);

    @RequestMapping("/index")
    public ModelAndView measureUnitCRUD(Model model, HttpSession session) {
        try {
            // Model
            return new ModelAndView("master/measureUnits/index");
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
        EdcsMasMeasureUnit measureUnit = new EdcsMasMeasureUnit();
        ModelAndView mv = new ModelAndView("master/measureUnits/add");
        mv.addObject("measureUnit", measureUnit);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("measureUnit") EdcsMasMeasureUnit measureUnit, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(measureUnit) >= 1) {

                String userId = (String) session.getAttribute("userId");
                measureUnit.setCreateBy(userId);
                measureUnit.setChangeBy(userId);

                measureUnitDAO.add(measureUnit);
                responseMessage = "succesfully add measureUnit " + measureUnitDAO.formatFullName(measureUnit.getUnitNameTh(), measureUnit.getUnitShortTh(), measureUnit.getUnitNameEn(), measureUnit.getUnitShortEn());
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
        EdcsMasMeasureUnit editingMeasureUnit = measureUnitDAO.find(id);
        ModelAndView mv = new ModelAndView("master/measureUnits/edit");
        mv.addObject("measureUnit", editingMeasureUnit);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("measureUnit") EdcsMasMeasureUnit measureUnit, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(measureUnit) >= 1) {

                String userId = (String) session.getAttribute("userId");
                measureUnit.setChangeBy(userId);

                measureUnitDAO.update(measureUnit);
                responseMessage = "succesfully edit measureUnit " + measureUnitDAO.formatFullName(measureUnit.getUnitNameTh(), measureUnit.getUnitShortTh(), measureUnit.getUnitNameEn(), measureUnit.getUnitShortEn());
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
        EdcsMasMeasureUnit deletingMeasureUnit = measureUnitDAO.find(id);
        ModelAndView mv = new ModelAndView("master/measureUnits/delete");
        mv.addObject("measureUnit", deletingMeasureUnit);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("measureUnitId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            measureUnitDAO.delete(id, userId);
            responseMessage = "succesfully delete measureUnit ID " + id;
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
            int result = measureUnitDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " measureUnits";
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

            int result = measureUnitDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " measureUnits";

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
        EdcsMasMeasureUnit reuseMeasureUnit = measureUnitDAO.find(id);
        ModelAndView mv = new ModelAndView("master/measureUnits/reuse");
        mv.addObject("measureUnit", reuseMeasureUnit);
        return mv;
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("measureUnitId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            measureUnitDAO.reuse(id, userId);
            responseMessage = "succesfully reuse measureUnit ID " + id;
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
            int result = measureUnitDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " measureUnits";
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    private int validateInput(EdcsMasMeasureUnit measureUnit) {
        responseMessage = "";
        int valid = 1;
        if (measureUnit.getUnitNameEn() == null && measureUnit.getUnitNameEn().trim().length() <= 0
                && measureUnit.getUnitNameTh() == null && measureUnit.getUnitNameTh().trim().length() <= 0) {
            valid = 0;
            responseMessage = "please enter at least one name";
        }
        Integer id = measureUnit.getUnitId();
        if (id == null) {
            id = 0;
        }
        if ((measureUnitDAO.isExistCount(measureUnit.getUnitNameTh(), measureUnit.getUnitNameEn(), id.toString()) > 0)) {
            valid = 0;
        }
        //return 1 if valid
        return valid;
    }

    @RequestMapping("/getAvailableMeasureUnit.htm")
    public void getAvailableMeasureUnit(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasMeasureUnit> measureUnits = measureUnitDAO.findByFlag(0);

        // Create JSON
        JSONObject json = new JSONObject();
        
        List<Map> measureUnitMap = new ArrayList<Map>();
        String edit = messageSource.getMessage("measureUnit.edit", null, thaiLocale);
        String delete = messageSource.getMessage("measureUnit.delete", null, thaiLocale);
        // Do something
        for (EdcsMasMeasureUnit row : measureUnits) {
            Map p = new HashMap();
            p.put("unitId", row.getUnitId());
            p.put("unitNameTh", row.getUnitNameTh());
            p.put("unitShortTh", row.getUnitShortTh());
            p.put("unitNameEn", row.getUnitNameEn());
            p.put("unitShortEn", row.getUnitShortEn());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

            String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getUnitId() + "'></i>" + edit + "</button> ";
            //+ "<button class='deleteData' value='./delete.htm?id=" + row.getUnitId() + "'>"+delete+"</button>";
            p.put("actionLink", actionLink);
            String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedMeasureUnit\" value=\"" + row.getUnitId() + "\">";

            p.put("deleteCheck", deleteCheckbox);
            measureUnitMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(measureUnitMap);
        
        //Go to view
        out.print("{" + "\"size\":\"" + measureUnits.size() + "\",\"data\":" + jsonString + "}");

    }

    @RequestMapping("/getUnavailableMeasureUnit.htm")
    public void getUnavailableMeasureUnit(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasMeasureUnit> measureUnits = measureUnitDAO.findByFlag(1);
        String reuse = messageSource.getMessage("measureUnit.reuse", null, thaiLocale);
        // Create JSON
        JSONObject json = new JSONObject();
        
        List<Map> measureUnitMap = new ArrayList<Map>();
        for (EdcsMasMeasureUnit row : measureUnits) {
            Map p = new HashMap();
            p.put("unitId", row.getUnitId());
            p.put("unitNameTh", row.getUnitNameTh());
            p.put("unitShortTh", row.getUnitShortTh());
            p.put("unitNameEn", row.getUnitNameEn());
            p.put("unitShortEn", row.getUnitShortEn());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getUnitId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
            String reuseCheckbox = " <input type=\"checkbox\" name=\"reuseMeasureUnit\" value=\"" + row.getUnitId() + "\">";
            p.put("reuseCheck", reuseCheckbox);

            String realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedMeasureUnit\" value=\"" + row.getUnitId() + "\">";

            p.put("realDeleteCheck", realDeleteCheckbox);
            measureUnitMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(measureUnitMap);
        
        //Go to view
        out.print("{" + "\"size\":\"" + measureUnits.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("nameTh") String nameTh, @RequestParam("nameEn") String nameEn, @RequestParam(value = "id", required = false) String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            int count = measureUnitDAO.isExistCount(nameTh.trim(), nameEn.trim(), id);
            responseMessage = "";
            if (count > 0) {
                responseMessage = "มียูนิต " + nameTh + " ( " + nameEn + " ) ในระบบแล้ว";
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
