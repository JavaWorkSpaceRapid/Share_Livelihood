package share.livelihood.persongroup.utils;

import lycan.base.extensions.StringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolUtil
{

    /**
     * 判断身份证是否输入错误
     *
     * @param input
     * @return
     */
    public static boolean checkIdCard(String input)
    {
        Pattern p = null;
        if (input.trim().length() == 15)
        {
            p = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"); // 15位
        }
        else if (input.trim().length() == 18)
        {
            p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$"); // 18位
        }
        if (p != null)
        {
            Matcher m = p.matcher(input);
            if (m.matches())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断输入的手机号码是否正确
     *
     * @param input
     * @return
     */
    public static boolean checkPhone(String input)
    {
//        Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,1,3,6,7,8])|(18[0-9]))\\d{8}$");
        Pattern p = Pattern.compile("^[1][0-9]{10}$");
        Matcher m = p.matcher(input);
        if (m.matches())
        {
            return true;
        }
        return false;
    }

    /**
     * 计算时间相差的天数
     *
     * @param beginDate
     * @param endDate
     * @return bwDate
     * @author SunDawei
     * @Date 2018-01-15
     */
    public static int timeDifference(Date beginDate, Date endDate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date bDate = null;
        Date eDate = null;
        try
        {
            System.out.println(df.format(beginDate) + "=" + df.format(endDate));
            bDate = df.parse(df.format(beginDate));
            eDate = df.parse(df.format(endDate));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        int bwDate = (int) ((eDate.getTime() - bDate.getTime()) / 1000);
        return bwDate;
    }

    public static StringBuilder getParamsStrBf(Map<String, Object> params)
    {
        StringBuilder sb = new StringBuilder();
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<String, Object>(params);
        // 遍历排序的字典,并拼接"key=value"格式
        int i = 0;
        for (Map.Entry<String, Object> entry : sortParams.entrySet())
        {
            if(null != entry.getValue() && !StringExtension.isNullOrWhiteSpace(entry.getValue().toString()))
            {
                if (i > 0)
                {
                    sb.append("&");
                }
//            String str="";
//            if(entry.getValue()!=null)
//            {
//                str=entry.getValue().toString();
//            }
                sb.append(entry.getKey()).append("=").append(entry.getValue());
            }
            i++;
        }
        return sb;
    }


    public static Map<String, Object> getParamsMap(Map<String, Object> params)
    {
        Map<String, Object> paramsMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet())
        {
            if (null != entry.getValue() && !StringExtension.isNullOrWhiteSpace(entry.getValue().toString()))
            {
                paramsMap.put(entry.getKey(),entry.getValue());
            }
        }
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<String, Object>(paramsMap);
        return sortParams;
    }

}
