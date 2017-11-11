package com.family.financial.management.utils;

import com.family.financial.management.exception.FFMException;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.family.financial.management.emun.FFMExceptionEnum.ERROR_PARAMETER;

/**
 * Created by zhangyiping on 2017/10/14.
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        return ((str == null)||("".equals(str)));
    }
    public static Long praseLong(String str) throws FFMException {
        try {
            return Long.parseLong(str);
        }catch (Exception e){
            throw new FFMException(ERROR_PARAMETER);
        }
    }

    public static Integer praseInteger(String str) throws FFMException {

        try {
            return Integer.parseInt(str);
        }catch (Exception e){
            throw new FFMException(ERROR_PARAMETER);
        }
    }
    public static Date praseDate(String str) throws FFMException {
        try {
            DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = df.parse(str);
            return date;
        }catch (Exception e){
            throw new FFMException(ERROR_PARAMETER);
        }
    }

    public static List<String> praseList(String str) throws FFMException {
        try {
            String[] strs = str.split(",");
            List<String> list = Arrays.asList(strs);
            return list;
        }catch (Exception e){
            throw new FFMException(ERROR_PARAMETER);
        }
    }
    public static void main(String[] args) throws FFMException {
        String time = "2017/07/20 04:20:11";
        Date date = praseDate(time);
        System.out.println(date);
    }
}
