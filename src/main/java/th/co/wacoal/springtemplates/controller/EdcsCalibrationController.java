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
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationAttachHeadDAO;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationAttachItemDAO;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationAttachHeadDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationAttachitemDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachHead;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachItem;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachItemPK;
import th.co.wacoal.springtemplates.domain.calibrationDeviceCheckModel;
import th.co.wacoal.springtemplates.domain.calibrationDeviceCheckModelsList;
import th.co.wacoal.springtemplates.domain.calibrationRequestModel;
import th.co.wacoal.springtemplates.domain.calibrationRequestModelsList;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/calibration")
public class EdcsCalibrationController {

    @Autowired
    private MessageSource messageSource;
    Locale thaiLocale = new Locale.Builder().setLanguage("th").build();

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
    DateFormat dfnt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateFormat.setLenient(false);
        //for attachHeader
        binder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/createCalibration", method = RequestMethod.POST)
    public void createCalibration(@RequestBody calibrationRequestModelsList reqModels, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            String userId = (String) session.getAttribute("userId");
            EdcsCalibration baseCalib;
            for (calibrationRequestModel requestModel : reqModels.getCalibrationRequestModels()) {
                baseCalib = new EdcsCalibration();
                int thisMeasureId = Integer.valueOf(requestModel.getMeasureId());
                baseCalib.setMeasureId(thisMeasureId);
                baseCalib.setRequestBy(userId);
                baseCalib.setCreateBy(userId);
                baseCalib.setRequestStatus("1");
                baseCalib.setRequestComment(requestModel.getRequestComment());
                baseCalib.setRequestApproverBy(requestModel.getApproverId());
                calibDAO.addNewCalibrationCombineWithPreviousCalibrationIfAny(baseCalib);
            }
            responseMessage = "success";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = "error";
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping("/createCalibrationReportHeader")
    public String createCalibrationReportHeader(@ModelAttribute("calibration") EdcsCalibration calibrationHeader, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            EdcsCalibration calib = calibrationHeader;
            calibDAO.saveCalibrationHeader(calib);
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = "error";
        } finally {
            db.close();
            return "redirect:/index/createSearch.htm";
        }
    }

    @RequestMapping("/saveCalibrationReportAttachmentByCalibration")
    public String saveCalibrationReportAttachItem(@ModelAttribute("calibration") EdcsCalibration calibrationOfRequestedItem, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationAttachHeadDAO headDAO = new EdcsCalibrationAttachHeadDAOImpI(db);
            EdcsCalibrationAttachItemDAO itemDAO = new EdcsCalibrationAttachitemDAOImpl(db);
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            EdcsCalibration calib = calibrationOfRequestedItem;
            List<EdcsCalibrationAttachHead> associateHeadAttactment = calib.getEdcsCalibrationAttachHeadList();
            if (associateHeadAttactment.size() > 0) {
                for (EdcsCalibrationAttachHead head : associateHeadAttactment) {
                    Integer headId = head.getCalAttachHeadId();
                    if (headId != null && headId > 0) {
                        //save item
                        headId = head.getCalAttachHeadId();
                        List<EdcsCalibrationAttachItem> items = head.getEdcsCalibrationAttachItemList();
                        for (EdcsCalibrationAttachItem item : items) {
                            EdcsCalibrationAttachItemPK itemPk = item.getEdcsCalibrationAttachItemPK();
                            itemPk.setCalAttachHeadId(headId);
                            item.setEdcsCalibrationAttachItemPK(itemPk);
                            itemDAO.update(item);
                        }
                        headDAO.update(head);
                    } else {
                        headId = headDAO.addAndReturnId(head);
                        //then add item
                        List<EdcsCalibrationAttachItem> items = head.getEdcsCalibrationAttachItemList();
                        for (EdcsCalibrationAttachItem item : items) {
                            EdcsCalibrationAttachItemPK itemPk = item.getEdcsCalibrationAttachItemPK();
                            itemPk.setCalAttachHeadId(headId);
                            item.setEdcsCalibrationAttachItemPK(itemPk);
                            itemDAO.add(item);
                        }
                    }

                }
                String userId = (String) session.getAttribute("userId");
                calib.setCalibrationAttachStatusBy(userId);
                calibDAO.finalizeDataAndMarkCalibrationAsComplete(calib);
                responseMessage = "success";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = "error";
        } finally {
            db.close();
            return "redirect:/index/createSearch.htm";
        }
    }

    @RequestMapping("/sendCalibrationToApprove")
    public void sendCalibrationToApprove(@RequestParam(value = "checkedValues") String calIdsStringForm, @RequestParam(value = "calibApprover", required = false) String approverId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            String userId = (String) session.getAttribute("userId");
            String[] calIds = calIdsStringForm.split(",");
            for (String calId : calIds) {
                int thisCalId = Integer.valueOf(calId);
                calibDAO.markCalibrationForApproval(thisCalId, approverId);
            }
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = "error";
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping("/approveLapResult")
    public void approveLapResult(@RequestParam(value = "checkedValues") String calIdsStringForm, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String result = "อนุมัติรายงานสอบเทียบหมายเลข ";
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            String[] calIds = calIdsStringForm.split(",");

            String userId = (String) session.getAttribute("userId");
            for (String calId : calIds) {
                int thisCalId = Integer.valueOf(calId);
                calibDAO.approveLabResult(thisCalId, userId);
                result += calibDAO.find(thisCalId).getCalCode() + ",";
            }
            result = result.replaceAll(", $", "");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = "error";
        } finally {
            db.close();
            out.print(result);
        }
    }

    @RequestMapping("/disapproveLapResult")
    public void disapproveLapResult(@RequestParam(value = "checkedValues") String calIdsStringForm, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String result = "ยกเลิกการส่งอนุมัติรายงานสอบเทียบหมายเลข ";
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            String[] calIds = calIdsStringForm.split(",");

            for (String calId : calIds) {
                int thisCalId = Integer.valueOf(calId);
                String userId = (String) session.getAttribute("userId");
                calibDAO.disapproveLabResult(thisCalId, userId);
                result += calibDAO.find(thisCalId).getCalCode() + ",";
            }
            result = result.replaceAll(", $", "");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = "error";
        } finally {
            db.close();
            out.print(result);
        }
    }

    @RequestMapping("/approveRequestedApprover")
    public void approveRequestedApprover(@RequestBody calibrationRequestModelsList reqModels, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            String userId = (String) session.getAttribute("userId");
            for (calibrationRequestModel reqApproving : reqModels.getCalibrationRequestModels()) {
                reqApproving.setApproverId(userId);
                calibDAO.approverRequested(reqApproving);
            }
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = "error";
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/receivedDevice", method = RequestMethod.POST)
    public void receivedDevice(@RequestBody calibrationDeviceCheckModelsList calibrationDeviceCheckModels, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            String userId = (String) session.getAttribute("userId");
            for (calibrationDeviceCheckModel recModel : calibrationDeviceCheckModels.getCalibrationDeviceCheckModels()) {
                recModel.setInspector(userId);
                calibDAO.receivedDevice(recModel);
            }
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = "error";
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping("/printStickerFromCalibrationId")
    public void printStickerFromCalibrationId(@RequestParam(value = "checkedValues") String calIdsStringForm, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            String[] calIds = calIdsStringForm.split(",");
            String userId = (String) session.getAttribute("userId");
            for (String calId : calIds) {
                int thisCalId = Integer.valueOf(calId);
                calibDAO.printedStickerCheck(thisCalId, userId);
            }
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = "error";
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping("/receiveCalibratedDeviceBack")
    public void receiveCalibratedDeviceBack(@RequestBody calibrationDeviceCheckModelsList calibrationDeviceCheckModels, Model model, HttpSession session, HttpServletResponse response) throws IOException {
         String responseMessage = "";
        PrintWriter out = response.getWriter();
        Database db = new Database("sqlServer");
        try {
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            String userId = (String) session.getAttribute("userId");
            for (calibrationDeviceCheckModel recModel : calibrationDeviceCheckModels.getCalibrationDeviceCheckModels()) {
                recModel.setInspector(userId);
                calibDAO.returnedDeviceCheck(recModel);
            }
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = "error";
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

}
