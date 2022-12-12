package cn.starrys.util.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * creationTime: 2022/12/12 15:27
 *
 * @author XingKong
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailProps {

    /**
     * 邮件协议
     * <br>
     * 常用发件协议：smtp
     * <br>
     * 常用收件协议：imap，pop3
     */
    private @NotNull String protocol = "smtp";

    /**
     * 主机
     */
    private @NotNull String host;

    /**
     * 发件邮箱
     */
    private @NotNull String from;

    /**
     * 发件邮箱授权码
     */
    private @NotNull String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 主机端口
     */
    private Integer port;

    /**
     * 是否启用 ssl
     */
    private boolean ssl = true;

    /**
     * 是否开启自动授权
     */
    private boolean auth = true;

    /**
     * 是否开启debug
     */
    private boolean debug = false;

}
