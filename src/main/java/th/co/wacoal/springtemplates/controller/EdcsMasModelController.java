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
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasModelDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasModelDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;
import th.co.wacoal.springtemplates.domain.EdcsMasModel;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/model")
public class EdcsMasModelController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping("/index")
    public ModelAndView modelCRUD(Model model, HttpSession session) {
        return new ModelAndView("master/model/index");
    }

    //activate/disactivate
    @RequestMapping(value = "/activate")
    public void activate(@RequestParam("modelId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            modelDAO.activate(id);
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/disactivate")
    public void disactivate(@RequestParam("modelId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            modelDAO.disactivate(id);
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    //add Data
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        EdcsMasMeasureDAO measueDAO = new EdcsMasMeasureDAOImpI(db);
        EdcsMasModel Entitymodel = new EdcsMasModel();
        ModelAndView mv = new ModelAndView("master/model/add");
        try {
            List<EdcsMasMeasure> measures = measueDAO.findByFlag(0);
            measures.add(0, new EdcsMasMeasure());

            mv.addObject("measures", measures);
            mv.addObject("entityModel", Entitymodel);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("entityModel") EdcsMasModel Entitymodel, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        EdcsMasMeasureDAO measueDAO = new EdcsMasMeasureDAOImpI(db);
        try {
            if (validateInput(Entitymodel) >= 1) {

                String userId = (String) session.getAttribute("userId");
                Entitymodel.setCreateBy(userId);
                Entitymodel.setChangeBy(userId);

                modelDAO.add(Entitymodel);
                EdcsMasMeasure added = measueDAO.find(Entitymodel.getMeasureId());
                responseMessage = "succesfully add model measurement " + added.getFullName() + " Cert id" + Entitymodel.getcerNo() + " by" + Entitymodel.getLocationBy();;
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
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        EdcsMasMeasureDAO measueDAO = new EdcsMasMeasureDAOImpI(db);
        ModelAndView mv = new ModelAndView("master/model/edit");
        try {
            EdcsMasModel editingEntityModel = modelDAO.find(id);

            mv.addObject("entityModel", editingEntityModel);

            List<EdcsMasMeasure> measures = measueDAO.findByFlag(0);
            measures.add(0, new EdcsMasMeasure());

            mv.addObject("measures", measures);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("entityModel") EdcsMasModel Entitymodel, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            if (validateInput(Entitymodel) >= 1) {

                String userId = (String) session.getAttribute("userId");
                Entitymodel.setChangeBy(userId);

                modelDAO.update(Entitymodel);
                responseMessage = "succesfully edit MODEL ID" + Entitymodel.getModelId();
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
        ModelAndView mv = new ModelAndView("master/model/delete");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            EdcsMasModel deletingEntitymodel = modelDAO.find(id);

            mv.addObject("entityModel", deletingEntitymodel);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("modelId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            String userId = (String) session.getAttribute("userId");
            modelDAO.delete(id, userId);
            responseMessage = "succesfully delete MODEL ID " + id;

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
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = modelDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " model";

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
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            String[] ids = idArray.split(",");

            int result = modelDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " model";

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
        ModelAndView mv = new ModelAndView("master/model/reuse");
        try {
            EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
            EdcsMasModel reuseEntitymodel = modelDAO.find(id);

            mv.addObject("entityModel", reuseEntitymodel);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return mv;
        }
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("modelId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            String userId = (String) session.getAttribute("userId");
            modelDAO.reuse(id, userId);
            responseMessage = "succesfully reuse MODEL ID " + id;

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
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);

        try {
            String userId = (String) session.getAttribute("userId");
            String[] ids = idArray.split(",");
            int result = modelDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " model";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    private int validateInput(EdcsMasModel Entitymodel) {
        String responseMessage = "";
        int valid = 1;
        if (Entitymodel.getcerNo() == null) {
            valid = 0;
        }

        if (Entitymodel.getMeasureId() == null) {
            valid = 0;
        }

        if (Entitymodel.getLocationBy() == null || Entitymodel.getLocationReturn() == null) {
            valid = 0;
        }

//        if (Entitymodel.getModelId() != null && (modelDAO.isExistCount(Entitymodel.getModelId().toString())) > 0) {
//            valid = 0;
//        }
        //return 1 if valid
        return valid;
    }

    @RequestMapping("/getAvailableModel.htm")
    public void getAvailableModel(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        try {
            EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
            // Create JSON
            JSONObject json = new JSONObject();
            EdcsMasMeasureDAO measueDAO = new EdcsMasMeasureDAOImpI(db);
            List<EdcsMasModel> Entitymodel = modelDAO.findByFlag(0);
            List<Map> modelMap = new ArrayList<Map>();
            String edit = messageSource.getMessage("model.edit", null, thaiLocale);
            String delete = messageSource.getMessage("model.delete", null, thaiLocale);
            // Do something
            for (EdcsMasModel row : Entitymodel) {
                EdcsMasMeasure thisMeasure = new EdcsMasMeasure();
                thisMeasure = measueDAO.find(row.getMeasureId());
                Map p = new HashMap();
                p.put("modelId", row.getModelId());
                p.put("modelCode", row.getModelCode());
                p.put("measureId", thisMeasure.getMeasureId());
                p.put("measureCode", thisMeasure.getMeasureCode());
                p.put("measureName", thisMeasure.getFullName());
                p.put("cerNo", row.getcerNo());
                p.put("locationBy", row.getLocationBy());
                p.put("locationReturn", row.getLocationReturn());
                p.put("resolution", row.getResolution());
                p.put("uncertainty", row.getUncertainty());
                p.put("duedate", dfnt.format(row.getDueDate()));
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));

                String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getModelId() + "'></i>" + edit + "</button> ";
                p.put("actionLink", actionLink);
                String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedModel\" value=\"" + row.getModelId() + "\">";
                p.put("deleteCheck", deleteCheckbox);
                String activeFlag = "";
                if (row.getFlagActive().equals("1")) {
                    activeFlag = "<input type=\"checkbox\" name=\"activate\" checked value=\"" + row.getModelId() + "\">";
                } else if (row.getFlagActive().equals("0")) {
                    activeFlag = "<input type=\"checkbox\" name=\"activate\" value=\"" + row.getModelId() + "\">";
                }
                p.put("active", row.getFlagActive());
                p.put("activeCheck", activeFlag);
                modelMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(modelMap);
            responseMessage = "{" + "\"size\":\"" + Entitymodel.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            ex.getCause().printStackTrace();
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
        //Go to view

    }

    @RequestMapping("/getUnavailableModel.htm")
    public void getUnavailableModel(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String responseMessage = "";
        Database db = new Database("sqlServer");
        try {
            EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
            EdcsMasMeasureDAO measueDAO = new EdcsMasMeasureDAOImpI(db);

            List<EdcsMasModel> Entitymodels = modelDAO.findByFlag(1);
            String reuse = messageSource.getMessage("model.reuse", null, thaiLocale);
            // Create JSON
            JSONObject json = new JSONObject();

            List<Map> modelMap = new ArrayList<Map>();
            for (EdcsMasModel row : Entitymodels) {
                EdcsMasMeasure thisMeasure = new EdcsMasMeasure();
                thisMeasure = measueDAO.find(row.getMeasureId());
                Map p = new HashMap();
                p.put("modelId", row.getModelId());
                p.put("modelCode", row.getModelCode());
                p.put("measureId", thisMeasure.getMeasureId());
                p.put("measureName", thisMeasure.getFullName());
                p.put("cerNo", row.getcerNo());
                p.put("locationBy", row.getLocationBy());
                p.put("locationReturn", row.getLocationReturn());
                p.put("resolution", row.getResolution());
                p.put("uncertainty", row.getUncertainty());
                p.put("measureCode", thisMeasure.getMeasureCode());
                p.put("createBy", row.getCreateBy());
                p.put("createOn", dfnt.format(row.getCreateOn()));
                p.put("changeBy", row.getChangeBy());
                p.put("changeOn", df.format(row.getChangeOn()));
                p.put("duedate", dfnt.format(row.getDueDate()));
                p.put("active", row.getFlagActive());
//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getModelId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
                String reuseCheckbox = "<font color='red'>เครื่องมือวัดขอต้นแบบนี้ถูกลบถูกลบ <br/>กรุณากู้คืนเครื่องมืดวัดที่เกี่ยวข้องก่อน</font>";
                if ("0".equals(thisMeasure.getFlagDel())) {
                    reuseCheckbox = " <input type=\"checkbox\" name=\"reuseModel\" value=\"" + row.getModelId() + "\">";
                }
                p.put("reuseCheck", reuseCheckbox);

                String realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedModel\" value=\"" + row.getModelId() + "\">";

                p.put("realDeleteCheck", realDeleteCheckbox);
                modelMap.add(p);
            }
            JSONArray jsonString = JSONArray.fromObject(modelMap);

            //Go to view
            responseMessage = "{" + "\"size\":\"" + Entitymodels.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("modelId") String modelId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responseMessage = "";
        EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
        try {
            int count = modelDAO.isExistCount(modelId);
            if (count > 0) {
                responseMessage = "มีกลุ่มเครื่องวัดรหัส " + modelId + "ในระบบแล้ว";
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
