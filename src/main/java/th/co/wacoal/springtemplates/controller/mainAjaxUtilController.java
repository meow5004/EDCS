/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasCalpointDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasEquipconDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureGroupDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasModelDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasCalpointDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasEquipconDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureGroupDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasModelDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;
import th.co.wacoal.springtemplates.domain.EdcsMasModel;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/ajaxHelper")
public class mainAjaxUtilController {

    Database db = new Database("sqlServer");
    EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
    EdcsMasMeasureDAO measureDAO = new EdcsMasMeasureDAOImpI(db);
    EdcsMasCalpointDAO calpointDAO = new EdcsMasCalpointDAOImpl(db);
    EdcsMasEquipconDAO equipConDAO = new EdcsMasEquipconDAOImpl(db);
    EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
    EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);

    @RequestMapping("/getNearDueAndNewCalibration.htm")
    public void getNearDueAndNewCalibration(@RequestParam("dayDue") int dayDue, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsCalibration> dueCalibs = calibDAO.getNewAndNearExpireCalibrationMoreThanDays(30);
        // Do somethin

        JSONArray jsonString = JSONArray.fromObject(dueCalibs);
        //Go to view
        out.print("{" + "\"size\":\"" + dueCalibs.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getRequestedCalibration.htm")
    public void getRequestedCalibration(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsCalibration> requestedCalib = calibDAO.getRequestedCalibration();
        // Do somethin

        JSONArray jsonString = JSONArray.fromObject(requestedCalib);
        //Go to view
        out.print("{" + "\"size\":\"" + requestedCalib.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getApprovedDevice.htm")
    public void getApprovedDevice(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsCalibration> approveDevice = calibDAO.getApprovedDevice();
        // Do somethin

        JSONArray jsonString = JSONArray.fromObject(approveDevice);
        //Go to view
        out.print("{" + "\"size\":\"" + approveDevice.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/getApprovedAndReceiverdDevice.htm")
    public void getApprovedAndReceiverdDevice(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsCalibration> approveDevice = calibDAO.getApprovedAndReceiverdDevice();
        // Do somethin

        JSONArray jsonString = JSONArray.fromObject(approveDevice);
        //Go to view
        out.print("{" + "\"size\":\"" + approveDevice.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/listNonFinishCalibration.htm")
    public void listNonFinishCalibration(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<EdcsCalibration> approveDevice = calibDAO.listNonFinishCalibration();
        // Do somethin

        JSONArray jsonString = JSONArray.fromObject(approveDevice);
        //Go to view
        out.print("{" + "\"size\":\"" + approveDevice.size() + "\",\"data\":" + jsonString + "}");
    }

    @RequestMapping("/findModel.htm")
    public void findModel(@RequestParam("modelId") String modelId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasModel Entitymodel = modelDAO.find(Integer.parseInt(modelId));

        // Create JSON
        JSONObject jsonString = JSONObject.fromObject(Entitymodel);
        out.print(jsonString);
    }

    @RequestMapping("/findMeasure.htm")
    public void findMeasure(@RequestParam("measureId") String measureId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasMeasure measure = measureDAO.find(Integer.parseInt(measureId));
        // Do somethin

        JSONObject jsonString = JSONObject.fromObject(measure);
        out.print(jsonString);
    }
}
