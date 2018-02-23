/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.vehiclebooking.ldap;

import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

/**
 *
 * @author user
 */
public class LdapResultSetBuilderImpl implements LdapResultSetBuilder {
    public LdapResultSet buildLdapResultSet(SearchResult result, String[] attributesToReturn) throws NamingException {
                
                if(result == null)
                        return null;
                
                LdapResultSet resultSet = new LdapResultSet();
                Attributes attributes = result.getAttributes();
                Map<String, Object> attributesMap = new HashMap<String, Object>();
                
                if(attributesToReturn == null){
                        
                        resultSet.setAttributeMap(null);
                }else{
                        
                        for(String attribute : attributesToReturn){
                                Attribute attr = attributes.get(attribute);
                                if(attr != null)
                                        attributesMap.put(attribute,  attr.get());
                                else
                                        attributesMap.put(attribute,  null);
                        }
                        
                        resultSet.setAttributeMap(attributesMap);
                }
                
                return resultSet;
        }
}
