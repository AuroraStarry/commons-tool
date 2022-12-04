package cn.starrys.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;

/**
 * 时间工具类
 * <p>
 * creationTime: 2022/12/4 16:51
 *
 * @author XingKong
 */
public class DateUtils {

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
     * 获取当前时间戳
     *
     * @return Timestamp类型的当前时间
     */
    @Contract(" -> new")
    public static @NotNull Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
