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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import th.co.wacoal.springtemplates.dao.EdcsCalibrationDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsCalibrationDAOImpI;
import th.co.wacoal.springtemplates.dao.impl.stickerReserveDAOImpI;
import th.co.wacoal.springtemplates.dao.stickerReserveDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;
import th.co.wacoal.springtemplates.domain.StickerReserve;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/sticker")
public class StickerReserveController {

    @RequestMapping(value = "/listReservedSticker", method = RequestMethod.GET)
    public void listReservedSticker(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasUser user = new EdcsMasUser();
        user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        String responseMessage = null;
        try {
            stickerReserveDAO stickerDAO = new stickerReserveDAOImpI(db);
            List<StickerReserve> stickers = stickerDAO.findStickersByUserId(user.getEmpId());
            // fill in blank sticker template
            //maximum sticker per page is 77

            JSONArray jsonString = JSONArray.fromObject(stickers);
            //Go to view
            responseMessage = "{" + "\"size\":\"" + stickers.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/clearStickersReserve", method = RequestMethod.POST)
    public void clearAndAddNewCache(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasUser user = new EdcsMasUser();
        user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        String responseMessage = null;
        try {
            db.beginTransaction();
            stickerReserveDAO stickerDAO = new stickerReserveDAOImpI(db);
            stickerDAO.clearStickerByUserId(user.getEmpId());
            db.commit();
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/addStickersByCalIds", method = RequestMethod.POST)
    public void addStickersByCalIds(@RequestParam(value = "checkedValues") String calIdsStringForm, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasUser user = new EdcsMasUser();
        user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        String responseMessage = null;
        String[] calIds = calIdsStringForm.split(",");
        try {
            db.beginTransaction();
            stickerReserveDAO stickerDAO = new stickerReserveDAOImpI(db);

            List<StickerReserve> stickers = new ArrayList<>();
            for (String calId : calIds) {
                StickerReserve sticker = new StickerReserve();
                int calIdInt = Integer.parseInt(calId);
                sticker.setCalId(calIdInt);
                sticker.setEmpId(user.getEmpId());
                stickers.add(sticker);
            }
            stickerDAO.addStickersToReserve(stickers);
            db.commit();
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/deleteStickersFromReserve", method = RequestMethod.POST)
    public void deleteStickersFromReserve(@RequestParam(value = "stickerCheckedValues") String stickerStringForm, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasUser user = new EdcsMasUser();
        user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        String responseMessage = null;
        String[] stickersId = stickerStringForm.split(",");
        try {
            db.beginTransaction();
            stickerReserveDAO stickerDAO = new stickerReserveDAOImpI(db);
            for (String id : stickersId) {
                stickerDAO.deleteSticker(Integer.parseInt(id));
            }
            db.commit();
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/printProcessedStickers", method = RequestMethod.POST)
    public void printProcessedStickers(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasUser user = new EdcsMasUser();
        user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        String responseMessage = null;
        try {
            db.beginTransaction();
            stickerReserveDAO stickerDAO = new stickerReserveDAOImpI(db);
            EdcsCalibrationDAO calibDAO = new EdcsCalibrationDAOImpI(db);
            List<StickerReserve> stickers = stickerDAO.findStickersByUserId(user.getEmpId());
            for (StickerReserve sticker : stickers) {
                //mark calib as printed
                calibDAO.printedStickerCheck(sticker.getCalId(), sticker.getEmpId());
            }
            //clear reserve
            stickerDAO.clearStickerByUserId(user.getEmpId());
            db.commit();
            responseMessage = "success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            responseMessage = ex.getMessage();
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

}
