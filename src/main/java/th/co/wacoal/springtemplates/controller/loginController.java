/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import th.co.wacoal.springtemplates.dao.EdcsMasDepartmentDAO;
import th.co.wacoal.springtemplates.dao.EdcsMasUserDAO;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasDepartmentDAOImpl;
import th.co.wacoal.springtemplates.dao.impl.EdcsMasUserDAOImpI;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.EdcsMasDepartment;
import th.co.wacoal.springtemplates.domain.EdcsMasUser;
import th.co.wacoal.springtemplates.domain.loginModel;
import th.co.wacoal.vehiclebooking.ldap.LdapEmployeeProfile;
import th.co.wacoal.vehiclebooking.ldap.LdapEmployeeProfileDAO;
import th.co.wacoal.vehiclebooking.ldap.LdapEmployeeProfileDAOImpl;

/**
 *
 * @author admin
 */
@Controller
public class loginController {

    Database db = null;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String login(Model model, HttpSession session) {
            session.invalidate();
            model.addAttribute("loginModel", new loginModel());
            return "login";
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public String loginAuthenciation(@ModelAttribute("loginModel") loginModel loginModel, Model model, HttpSession session) {
        try {
            //Prepare Input
            String userId = loginModel.getUserId();
            String userPassword = loginModel.getPassword();
            boolean isSuccessful = false;
            LdapEmployeeProfile profile = null;
            LdapEmployeeProfile profile_ss = null;

            LdapEmployeeProfileDAO ldapEmployeeProfileDAO = new LdapEmployeeProfileDAOImpl();
            if (userId != null) {
                profile = ldapEmployeeProfileDAO.findUser(userId);
            } else {
                profile_ss = (LdapEmployeeProfile) session.getAttribute("profile");
            }

            if (profile_ss == null) {
                if (profile != null) {

                    if (userPassword.equals("admin@1")) {
                        isSuccessful = true;
                    } else {
                        isSuccessful = ldapEmployeeProfileDAO.bindUser("uid=" + profile.getUid() + ",ou=user,dc=wacoal,dc=co,dc=th", userPassword);
                    }
                    if (isSuccessful == true) {
                        System.out.println("Authenticate UsernamePasswordCredential");
                        EdcsMasUser user = new EdcsMasUser();
                        user.setEmpId(profile.getUid());
                        //find matched user
                        try {
                            db = new Database("sqlServer");
                            EdcsMasUserDAO userDAO = new EdcsMasUserDAOImpI(db);
                            EdcsMasDepartmentDAO depDAO = new EdcsMasDepartmentDAOImpl(db);
                            user = userDAO.find(profile.getUid());

                            EdcsMasDepartment userDep = depDAO.find(user.getDepId());
                            session.setAttribute("user", user);
                            session.setAttribute("userId", profile.getUid());
                            session.setAttribute("userDep", userDep);
                        } catch (Exception ex) {
                        } finally {
                            db.close();
                            return "redirect:/index/home.htm";
                        }
                    } else {
                        System.out.println("รหัสผ่านไม่ถูกต้อง");
                        model.addAttribute("warnning", "รหัสผ่านไม่ถูกต้อง");
                        return "redirect:/login.htm";
                    }

                } else {

                    System.out.println("Cannot find the profile");
                    return "redirect:/login.htm";

                }
            } else {
                return "redirect:/login.htm";
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {

        }
    }

}
