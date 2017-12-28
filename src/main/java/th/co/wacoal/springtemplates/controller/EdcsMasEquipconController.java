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
import th.co.wacoal.springtemplates.dao.EdcsMasEquipconDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasEquipconDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasEquipcon;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/equipcons")
public class EdcsMasEquipconController {

    Database db = new Database("sqlServer");
    EdcsMasEquipconDAO equipconDAO = new EdcsMasEquipconDAOImpl(db);
    EdcsMasMeasureDAO measureDAO = new EdcsMasMeasureDAOImpI(db);
    String responseMessage = "please enter at least one name";
    PrintWriter out;

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

   DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy",Locale.US);

    @RequestMapping("/index")
    public ModelAndView equipconCRUD(Model model, HttpSession session) {
        try {
            // Model
            return new ModelAndView("master/equipcons/index");
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
        EdcsMasEquipcon equipcon = new EdcsMasEquipcon();
        ModelAndView mv = new ModelAndView("master/equipcons/add");
        mv.addObject("equipcon", equipcon);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("equipcon") EdcsMasEquipcon equipcon, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(equipcon) >= 1) {

                String userId = (String) session.getAttribute("userId");
                equipcon.setCreateBy(userId);
                equipcon.setChangeBy(userId);

                equipconDAO.add(equipcon);
                responseMessage = "succesfully add EQUIPMENT CONDITION " + equipconDAO.formatFullName(equipcon.getEquipConNameTh(), equipcon.getEquipConNameEn());
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
        EdcsMasEquipcon editingEquipcon = equipconDAO.find(id);
        ModelAndView mv = new ModelAndView("master/equipcons/edit");
        mv.addObject("equipcon", editingEquipcon);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("equipcon") EdcsMasEquipcon equipcon, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(equipcon) >= 1) {

                String userId = (String) session.getAttribute("userId");
                equipcon.setChangeBy(userId);

                equipconDAO.update(equipcon);
                responseMessage = "succesfully edit EQUIPMENT CONDITION " + equipconDAO.formatFullName(equipcon.getEquipConNameTh(), equipcon.getEquipConNameEn());
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
        EdcsMasEquipcon deletingEquipcon = equipconDAO.find(id);
        ModelAndView mv = new ModelAndView("master/equipcons/delete");
        mv.addObject("equipcon", deletingEquipcon);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("equipConId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            equipconDAO.delete(id, userId);
            responseMessage = "succesfully delete EQUIPMENT CONDITION ID " + id;
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
            int result = equipconDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " equipcons";
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

            int result = equipconDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " equipcons";

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
        EdcsMasEquipcon reuseEquipcon = equipconDAO.find(id);
        ModelAndView mv = new ModelAndView("master/equipcons/reuse");
        mv.addObject("equipcon", reuseEquipcon);
        return mv;
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("equipConId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            equipconDAO.reuse(id, userId);
            responseMessage = "succesfully reuse EQUIPMENT CONDITION ID " + id;
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
            int result = equipconDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " equipcons";
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    private int validateInput(EdcsMasEquipcon equipcon) {
        responseMessage = "";
        int valid = 1;
        if (equipcon.getEquipConNameEn() == null && equipcon.getEquipConNameEn().trim().length() <= 0
                && equipcon.getEquipConNameTh() == null && equipcon.getEquipConNameTh().trim().length() <= 0) {
            valid = 0;
            responseMessage = "please enter at least one name";
        }
        Integer id = equipcon.getEquipConId();
        if (id == null) {
            id = 0;
        }
        if ((equipconDAO.isExistCount(equipcon.getEquipConNameTh(), equipcon.getEquipConNameEn(), id.toString())) > 0) {
            responseMessage = "มีสาขา " + equipcon.getEquipConNameTh() + " ( " + equipcon.getEquipConNameEn() + " ) ในระบบแล้ว";
            valid = 0;
        }
        //return 1 if valid
        return valid;
    }

    @RequestMapping("/getAvailableEquipcon.htm")
    public void getAvailableEquipcon(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasEquipcon> equipcons = equipconDAO.findByFlag(0);

        // Create JSON
        JSONObject json = new JSONObject();
        json.put("total", equipcons.size());
        List<Map> equipconMap = new ArrayList<Map>();
        // Do something
        for (EdcsMasEquipcon row : equipcons) {
            Map p = new HashMap();
            p.put("equipConId", row.getEquipConId());
            p.put("equipConNameTh", row.getEquipConNameTh());
            p.put("equipConNameEn", row.getEquipConNameEn());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));
            p.put("flagComment", df.format(row.getChangeOn()));

            String flagComment = row.getFlagComment();
            String flagCommentCheckbox;
            if ("1".equals(flagComment)) {
                flagCommentCheckbox = " <input type=\"checkbox\" class='flagComment' name=\"flagComment\" value=\"" + row.getEquipConId() + "\" checked >";
            } else {
                flagCommentCheckbox = " <input type=\"checkbox\" class='flagComment' name=\"flagComment\" value=\"" + row.getEquipConId() + "\">";
            }
            p.put("flagComment", flagCommentCheckbox);

            String edit = messageSource.getMessage("equipcon.edit", null, thaiLocale);
            String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getEquipConId() + "'></i>" + edit + "</button> ";
            //+ "<button class='deleteData' value='./delete.htm?id=" + row.getEquipConId() + "'>"+delete+"</button>";
            p.put("actionLink", actionLink);

            String deleteCheckbox = "<font color='red'>มีเครื่องวัดใช้งานอยู่ <br/>ไม่สามารถลบได้</font>";
            if (measureDAO.findByEquipconIdByFlag(row.getEquipConId(), "0").size() <= 0) {
                deleteCheckbox = " <input type=\"checkbox\" name=\"deletedEquipcon\" value=\"" + row.getEquipConId() + "\">";
            }

            p.put("deleteCheck", deleteCheckbox);
            equipconMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(equipconMap);
        json.put("data", jsonString);
        //Go to view
        out.print("{" + "\"size\":\"" + equipcons.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getUnavailableEquipcon.htm")
    public void getUnavailableEquipcon(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasEquipcon> equipcons = equipconDAO.findByFlag(1);
        String reuse = messageSource.getMessage("equipcon.reuse", null, thaiLocale);
        // Create JSON
        JSONObject json = new JSONObject();
        json.put("total", equipcons.size());
        List<Map> equipconMap = new ArrayList<Map>();
        for (EdcsMasEquipcon row : equipcons) {
            Map p = new HashMap();
            p.put("equipConId", row.getEquipConId());
            p.put("equipConNameTh", row.getEquipConNameTh());
            p.put("equipConNameEn", row.getEquipConNameEn());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getEquipConId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
            String reuseCheckbox = " <input type=\"checkbox\" name=\"reuseEquipcon\" value=\"" + row.getEquipConId() + "\">";
            p.put("reuseCheck", reuseCheckbox);

            String realDeleteCheckbox = "<font color='red'>มีเครื่องวัดใช้ในระบบอยู่ <br/>ไม่สามารถลบได้</font>";
            if (measureDAO.findByEquipconIdByFlag(row.getEquipConId(), "1").size() <= 0) {
                realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedEquipcon\" value=\"" + row.getEquipConId() + "\">";
            }

            p.put("realDeleteCheck", realDeleteCheckbox);
            equipconMap.add(p);

        }
        JSONArray jsonString = JSONArray.fromObject(equipconMap);
        json.put("data", jsonString);
        //Go to view
        out.print("{" + "\"size\":\"" + equipcons.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping(value = "/flaggedComment", method = RequestMethod.GET)
    public void flaggedComment(@RequestParam("id") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            int flag = equipconDAO.flaggedComment(id, userId);
            String flagST;
            if (flag == 1) {
                flagST = "flagged";
            } else {
                flagST = "unflagged";
            }
            responseMessage = "comment " + flagST + " EQUIPMENT CONDITION ID " + id;
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("nameTh") String nameTh, @RequestParam("nameEn") String nameEn, @RequestParam(value = "id", required = false) String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            int count = equipconDAO.isExistCount(nameTh.trim(), nameEn.trim(), id);
            responseMessage = "";
            if (count > 0) {
                responseMessage = "มีสาขา " + nameTh + " ( " + nameEn + " ) ในระบบแล้ว";
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
