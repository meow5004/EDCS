/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationAttachItemDAO;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasCalpointDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasEquipconDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureGroupDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureUnitDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasModelDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasProcessDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationAttachitemDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasCalpointDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasDepartmentDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasEquipconDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureGroupDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureUnitDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasModelDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasProcessDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachHead;
import th.co.wacoal.springtemplates.domain.EdcsMasCalpoint;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;

/**
 *
 * @author admin
 */
@Controller
public class IndexController {

    Database db = new Database("sqlServer");
    EdcsMasMeasureDAO measureDAO = new EdcsMasMeasureDAOImpI(db);
    EdcsMasCalpointDAO calpointDAO = new EdcsMasCalpointDAOImpl(db);
    EdcsMasEquipconDAO equipConDAO = new EdcsMasEquipconDAOImpl(db);
    EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);
    EdcsMasMeasureGroupDAO measureGroupDAO = new EdcsMasMeasureGroupDAOImpI(db);
    EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
    EdcsMasProcessDAO proDAO = new EdcsMasProcessDAOImpI(db);
    EdcsMasMeasureUnitDAO unitDAO = new EdcsMasMeasureUnitDAOImpl(db);
    EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
    EdcsCalibrationAttachItemDAO itemDAO = new EdcsCalibrationAttachitemDAOImpl(db);

    @RequestMapping("/home.htm")
    public String home(Model model, HttpSession session) {
        //Database db = null;
        try {
            session.setAttribute("userId", "92612");
            // Model
            //db = new Database("sqlServer");
            //ChkMasBoxcodeDAO chkMasBoxcodeDAO = new ChkMasBoxcodeDAOImpl(db);

            //List<ChkMasBoxcode> chkMasBoxcode = chkMasBoxcodeDAO.findAll();
            //model.addAttribute("chkMasBoxcode", chkMasBoxcode);
            return "index";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.addAttribute("error", ex.getMessage());
            return "error";
        } finally {
            //db.close();
        }
    }

    @RequestMapping("/createDoc.htm")
    public String createDoc(@RequestParam String calId, Model model, HttpSession session) {
        unitDAO = new EdcsMasMeasureUnitDAOImpl(db);
        calibDAO = new EdcsCalibrationDAOImpI(db);
        proDAO = new EdcsMasProcessDAOImpI(db);
        depDAO = new EdcsMasDepartmentDAOImpl(db);
        equipConDAO = new EdcsMasEquipconDAOImpl(db);
        EdcsCalibration calibration = calibDAO.find(Integer.valueOf(calId));
        model.addAttribute("departments", depDAO.findByFlag(0));
        model.addAttribute("processes", proDAO.findByFlag(0));
        model.addAttribute("models", modelDAO.findByFlag(0));
        model.addAttribute("units", unitDAO.findByFlag(0));
        model.addAttribute("equipConditions", equipConDAO.findByFlag(0));
        model.addAttribute("calibration", calibration);
        return "createDoc";
    }

    @RequestMapping("/createDetail.htm")
    public String createDetail(@RequestParam String calId, Model model, HttpSession session) {
        calpointDAO = new EdcsMasCalpointDAOImpl(db);
        itemDAO = new EdcsCalibrationAttachitemDAOImpl(db);
        EdcsCalibration calibration = calibDAO.find(Integer.valueOf(calId));
        EdcsMasCalpoint calPoint = calpointDAO.find(calibration.getAssociateMeasure().getCalpointId());
        List<EdcsCalibrationAttachHead> calibrationAttachmentHeadSet = new ArrayList<>();
        EdcsCalibrationAttachHead attachment = new EdcsCalibrationAttachHead();
        attachment.setCalId(calibration.getCalId());
        attachment.setAbType("A");
        attachment.setActiveRange(calPoint.getCalpointMin() + "-" + calPoint.getCalpointMax());
        EdcsMasMeasure modelMeasure = measureDAO.find(calibration.getAssociateModel().getMeasureId());
        attachment.setEdcsCalibrationAttachItemCollection(itemDAO.createAttachItemInputTemplateForAttachHeadModelFromCalibration(calibration));
        calibrationAttachmentHeadSet.add(0, attachment);
        //if calibration has type AB mean we have to add head type b too
        if (calibration.getAssociateMeasure().getAbType().equals("AB")) {
            attachment.setAbType("B");
            calibrationAttachmentHeadSet.add(1, attachment);
        }

        calibration.setEdcsCalibrationAttachHeadCollection(calibrationAttachmentHeadSet);
//        model.addAttribute("attachment", attachment);
        model.addAttribute("calibration", calibration);
        model.addAttribute("modelMeasure", modelMeasure);
        return "createDetail";
    }

    @RequestMapping("/approveDoc.htm")
    public String approveDoc(Model model, HttpSession session) {
        return "approveDoc";
    }

    @RequestMapping("/trackingDoc.htm")
    public String trackingDoc(Model model, HttpSession session) {
        return "trackingDoc";
    }

    @RequestMapping("/deviceReceivce.htm")
    public String deviceReceivce(Model model, HttpSession session) {
        return "deviceReceivce";
    }

    @RequestMapping("/deviceSend.htm")
    public String deviceSend(Model model, HttpSession session) {
        return "deviceSend";
    }

    @RequestMapping("/labApprove.htm")
    public String labApprove(Model model, HttpSession session) {
        return "labApprove";
    }

    @RequestMapping("/checkDoc.htm")
    public String checkDoc(Model model, HttpSession session) {
        return "checkDoc";
    }

    @RequestMapping("/printSticker.htm")
    public String printSticker(Model model, HttpSession session) {
        return "printSticker";
    }

    @RequestMapping("/createSearch.htm")
    public String createSearch(Model model, HttpSession session) {
        return "createSearch";
    }

}
