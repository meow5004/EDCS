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
import th.co.wacoal.springtemplates.dao.EdcsMasProcessDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasProcessDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasProcess;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/process")
public class EdcsMasProcessController {

    Database db = new Database("sqlServer");
    EdcsMasProcessDAO processDAO = new EdcsMasProcessDAOImpI(db);
    String responseMessage = "please enter at least one name";
    PrintWriter out;

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

   DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy",Locale.US);

    @RequestMapping("/index")
    public ModelAndView processCRUD(Model model, HttpSession session) {
        try {
            // Model
            return new ModelAndView("master/process/index");
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
        EdcsMasProcess process = new EdcsMasProcess();
        ModelAndView mv = new ModelAndView("master/process/add");
        mv.addObject("process", process);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addConfirm(@ModelAttribute("process") EdcsMasProcess process, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(process) >= 1) {

                String userId = (String) session.getAttribute("userId");
                process.setCreateBy(userId);
                process.setChangeBy(userId);

                processDAO.add(process);
                responseMessage = "succesfully add PROCESS " + process.getFullName();
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
        EdcsMasProcess editingProcess = processDAO.find(id);
        ModelAndView mv = new ModelAndView("master/process/edit");
        mv.addObject("process", editingProcess);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editConfirm(@ModelAttribute("process") EdcsMasProcess process, BindingResult result, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            if (validateInput(process) >= 1) {

                String userId = (String) session.getAttribute("userId");
                process.setChangeBy(userId);

                processDAO.update(process);
                responseMessage = "succesfully edit PROCESS " + process.getFullName();
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
        EdcsMasProcess deletingProcess = processDAO.find(id);
        ModelAndView mv = new ModelAndView("master/process/delete");
        mv.addObject("process", deletingProcess);
        return mv;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteConfirm(@RequestParam("processId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            processDAO.delete(id, userId);
            responseMessage = "succesfully delete PROCESS ID " + id;
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
            int result = processDAO.deleteMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully delete " + result + " process";
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

            int result = processDAO.realDeleteMutiple(HelperFunction.stringArrayToInt(ids));
            responseMessage = "succesfully permanantly delete " + result + " process";

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
        EdcsMasProcess reuseProcess = processDAO.find(id);
        ModelAndView mv = new ModelAndView("master/process/reuse");
        mv.addObject("process", reuseProcess);
        return mv;
    }

    @RequestMapping(value = "/reuse", method = RequestMethod.POST)
    public void reuseConfirm(@RequestParam("processId") int id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        responseMessage = "";
        try {
            String userId = (String) session.getAttribute("userId");
            processDAO.reuse(id, userId);
            responseMessage = "succesfully reuse PROCESS ID " + id;
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
            int result = processDAO.reuseMutiple(HelperFunction.stringArrayToInt(ids), userId);
            responseMessage = "succesfully reuse " + result + " process";
            out.print(responseMessage);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print(ex.getMessage());
        } finally {
            //db.close();
        }
    }

    private int validateInput(EdcsMasProcess process) {
        responseMessage = "";
        int valid = 1;
        if (process.getProcessNameTh() == null && process.getProcessNameTh().trim().length() <= 0
                && process.getProcessNameEn() == null && process.getProcessNameEn().trim().length() <= 0) {
            valid = 0;
            responseMessage = "please enter at least one process name";
        }

        if (process.getProcessSubjectTh() == null && process.getProcessSubjectTh().trim().length() <= 0
                && process.getProcessSubjectEn() == null && process.getProcessSubjectEn().trim().length() <= 0) {
            valid = 0;
            responseMessage = "please enter at least one subject name";
        }
        Integer id = process.getProcessId();
        if (id == null) {
            id = 0;
        }

        if ((processDAO.isExistCount(process.getProcessNameTh(), process.getProcessNameEn(), process.getProcessSubjectTh(), process.getProcessSubjectEn(), id.toString())) > 0) {
            valid = 0;
        }
        //return 1 if valid
        return valid;
    }

    @RequestMapping("/getAvailableProcess.htm")
    public void getAvailableProcess(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasProcess> process = processDAO.findByFlag(0);

        // Create JSON
        JSONObject json = new JSONObject();
        
        List<Map> processMap = new ArrayList<Map>();
        String edit = messageSource.getMessage("process.edit", null, thaiLocale);
        String delete = messageSource.getMessage("process.delete", null, thaiLocale);
        // Do something
        for (EdcsMasProcess row : process) {
            Map p = new HashMap();
            p.put("processId", row.getProcessId());
            p.put("processName", row.getFullProcessName());
            p.put("subjectName", row.getFullSubjectName());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

            String actionLink = "<button class='editData btn btn-primary btn-sm'  value='./edit.htm?id=" + row.getProcessId() + "'></i>" + edit + "</button> ";
            //+ "<button class='deleteData' value='./delete.htm?id=" + row.getProcessId() + "'>"+delete+"</button>";
            p.put("actionLink", actionLink);
            String deleteCheckbox = " <input type=\"checkbox\" name=\"deletedProcess\" value=\"" + row.getProcessId() + "\">";

            p.put("deleteCheck", deleteCheckbox);
            processMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(processMap);
        
        //Go to view
        out.print("{" + "\"size\":\"" + process.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getUnavailableProcess.htm")
    public void getUnavailableProcess(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsMasProcess> process = processDAO.findByFlag(1);
        String reuse = messageSource.getMessage("process.reuse", null, thaiLocale);
        // Create JSON
        JSONObject json = new JSONObject();
        
        List<Map> processMap = new ArrayList<Map>();
        for (EdcsMasProcess row : process) {
            Map p = new HashMap();
            p.put("processId", row.getProcessId());
            p.put("processName", row.getFullProcessName());
            p.put("subjectName", row.getFullSubjectName());
            p.put("createBy", row.getCreateBy());
            p.put("createOn", dfnt.format(row.getCreateOn()));
            p.put("changeBy", row.getChangeBy());
            p.put("changeOn", df.format(row.getChangeOn()));

//               String actionLink = "<button class='reuseData' value='./reuse.htm?id=" + row.getProcessId() + "'>"+reuse+"</button> ";
//            p.put("actionLink", actionLink);
            String reuseCheckbox = " <input type=\"checkbox\" name=\"reuseProcess\" value=\"" + row.getProcessId() + "\">";
            p.put("reuseCheck", reuseCheckbox);

            String realDeleteCheckbox = " <input type=\"checkbox\" name=\"realDeletedProcess\" value=\"" + row.getProcessId() + "\">";

            p.put("realDeleteCheck", realDeleteCheckbox);
            processMap.add(p);
        }
        JSONArray jsonString = JSONArray.fromObject(processMap);
        
        //Go to view
        out.print("{" + "\"size\":\"" + process.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping(value = "/checkIfExisted")
    public void checkIfExisted(@RequestParam("processNameTh") String processNameTh, @RequestParam("processNameEn") String processNameEn, @RequestParam("processSubjectTh") String processSubjectTh, @RequestParam("processSubjectEn")String processSubjectEn, @RequestParam(value = "id", required = false) String id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            int count = processDAO.isExistCount(processNameTh.trim(), processNameEn.trim(), processSubjectTh.trim(), processSubjectEn.trim(), id);
            responseMessage = "";
            EdcsMasProcess temp = new EdcsMasProcess();
            temp.setProcessNameTh(processNameTh);
            temp.setProcessNameEn(processNameEn);
            temp.setProcessSubjectTh(processSubjectTh);
            temp.setProcessSubjectEn(processSubjectEn);
            if (count > 0) {
                responseMessage = "มี PROCESS " + temp.getFullName() + "ในระบบแล้ว";
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
