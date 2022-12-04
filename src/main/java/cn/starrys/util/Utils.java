package cn.starrys.util;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static cn.starrys.util.StringUtils.isEmpty;

/**
 * 工具类
 * <p>
 * creationTime: 2022/8/17 15:45
 *
 * @author XingKong
 */
public class Utils {

    /**
     * 获取一串随机的32位UUID
     *
     * @return 一串32位长度的UUID
     */
    public static @NotNull String uuid() {
        // 产生一串随机的 UUID 并转换为 String
        String uuidString = UUID.randomUUID().toString();
        // 将 UUID 中的 “-” 去除
        return uuidString.replace("-", "");
    }

    /**
     * 获取IP地址（适用于javax.servlet）
     *
     * @param request javax.servlet.http.HttpServletRequest
     * @return IP地址
     */
    public static String requestGetIp(javax.servlet.http.HttpServletRequest request) {
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

        // 目的是将localhost访问对应的ip 0:0:0:0:0:0:0:1 转成 127.0.0.1。
        return "0:0:0:0:0:0:0:1".equals(ipStr) ? "127.0.0.1" : ipStr;
    }

    /**
     * 获取IP地址（适用于jakarta.servlet）
     *
     * @param request jakarta.servlet.http.HttpServletRequest
     * @return IP地址
     */
    public static String requestGetIp(jakarta.servlet.http.HttpServletRequest request) {
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

        // 目的是将localhost访问对应的ip 0:0:0:0:0:0:0:1 转成 127.0.0.1。
        return "0:0:0:0:0:0:0:1".equals(ipStr) ? "127.0.0.1" : ipStr;
    }

}
