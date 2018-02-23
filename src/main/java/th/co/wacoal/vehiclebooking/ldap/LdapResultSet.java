/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

import java.util.Map;

/**
 *
 * @author user
 */
public class LdapResultSet {

    Map<String, Object> attributeMap;

    public final Map<String, Object> getAttributeMap() {
        return attributeMap;
    }

    public final void setAttributeMap(Map<String, Object> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public Object getAttribute(String attribute) {

        if (attributeMap == null) {

            return null;
        } else {

            return attributeMap.get(attribute);
        }
    }
}
