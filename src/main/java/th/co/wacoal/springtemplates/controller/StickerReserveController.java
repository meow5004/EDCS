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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            List<StickerReserve> shownStickers = new ArrayList<>();
            StickerReserve shownSticker = new StickerReserve();
            for (int i = 1; i <= 77; i++) {
                shownSticker = new StickerReserve();
                for (StickerReserve sticker : stickers) {
                    if (sticker.getPrintOrder() == i) {
                        shownSticker.setPrintOrder(i);
                        shownSticker.setCalId(sticker.getCalId());
                        shownSticker.setEmpId(sticker.getEmpId());
                        shownSticker.setStickerId(sticker.getStickerId());
                    }
                }
                shownStickers.add(shownSticker);
            }
            JSONArray jsonString = JSONArray.fromObject(shownStickers);
            //Go to view
            responseMessage = "{" + "\"size\":\"" + stickers.size() + "\",\"data\":" + jsonString + "}";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            db.close();
            out.print(responseMessage);
        }
    }

    @RequestMapping(value = "/clearAndAddNewCache", method = RequestMethod.POST)
    public void clearAndAddNewCache(@ModelAttribute("stickers") List<StickerReserve> stickers, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        EdcsMasUser user = new EdcsMasUser();
        user = (EdcsMasUser) session.getAttribute("user");
        Database db = new Database("sqlServer");
        String responseMessage = null;
        try {
            db.beginTransaction();
            stickerReserveDAO stickerDAO = new stickerReserveDAOImpI(db);
            stickerDAO.clearStickerByUserId(user.getEmpId());
            int order = 1;
            for (StickerReserve sticker : stickers) {
                if (sticker.getCalId() != 0) {
                    sticker.setPrintOrder(order);
                    stickerDAO.addStickerToReserve(sticker);
                    order++;
                }
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

}
