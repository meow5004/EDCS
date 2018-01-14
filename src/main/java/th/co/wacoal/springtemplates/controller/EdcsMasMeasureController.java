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
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasEquipconDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureGroupDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasModelDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasCalpointDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasDepartmentDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasEquipconDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureGroupDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasModelDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasCalpoint;
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasureGroup;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/measure")
public class EdcsMasMeasureController {

    Database db = new Database("sqlServer");
    EdcsMasMeasureDAO measureDAO = new EdcsMasMeasureDAOImpI(db);
    EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
    EdcsMasCalpointDAO calpointDAO = new EdcsMasCalpointDAOImpl(db);
    EdcsMasEquipconDAO equipConDAO = new EdcsMasEquipconDAOImpl(db);
    EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
    EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
    String responseMessage = "please enter at least one name";
    PrintWriter out;

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView measureCRUD(Model model, HttpSession session) {
        try {
            // Model
            return new ModelAndView("master/measure/index");
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
        EdcsMasMeasure measure = new EdcsMasMeasure();
        depDAO = new EdcsMasDepartmentDAOImpl(db);
        ModelAndView mv = new ModelAndView("master/measure/add");
        mv.addObject("calpoints", calpointDAO.findByFlag(0));
        mv.addObject("departments", depDAO.findByFlag(0));
        mv.addObject("measureGroups", measureGroupDAO.findByFlag(0));
        mv.addObject("measure", measure);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("measure") EdcsMasMeasure measure, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(measure) >= 1) {

                String userId = (String) session.getAttribute("userId");
                measure.setCreateBy(userId);
                measure.setChangeBy(userId);

                measureDAO.add(measure);
                responseMessage = "succesfully add MEASURE  " + measure.getFullName();
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
        EdcsMasMeasure editingmeasureId = measureDAO.find(id);
        ModelAndView mv = new ModelAndView("master/measure/edit");
        mv.addObject("measure", editingmeasureId);
        depDAO = new EdcsMasDepartmentDAOImpl(db);
        List<EdcsMasCalpoint> calpoints = calpointDAO.findByFlag(0);

        mv.addObject("calpoints", calpoints);

        List<EdcsMasMeasureGroup> measureGroups = measureGroupDAO.findByFlag(0);
        mv.addObject("measureGroups", measureGroups);
        mv.addObject("departments", depDAO.findByFlag(0));
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("measure") EdcsMasMeasure measure, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(measure) >= 1) {

                String userId = (String) session.getAttribute("userId");
                measure.setChangeBy(userId);

                measureDAO.update(measure);
                responseMessage = "succesfully edit MEASURE  " + measure.getFullName();
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
        EdcsMasMeasure deletingmeasureId = measureDAO.find(id);
        ModelAndView mv = new ModelAndView("master/measure/delete");
        mv.addObject("measure", deletingmeasureId);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("measureId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            measureDAO.delete(id, userId);
            responseMessage = "succesfully delete MEASURE  ID " + id;
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
            int result = measureDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " measure";
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

            int result = measureDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " measure";

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
        EdcsMasMeasure reusemeasureId = measureDAO.find(id);
        ModelAndView mv = new ModelAndView("master/measure/reuse");
        mv.addObject("measure", reusemeasureId);
        return mv;
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("measureId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            measureDAO.reuse(id, userId);
            responseMessage = "succesfully reuse MEASURE  ID " + id;
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
            int result = measureDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " measure";
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    private int validateInput(EdcsMasMeasure measure) {
        responseMessage = "";
        int valid = 1;

        //return 1 if valid
        return valid;
    }

    @RequestMapping("/getAvailableMeasure.htm")
    public void getAvailablemeasureId(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasMeasure> measure = measureDAO.findByFlag(0);
        depDAO = new EdcsMasDepartmentDAOImpl(db);
        // Create JSON
        JSONObject json = new JSONObject();

        List<Map> measureMap = new ArrayList<Map>();
        String edit = messageSource.getMessage("measure.edit", null, thaiLocale);
        String delete = messageSource.getMessage("measure.delete", null, thaiLocale);
        // Do something

        for (EdcsMasMeasure row : measure) {
            EdcsMasMeasureGroup measureGroup = measureGroupDAO.find(row.getMeasureGroupId());
            Map p = new HashMap();
            p.put("measure", row.getMeasureId());
            p.put("measureGroup", row.getMeasureGroupId());
            p.put("measureGroupName", measureGroup.getFullName());
            p.put("measureCode", row.getMeasureCode());
            p.put("measureName", row.getFullName());
            p.put("range", row.getRange());
            p.put("useRange", row.getUseRange());
            p.put("description", row.getDescription());
            p.put("measureTime", row.getMeasureTimes());
            p.put("abtype", row.getAbType());

            EdcsMasCalpoint tempC = calpointDAO.find(row.getCalpointId());
            p.put("calpointId", tempC.getCalpoint());

            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));
            p.put("depId", row.getDepId());
            EdcsMasDepartment dept = depDAO.find(row.getDepId());
            p.put("depName", dept.getFullName());
            String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getMeasureId() + "'></i>" + edit + "</button> ";
            //+ "<button class='deleteData' value='./delete.htm?id=" + row.getmeasureId() + "'>"+delete+"</button>";
            p.put("actionLink", actionLink);

            String deleteCheckbox = "<font color='red'>มีแม่แบบใช้งานอยู่ <br/>ไม่สามารถลบได้</font>";
            if (modelDAO.findByMeasureIdByFlag(row.getMeasureId(), "0").size() <= 0) {
                deleteCheckbox = " <input type=\"checkbox\" name=\"deletedmeasureId\" value=\"" + row.getMeasureId() + "\">";
            }

            p.put("deleteCheck", deleteCheckbox);
            measureMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(measureMap);

        //Go to view
        out.print("{" + "\"size\":\"" + measure.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getUnavailableMeasure.htm")
    public void getUnavailablemeasureId(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasMeasure> measure = measureDAO.findByFlag(1);

        String reuse = messageSource.getMessage("measure.reuse", null, thaiLocale);
        // Create JSON
        JSONObject json = new JSONObject();

        List<Map> measureMap = new ArrayList<Map>();
        for (EdcsMasMeasure row : measure) {
            EdcsMasMeasureGroup measureGroup = measureGroupDAO.find(row.getMeasureGroupId());
            Map p = new HashMap();
            p.put("measure", row.getMeasureId());
            p.put("measureGroup", row.getMeasureGroupId());
            p.put("measureGroupName", measureGroup.getFullName());
            p.put("measureCode", row.getMeasureCode());
            p.put("measureName", row.getFullName());
            p.put("range", row.getRange());
            p.put("useRange", row.getUseRange());
            p.put("description", row.getDescription());
            p.put("measureTime", row.getMeasureTimes());
            p.put("abtype", row.getAbType());
            EdcsMasDepartment dept = depDAO.find(row.getDepId());
            p.put("depName", dept.getFullName());
            EdcsMasCalpoint tempC = calpointDAO.find(row.getCalpointId());
            EdcsMasMeasureGroup tempG = measureGroupDAO.find(row.getMeasureGroupId());

            p.put("calpointId", tempC.getCalpoint());

            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));
            p.put("depId", row.getDepId());
//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getmeasureId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
            String reuseCheckbox = " <input type=\"checkbox\" name=\"reusemeasureId\" value=\"" + row.getMeasureId() + "\">";
            boolean isDeletedCalpointExist = tempC.getFlagDel().equals("1");
            boolean isDeletedMeasureGroupExist = tempG.getFlagDel().equals("1");
            //if 1 parent relation is still unusable

            String reuseCheckboxMessage = "";
            int validReuse = 1;

            if (isDeletedCalpointExist) {
                reuseCheckboxMessage += "<font color='red'>กรุณากู้คืนจุดสอบเทียบรหัส " + tempC.getCalpointId() + "<br/> ก่อนกู้คืนเครื่องวัดนี้</font><br/>";
            }

            if (isDeletedMeasureGroupExist) {
                reuseCheckboxMessage += "<font color='red'>กรุณากู้คืนกลุ่มเครื่องวัดรหัส " + tempG.getMeasureGroupId() + "<br/> ก่อนกู้คืนเครื่องวัดนี้</font><br/>";
            }

            if (isDeletedCalpointExist
                    || isDeletedMeasureGroupExist) {
                reuseCheckbox = reuseCheckboxMessage;
            }

            p.put("reuseCheck", reuseCheckbox);
            String realDeleteCheckbox = "<font color='red'>มีแม่แบบในระบบ <br/>ไม่สามารถลบออกนอกระบบได้</font>";
            if (modelDAO.findByMeasureIdByFlag(row.getMeasureId(), "1").size() <= 0) {
                realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedmeasureId\" value=\"" + row.getMeasureId() + "\">";
            }

            p.put("realDeleteCheck", realDeleteCheckbox);
            measureMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(measureMap);

        //Go to view
        out.print("{" + "\"size\":\"" + measure.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("measureNameTh") String measureNameTh, @RequestParam("measureNameEn") String measureNameEn, @RequestParam(value = "id", required = false) String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            int count = measureDAO.isExistCount(measureNameTh.trim(), measureNameEn.trim(), id);
            responseMessage = "";
//            EdcsMasMeasure temp = new EdcsMasMeasure();
//            temp.setmeasureNameTh(measureNameTh);
//            temp.setmeasureNameEn(measureNameEn);
//            if (count > 0) {
//                responseMessage = "มีกลุ่มเครื่องวัด " + temp.getFullName() + "ในระบบแล้ว";
//            }

            out.write(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.write(ex.getMessage());
        } finally {
            //db.close();
        }
    }
}
