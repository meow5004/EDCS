/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.controller;

/**
 *
 * @author admin
 */
public class HelperFunction {

    protected static Integer[] stringArrayToInt(String[] strings) {
        Integer[] intarray = new Integer[strings.length];
        int i = 0;
        for (String str : strings) {
            try {
                intarray[i] = Integer.parseInt(str);
                i++;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Not a number: " + str + " at index " + i, e);
            }
        }
        return intarray;
    }
}
