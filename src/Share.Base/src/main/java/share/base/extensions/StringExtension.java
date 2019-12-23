package share.base.extensions;

import org.apache.commons.lang3.StringUtils;

import static share.base.extensions.ObjectExtension.isNull;

public class StringExtension {
    /**
     * 空字符串
     */
    public static final String Empty = "";

    /**
     * 同数据库中的nvl
     *
     * @param str
     * @param val
     * @return
     */
    public static String nvl(String str, String val) {
        if (isNull(str)) return val;
        return str;
    }

    /**
     * 判断字符串是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrWhiteSpace(String obj) {
        return obj == null || obj.length() == 0 || obj.equals(Empty) || obj.trim().equals(Empty);
    }

    /**
     * 对象转换为字符串，如果是null则返回空字符串，解决空指针问题
     *
     * @param obj
     * @return
     */
    public static String asString(Object obj) {
        if (obj == null)
            return Empty;
        return obj.toString();
    }

    /**
     * 将字节数组的内容转换为字符串，如果是null则返回空字符串，解决空指针问题
     *
     * @param obj
     * @return
     */
    public static String asString(byte[] obj) {
        if (obj == null)
            return Empty;
        return new String(obj);
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String asLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String asUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 字符串左移
     *
     * @param src    需要左移的字符串
     * @param size   左右的位数
     * @param padStr 补充的字符串
     * @return
     */
    public static String padLeft(String src, int size, String padStr) {
        return StringUtils.leftPad(src, size, padStr);
    }

    /**
     * 字符串右移
     *
     * @param src    需要右移的字符串
     * @param size   右移的个数
     * @param padStr 补充的字符串
     * @return
     */
    public static String padRight(String src, int size, String padStr) {
        return StringUtils.rightPad(src, size, padStr);
    }
}
