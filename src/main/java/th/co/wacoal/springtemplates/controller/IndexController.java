/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationAttachItemDAO;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasMeasureDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasModelDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationAttachitemDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasCalageDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasCalpointDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasDepartmentDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasEquipconDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasMeasureUnitDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasModelDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasProcessDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasStatusCaldocDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsCalibration;
import th.co.wacoal.springtemplates.domain.EdcsCalibrationAttachHead;
import th.co.wacoal.springtemplates.domain.EdcsMasCalpoint;
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;
import th.co.wacoal.springtemplates.domain.EdcsMasMeasure;
import th.co.wacoal.springtemplates.domain.EdcsMasStatusCaldoc;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateFormat.setLenient(false);
        //for attachHeader
        binder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/home.htm")
    public String home(Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        try {
            // session.setAttribute("userId", "92612");
            // Model
            EdcsMasUser user = new EdcsMasUser();
            user = (EdcsMasUser) session.getAttribute("user");
            model.addAttribute("user", user);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.addAttribute("error", ex.getMessage());
            return "error";
        } finally {
            db.close();
            return "index";
        }
    }

    @RequestMapping("/createDoc.htm")
    public String createDoc(@RequestParam String calId, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        try {
            EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
            EdcsMasMeasureUnitDAOImpl unitDAO = new EdcsMasMeasureUnitDAOImpl(db);
            EdcsCalibrationDAOImpI calibDAO = new EdcsCalibrationDAOImpI(db);
            EdcsMasProcessDAOImpI proDAO = new EdcsMasProcessDAOImpI(db);
            EdcsMasDepartmentDAOImpl depDAO = new EdcsMasDepartmentDAOImpl(db);
            EdcsMasEquipconDAOImpl equipConDAO = new EdcsMasEquipconDAOImpl(db);
            EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
            EdcsMasModelDAO modelDAO = new EdcsMasModelDAOImpI(db);

            EdcsCalibration calibration = calibDAO.find(Integer.valueOf(calId));
            if (calibration.getCalibratorBy() == null || calibration.getCalibratorBy().length() <= 0) {
                calibration.setCalibratorBy(user.getEmpId());
            }
            calibration.setCalibrationStatusBy(user.getEmpId());

            EdcsMasUser calibrator = userDAO.find(calibration.getCalibratorBy());
            model.addAttribute("departments", depDAO.findByFlag(0));
            model.addAttribute("processes", proDAO.findByFlag(0));
            model.addAttribute("models", modelDAO.findByFlag(0));
            model.addAttribute("units", unitDAO.findByFlag(0));
            model.addAttribute("equipConditions", equipConDAO.findByFlag(0));
            model.addAttribute("calibration", calibration);
            model.addAttribute("calibrator", calibrator);
            model.addAttribute("approvers", userDAO.findByViewableDepId(user.getDepId()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return "createDoc";
        }

    }

    @RequestMapping("/createDetail.htm")
    public String createDetail(@RequestParam String calId, Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        try {
            EdcsMasCalpointDAOImpl calpointDAO = new EdcsMasCalpointDAOImpl(db);
            EdcsCalibrationAttachItemDAO itemDAO = new EdcsCalibrationAttachitemDAOImpl(db);
            EdcsMasStatusCaldocDAOImpl statusDocDAO = new EdcsMasStatusCaldocDAOImpl(db);
            EdcsMasCalageDAOImpI calAgeDao = new EdcsMasCalageDAOImpI(db);
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            EdcsMasMeasureDAO measureDAO = new EdcsMasMeasureDAOImpI(db);

            EdcsCalibration calibration = calibDAO.find(Integer.valueOf(calId));
            EdcsMasCalpoint calPoint = calpointDAO.find(calibration.getAssociateMeasure().getCalpointId());
            List<EdcsCalibrationAttachHead> calibrationAttachmentHeadSet = new ArrayList<>();
            EdcsMasMeasure modelMeasure = measureDAO.find(calibration.getAssociateModel().getMeasureId());
            List<EdcsMasStatusCaldoc> statDoc = statusDocDAO.findByFlag(0);

            String ActiveRange = calPoint.getCalpointMin() + "-" + calPoint.getCalpointMax();
            if (calibration.getEdcsCalibrationAttachHeadList() == null || calibration.getEdcsCalibrationAttachHeadList().size() <= 0) {
                EdcsCalibrationAttachHead attachment = new EdcsCalibrationAttachHead();
                attachment.setCalId(calibration.getCalId());
                attachment.setAcceptance(calibration.getCalError());
                attachment.setAbType("A");
                attachment.setCalDate(new Date());
                attachment.setActiveRange(ActiveRange);
                attachment.setEdcsCalibrationAttachItemList(itemDAO.createAttachItemInputTemplateForAttachHeadModelFromCalibration(calibration));
                calibrationAttachmentHeadSet.add(0, attachment);
                //if calibration has type AB mean we have to add head type b too
                if (calibration.getAssociateMeasure().getAbType().equals("AB")) {

                    EdcsCalibrationAttachHead attachmentB = new EdcsCalibrationAttachHead();
                    attachmentB.setCalId(calibration.getCalId());
                    attachmentB.setAcceptance(calibration.getCalError());
                    attachmentB.setAbType("B");
                    attachmentB.setCalDate(new Date());
                    attachmentB.setActiveRange(ActiveRange);
                    attachmentB.setEdcsCalibrationAttachItemList(itemDAO.createAttachItemInputTemplateForAttachHeadModelFromCalibration(calibration));
                    calibrationAttachmentHeadSet.add(1, attachmentB);
                }
                calibration.setEdcsCalibrationAttachHeadList(calibrationAttachmentHeadSet);
            }
            calibration.setCalibrationAttachStatusOn(new Date());

            model.addAttribute("calibration", calibration);
            model.addAttribute("calDocConditions", statDoc);
            model.addAttribute("calAges", calAgeDao.findByFlag(0));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return "createDetail";
        }
    }

    @RequestMapping("/approveDoc.htm")
    public String approveDoc(Model model, HttpSession session) {
        return "approveDoc";
    }

    @RequestMapping("/trackingDoc.htm")
    public String trackingDoc(Model model, HttpSession session) {
        return "trackingDoc";
    }

     @RequestMapping("/stickerTableTemplate.htm")
    public String stickerTableTemplate(Model model, HttpSession session) {
        return "stickerTableTemplate";
    }
    
    @RequestMapping("/deviceReceivce.htm")
    public String deviceReceivce(Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        try {
            EdcsMasEquipconDAOImpl equipConDAO = new EdcsMasEquipconDAOImpl(db);
            model.addAttribute("conditions", equipConDAO.findByFlag(0));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return "deviceReceivce";
        }
    }

    @RequestMapping("/deviceSend.htm")
    public String deviceSend(Model model, HttpSession session) {
        Database db = new Database("sqlServer");
        try {
            EdcsMasEquipconDAOImpl equipConDAO = new EdcsMasEquipconDAOImpl(db);
            model.addAttribute("conditions", equipConDAO.findByFlag(0));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return "deviceSend";
        }
    }

    @RequestMapping("/labApprove.htm")
    public String labApprove(Model model, HttpSession session) {
        return "labApprove";
    }

    @RequestMapping("/checkDoc.htm")
    public String checkDoc(Model model, HttpSession session) {

        EdcsMasUser user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        try {
            EdcsMasEquipconDAOImpl equipConDAO = new EdcsMasEquipconDAOImpl(db);
            EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
            EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
            List<EdcsMasDepartment> deps = new ArrayList<>();
            for (String viewable : user.getViewableDepartmentIds()) {
                deps.add(depDAO.find(viewable));
            }
            List<EdcsMasUser> viewableDepUsers = userDAO.findByViewableDepId(user.getDepId());
            List<EdcsMasUser> canApproveRequestUser = new ArrayList<>();
            for (EdcsMasUser viewableDepUser : viewableDepUsers) {
                //2 คือ หน้าอนุมัติส่งรายงานสอบเทียบ
                if (viewableDepUser.getUserAuthTypeIds().contains(2)) {
                    canApproveRequestUser.add(viewableDepUser);
                }
            }

            model.addAttribute("approver", canApproveRequestUser);
            model.addAttribute("deps", deps);
            model.addAttribute("conditions", equipConDAO.findByFlag(0));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            return "checkDoc";
        }

    }

    @RequestMapping("/printSticker.htm")
    public String printSticker(Model model, HttpSession session) {
        return "printSticker";
    }

    @RequestMapping("/createSearch.htm")
    public String createSearch(Model model, HttpSession session) {
        return "createSearch";
    }

    @RequestMapping("/authenticationError.htm")
    public String authenticationError(Model model, HttpSession session) {
        return "noAuthenticationError";
    }
}
