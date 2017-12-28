/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import th.co.wacoal.springtemplates.dao.ChkMasBoxcodeDAO;
import th.co.wacoal.springtemplates.db.Database;
import th.co.wacoal.springtemplates.domain.ChkMasBoxcode;

/**
 *
 * @author admin
 */
public class ChkMasBoxcodeDAOImpl implements ChkMasBoxcodeDAO {
    
    private Database db;
    
    public ChkMasBoxcodeDAOImpl(Database db) {
        this.db = db;
    }
    
    @Override
    public List<ChkMasBoxcode> findAll() {
        String sql = "select * from CHK_MAS_BOXCODE";
        List<Map<String, Object>> rs = db.queryList(sql);
        List<ChkMasBoxcode> ret = new ArrayList<ChkMasBoxcode>();
        for (Map<String, Object> map : rs) {
            ChkMasBoxcode p = new ChkMasBoxcode();
            p.setBoxCode((String) map.get("BOX_CODE"));
            p.setBoxCodeName((String) map.get("BOX_CODE_NAME"));
            ret.add(p);
        }
        return ret;
    }
    
    
    
}
