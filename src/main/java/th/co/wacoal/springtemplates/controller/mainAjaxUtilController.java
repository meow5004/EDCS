/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static th.co.wacoal.springtemplates.controller.HelperFunction.calibrationFilterByDepartments;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasModelDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasProcessDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasModelDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasProcessDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;
import th.co.wacoal.springtemplates.domain.EdcsMasModel;
import th.co.wacoal.springtemplates.domain.EdcsMasProcess;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/ajaxHelper")
public class mainAjaxUtilController {

    @RequestMapping("/getNearDueAndNewCalibration.htm")
    public void getNearDueAndNewCalibration(@RequestParam("dayDue") int dayDue, @RequestParam(value = "depId", required = false) String depId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responResult = null;
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> dueCalibs = calibDAO.getNewAndNearExpireCalibration(30);
            // Do somethin
            EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
            JSONArray jsonString = new JSONArray();
            List<EdcsCalibration> duecalibsByDepId = new ArrayList<>();
            if (depId == null || depId.length() <= 0) {
                depId = user.getDepId();
            }

            duecalibsByDepId = calibrationFilterByDepartments(depId, dueCalibs);
            jsonString = JSONArray.fromObject(duecalibsByDepId);
            //Go to view
            responResult = "{" + "\"size\":\"" + dueCalibs.size() + "\",\"data\":" + jsonString + "}";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping("/getRequestedApprover.htm")
    public void getRequestedApprover(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasUser user = new EdcsMasUser();
        user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        String responResult = null;
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> requestedCalib = calibDAO.getRequestedApprover(user.getEmpId());
            // Do somethin

            JSONArray jsonString = JSONArray.fromObject(requestedCalib);
            //Go to view
            responResult = "{" + "\"size\":\"" + requestedCalib.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping("/getApprovedDevice.htm")
    public void getApprovedDevice(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responResult = null;
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> approveDevice = calibDAO.getApprovedDevice();
            EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
            approveDevice = HelperFunction.calibrationFilterPerUserViewableDepartments(user, approveDevice);
            JSONArray jsonString = JSONArray.fromObject(approveDevice);
            //Go to view
            responResult = "{" + "\"size\":\"" + approveDevice.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping("/getCalibrationListInSystem.htm")
    public void getCalibrationListInSystem(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responResult = null;
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> approveDevice = calibDAO.getCalibrationListInSystem();
            // Do somethin

            JSONArray jsonString = JSONArray.fromObject(approveDevice);
            //Go to view
            responResult = "{" + "\"size\":\"" + approveDevice.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping("/listNonFinishCalibration.htm")
    public void listNonFinishCalibration(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responResult = null;
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> approveCalib = calibDAO.listNonFinishCalibration();
            // Do somethin
            EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
            approveCalib = HelperFunction.calibrationFilterPerUserViewableDepartments(user, approveCalib);
            JSONArray jsonString = JSONArray.fromObject(approveCalib);
            //Go to view
            responResult = "{" + "\"size\":\"" + approveCalib.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping("/listDisapprovedCalibration.htm")
    public void listDisapprovedCalibration(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responResult = null;
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> disapproveCalib = calibDAO.listDisapprovedCalibration();
            // Do somethin
            EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
            disapproveCalib = HelperFunction.calibrationFilterPerUserViewableDepartments(user, disapproveCalib);
            JSONArray jsonString = JSONArray.fromObject(disapproveCalib);
            //Go to view
            responResult = "{" + "\"size\":\"" + disapproveCalib.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping("/listFinishCalibrationWaitForApproval.htm")
    public void listFinishCalibrationWaitForApproval(Model model, HttpSession session, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        String responResult = null;
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> approveDevice = calibDAO.listFinishCalibrationWaitForApproval(user.getEmpId());
            // Do somethin

            JSONArray jsonString = JSONArray.fromObject(approveDevice);
            //Go to view
            responResult = "{" + "\"size\":\"" + approveDevice.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }

    }

    @RequestMapping("/getLabApporvedDevice.htm")
    public void getLabApporvedDevice(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responResult = null;
        try {

            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> approveDevice = calibDAO.listLabAppovedCalibration();
            // Do somethin
            EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
            approveDevice = HelperFunction.calibrationFilterPerUserViewableDepartments(user, approveDevice);
            JSONArray jsonString = JSONArray.fromObject(approveDevice);
            //Go to view
            responResult = "{" + "\"size\":\"" + approveDevice.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping("/getStickerPrintedDevice.htm")
    public void getStickerPrintedDevice(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        String responResult = null;
        try {

            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<EdcsCalibration> approveDevice = calibDAO.listStickeredDevicesCalibration();
            // Do somethin
            EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
            approveDevice = HelperFunction.calibrationFilterPerUserViewableDepartments(user, approveDevice);
            JSONArray jsonString = JSONArray.fromObject(approveDevice);
            //Go to view
            responResult = "{" + "\"size\":\"" + approveDevice.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responResult);
        }
    }

    @RequestMapping("/previewReportFromInputDataModel.htm")
    public String previewReportByCalibId(@RequestParam("calId") String calId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            EdcsCalibration previewed = calibDAO.find(Integer.parseInt(calId));
            // Do somethin

            model.addAttribute("calibration", previewed);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return "calibrationReportPreview";
        }
    }

    @RequestMapping("/findModel.htm")
    public void findModel(@RequestParam("modelId") String modelId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        JSONObject jsonString = new JSONObject();
        Database db = new Database("sqlServer");
        try {
            EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
            EdcsMasModel Entitymodel = modelDAO.find(Integer.parseInt(modelId));

            // Create JSON
            jsonString = JSONObject.fromObject(Entitymodel);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(jsonString);
        }
    }

    @RequestMapping("/findMeasure.htm")
    public void findMeasure(@RequestParam("measureId") String measureId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        JSONObject jsonString = new JSONObject();
        Database db = new Database("sqlServer");
        try {
            EdcsMasMeasureDAO measureDAO = new EdcsMasMeasureDAOImpI(db);
            EdcsMasMeasure measure = measureDAO.find(Integer.parseInt(measureId));
            // Do somethin

            jsonString = JSONObject.fromObject(measure);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(jsonString);
        }
    }

    @RequestMapping("/findProcess.htm")
    public void findProcess(@RequestParam("processId") String processId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        JSONObject jsonString = new JSONObject();
        Database db = new Database("sqlServer");
        try {
            EdcsMasProcessDAO proDAO = new EdcsMasProcessDAOImpI(db);
            proDAO = new EdcsMasProcessDAOImpI(db);
            EdcsMasProcess measure = proDAO.find(Integer.parseInt(processId));
            // Do somethin

            jsonString = JSONObject.fromObject(measure);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(jsonString);
        }
    }

    @RequestMapping("/getRequestApproverByDepId.htm")
    public void getRequestApproverByDepId(@RequestParam("depId") String depId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        JSONArray jsonString = new JSONArray();
        Database db = new Database("sqlServer");
        try {
            EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
            List<EdcsMasUser> viewableDepUsers = userDAO.findByViewableDepId(depId);
            List<EdcsMasUser> canApproveRequestUser = new ArrayList<>();
            for (EdcsMasUser viewableDepUser : viewableDepUsers) {
                //2 คือ หน้าอนุมัติส่งรายงานสอบเทียบ
                if (viewableDepUser.getUserAuthTypeIds().contains(2)) {
                    canApproveRequestUser.add(viewableDepUser);
                }
            }

            jsonString = JSONArray.fromObject(canApproveRequestUser);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(jsonString);
        }
    }

}
