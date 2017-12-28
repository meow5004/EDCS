/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author admin
 */
@Controller
public class IndexController {

    @RequestMapping("/home.htm")
    public String home(Model model, HttpSession session) {
        //Database db = null;
        try {
            session.setAttribute("userId","92612");
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
    public String createDoc(Model model, HttpSession session) {
        return "createDoc";
    }

    @RequestMapping("/createDetail.htm")
    public String createDetail(Model model, HttpSession session) {
        return "createDetail";
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
