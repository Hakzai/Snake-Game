/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.helper;

import com.akeir.global.Constants;
import com.akeir.global.MessageLog;

/**
 *
 * @author Codeiro
 */
public class Utils {
    
    private Utils() { }
    
    public static final int stringToInt(String number)
    {
        int result;
        
        try
        {
            result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            MessageLog.EXCEPTION(e);
            return -1;
        }
        
        return result;
    }
    
    public static final int integerIncreaseByOne(int number)
    {
        return number + Constants.ONE_AS_INTEGER;
    }
    
    public static final int integerIncreaseByTen(int number)
    {
        return number + Constants.TEN_AS_INTEGER;
    }
    
}
