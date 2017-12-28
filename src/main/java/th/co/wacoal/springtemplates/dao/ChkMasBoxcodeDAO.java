/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.ChkMasBoxcode;

/**
 *
 * @author admin
 */
public interface ChkMasBoxcodeDAO {
    
    public List<ChkMasBoxcode> findAll();
    
}
