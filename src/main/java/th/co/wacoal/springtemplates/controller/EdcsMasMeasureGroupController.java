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
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureGroupDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureGroupDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasureGroup;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/measureGroup")
public class EdcsMasMeasureGroupController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView measureGroupCRUD(Model model, HttpSession session) {
        return new ModelAndView("master/measureGroup/index");
    }

    //add Data
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model, HttpSession session) {
        EdcsMasMeasureGroup measureGroup = new EdcsMasMeasureGroup();
        ModelAndView mv = new ModelAndView("master/measureGroup/add");
        mv.addObject("measureGroup", measureGroup);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("measureGroup") EdcsMasMeasureGroup measureGroup, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            if (validateInput(measureGroup) >= 1) {

                String userId = (String) session.getAttribute("userId");
                measureGroup.setCreateBy(userId);
                measureGroup.setChangeBy(userId);

                measureGroupDAO.add(measureGroup);
                responseMessage = "succesfully add MEASURE GROUP " + measureGroup.getFullName();
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
        ModelAndView mv = new ModelAndView("master/measureGroup/edit");
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            EdcsMasMeasureGroup editingMeasureGroupId = measureGroupDAO.find(id);
            mv.addObject("measureGroup", editingMeasureGroupId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("measureGroup") EdcsMasMeasureGroup measureGroup, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            if (validateInput(measureGroup) >= 1) {
                EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
                String userId = (String) session.getAttribute("userId");
                measureGroup.setChangeBy(userId);

                measureGroupDAO.update(measureGroup);
                responseMessage = "succesfully edit MEASURE GROUP " + measureGroup.getFullName();
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
        ModelAndView mv = new ModelAndView("master/measureGroup/delete");
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            EdcsMasMeasureGroup deletingMeasureGroupId = measureGroupDAO.find(id);

            mv.addObject("measureGroup", deletingMeasureGroupId);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("measureGroupId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            String userId = (String) session.getAttribute("userId");
            measureGroupDAO.delete(id, userId);
            responseMessage = "succesfully delete MEASURE GROUP ID " + id;

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
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            int result = measureGroupDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " measureGroup";

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
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String[] ids = idArray.split(",");
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            int result = measureGroupDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " measureGroup";

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
        ModelAndView mv = new ModelAndView("master/measureGroup/reuse");
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            EdcsMasMeasureGroup reuseMeasureGroupId = measureGroupDAO.find(id);
            mv.addObject("measureGroup", reuseMeasureGroupId);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("measureGroupId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            String userId = (String) session.getAttribute("userId");
            measureGroupDAO.reuse(id, userId);
            responseMessage = "succesfully reuse MEASURE GROUP ID " + id;
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
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            int result = measureGroupDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " measureGroup";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    private int validateInput(EdcsMasMeasureGroup measureGroup) {
        Database db = new Database("sqlServer");

        int valid = 1;
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);

            if (measureGroup.getMeasureGroupNameTh() == null && measureGroup.getMeasureGroupNameTh().trim().length() <= 0
                    && measureGroup.getMeasureGroupNameEn() == null && measureGroup.getMeasureGroupNameEn().trim().length() <= 0) {
                valid = 0;
            }

            Integer id = measureGroup.getMeasureGroupId();
            if (id == null) {
                id = 0;
            }

            if ((measureGroupDAO.isExistCount(measureGroup.getMeasureGroupNameTh(), measureGroup.getMeasureGroupNameEn(), id.toString())) > 0) {
                valid = 0;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            //return 1 if valid
            return valid;
        }
    }

    @RequestMapping("/getAvailableMeasureGroup.htm")
    public void getAvailableMeasureGroupId(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            List<EdcsMasMeasureGroup> measureGroup = measureGroupDAO.findByFlag(0);

            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> measureGroupMap = new ArrayList<Map>();
            String edit = messageSource.getMessage("measureGroup.edit", null, thaiLocale);
            String delete = messageSource.getMessage("measureGroup.delete", null, thaiLocale);
            // Do something
            for (EdcsMasMeasureGroup row : measureGroup) {
                Map p = new HashMap();
                p.put("measureGroup", row.getMeasureGroupId());
                p.put("measureGroupName", row.getFullName());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));

                String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getMeasureGroupId() + "'></i>" + edit + "</button> ";
                //+ "<button class='deleteData' value='./delete.htm?id=" + row.getMeasureGroupId() + "'>"+delete+"</button>";
                p.put("actionLink", actionLink);
                String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedMeasureGroupId\" value=\"" + row.getMeasureGroupId() + "\">";

                p.put("deleteCheck", deleteCheckbox);
                measureGroupMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(measureGroupMap);

            //Go to view
            responseMessage = "{" + "\"size\":\"" + measureGroup.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping("/getUnavailableMeasureGroup.htm")
    public void getUnavailableMeasureGroupId(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            List<EdcsMasMeasureGroup> measureGroup = measureGroupDAO.findByFlag(1);
            String reuse = messageSource.getMessage("measureGroup.reuse", null, thaiLocale);
            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> measureGroupMap = new ArrayList<Map>();
            for (EdcsMasMeasureGroup row : measureGroup) {
                Map p = new HashMap();
                p.put("measureGroup", row.getMeasureGroupId());
                p.put("measureGroupName", row.getFullName());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));

//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getMeasureGroupId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
                String reuseCheckbox = " <input type=\"checkbox\" name=\"reuseMeasureGroupId\" value=\"" + row.getMeasureGroupId() + "\">";
                p.put("reuseCheck", reuseCheckbox);

                String realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedMeasureGroupId\" value=\"" + row.getMeasureGroupId() + "\">";

                p.put("realDeleteCheck", realDeleteCheckbox);
                measureGroupMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(measureGroupMap);

            //Go to view
            responseMessage = "{" + "\"size\":\"" + measureGroup.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("measureGroupNameTh") String measureGroupNameTh, @RequestParam("measureGroupNameEn") String measureGroupNameEn, @RequestParam(value = "id", required = false) String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        try {
            EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
            int count = measureGroupDAO.isExistCount(measureGroupNameTh.trim(), measureGroupNameEn.trim(), id);
            responseMessage = "";
            EdcsMasMeasureGroup temp = new EdcsMasMeasureGroup();
            temp.setMeasureGroupNameTh(measureGroupNameTh);
            temp.setMeasureGroupNameEn(measureGroupNameEn);
            if (count > 0) {
                responseMessage = "มีกลุ่มเครื่องวัด " + temp.getFullName() + "ในระบบแล้ว";
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.write(responseMessage);
        }
    }
}
