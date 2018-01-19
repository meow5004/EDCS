/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/calibration")
public class EdcsCalibrationController {

    String responseMessage = "please enter at least one name";
    PrintWriter out;

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @RequestMapping(value = "/createCalibration", method = RequestMethod.POST)
    public void createCalibration(@RequestParam(value = "checkedValues") String measureIdsStringForm, Model model, HttpSession session, HttpServletResponse response) {
        Database db = new Database("sqlServer");
        EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
        try {
            PrintWriter out = response.getWriter();
            String[] measureIds = measureIdsStringForm.split(",");
            for (String measureId : measureIds) {
                int thisMeasureId = Integer.valueOf(measureId);
                calibDAO.addNewCalibrationByMeasureIdAndPreviousCalibration(thisMeasureId);
            }
            out.print("success");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print("error");
        } finally {
            db.close();
        }
    }

    @RequestMapping("/createCalibrationReportHeader")
    public String createCalibrationReportHeader(@ModelAttribute("calibration") EdcsCalibration calibrationHeader, Model model, HttpSession session, HttpServletResponse response) {
        Database db = new Database("sqlServer");
        EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
        EdcsCalibration calib = calibrationHeader;
        calibDAO.saveCalibrationHeader(calib);
        return "redirect:/index/createSearch.htm";
    }

    @RequestMapping("/approveRequestedApprover")
    public void approveRequestedApprover(@RequestParam(value = "checkedValues") String calIdsStringForm, Model model, HttpSession session, HttpServletResponse response) {
        Database db = new Database("sqlServer");
        EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
        try {
            PrintWriter out = response.getWriter();
            String[] calIds = calIdsStringForm.split(",");
            for (String calId : calIds) {
                int thisCalId = Integer.valueOf(calId);
                calibDAO.approverRequested(thisCalId);
            }
            out.print("success");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print("error");
        } finally {
            db.close();
        }
    }

    @RequestMapping("/receivedDevice")
    public void receivedDevice(@RequestParam(value = "checkedValues") String calIdsStringForm, Model model, HttpSession session, HttpServletResponse response) {
        Database db = new Database("sqlServer");
        EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
        try {
            PrintWriter out = response.getWriter();
            String[] calIds = calIdsStringForm.split(",");
            for (String calId : calIds) {
                int thisCalId = Integer.valueOf(calId);
                calibDAO.receivedDevice(thisCalId);
            }
            out.print("success");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out.print("error");
        } finally {
            db.close();
        }
    }

}
