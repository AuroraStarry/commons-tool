package cn.starrys.util;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    /**
     * 计算字符串的MD5
     *
     * @param text 要计算的字符串（若为空则返回 ""）
     * @return <code>text</code> 的MD5
     */
    public static @NotNull String md5(String text) {
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

}
