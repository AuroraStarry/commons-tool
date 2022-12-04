package cn.starrys.util;

/**
 * 字符串工具类
 * <p>
 * creationTime: 2022/12/4 16:56
 *
 * @author XingKong
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param str 进行判断的字符串
     * @return 当 str 为 null 或者 "" 时返回 true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 进行判断的字符串
     * @return 当 str 不为 null 或者 "" 时返回 true
     */
    public static boolean notEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

}
