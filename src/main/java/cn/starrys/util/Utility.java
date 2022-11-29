package cn.starrys.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author XingKong
 */
public class Utility {

    /**
     * 获取一串随机的32位UUID
     *
     * @return 一串32位长度的UUID
     */
    public static String uuid() {
        //产生一串随机的 UUID 并转换为 String
        String uuidString = UUID.randomUUID().toString();
        //将 UUID 中的 “-” 去除
        return uuidString.replace("-", "");
    }

    /**
     * 将小时转换为秒
     *
     * @param hour 小时
     * @return 传入小时的秒数
     */
    public static int convertHoursToSeconds(int hour) {
        return 60 * 60 * hour;
    }

    /**
     * 判断是否为空
     *
     * @param str 进行判断的字符串
     * @return 为 null 或空字符串为 true，不为空为 false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断是否不为空
     *
     * @param str 进行判断的字符串
     * @return 为 null 或空字符串为 false，不为空为 true
     */
    public static boolean notEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    /**
     * 获取当前时间戳
     *
     * @return Timestamp类型的当前时间
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 计算字符串的MD5
     *
     * @param text 要计算的字符串（若为空则返回 ""）
     * @return <code>text</code> 的MD5
     */
    public static String md5(String text) {
        if (isEmpty(text)) {
            System.err.println("不应该计算一个空值的字符串，因为没有意义。");
            return "";
        }

        MessageDigest msgDigest;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("当前系统不支持 MD5 算法。");
        }

        msgDigest.update(text.getBytes(StandardCharsets.UTF_8));

        // msgDigest.digest()：完成计算后的 byte 数组
        BigInteger bigInteger = new BigInteger(1, msgDigest.digest());

        // bigInteger.toString(16)：转换为16进制字符串
        StringBuilder strBuilder = new StringBuilder(bigInteger.toString(16));

        // MD5 长度
        final int md5Length = 32;

        while (strBuilder.length() < md5Length) {
            // 如果计算出的 MD5 不足32位则在前补"0"
            strBuilder.insert(0, "0");
        }

        return strBuilder.toString();
    }

    /**
     * 获取IP地址
     * <br>
     * 若使用Tomcat10及以上则应该导这个包：{@code jakarta.servlet.http.HttpServletRequest}
     *
     * @param request HttpServletRequest
     * @return IP地址
     */
    public static String requestGetIp(HttpServletRequest request) {
        if (request == null) {
            throw (new NullPointerException("HttpServletRequest对象为空"));
        }

        String unknown = "unknown";
        String ipStr = request.getHeader("x-forwarded-for");

        if (isEmpty(ipStr) || unknown.equalsIgnoreCase(ipStr)) {
            ipStr = request.getHeader("Proxy-Client-IP");
        }
        if (isEmpty(ipStr) || unknown.equalsIgnoreCase(ipStr)) {
            ipStr = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isEmpty(ipStr) || unknown.equalsIgnoreCase(ipStr)) {
            ipStr = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        String[] arr = ipStr.split(",");
        for (String str : arr) {
            if (!unknown.equalsIgnoreCase(str)) {
                ipStr = str;
                break;
            }
        }

        //目的是将localhost访问对应的ip 0:0:0:0:0:0:0:1 转成 127.0.0.1。
        return "0:0:0:0:0:0:0:1".equals(ipStr) ? "127.0.0.1" : ipStr;
    }

}
