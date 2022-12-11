package cn.starrys.util.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * 邮件
 * <p>
 * creationTime: 2022/12/5 9:43
 *
 * @author XingKong
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    /**
     * 发信人昵称
     */
    private String nickname;

    /**
     * 发件人
     */
    private String from;

    /**
     * “收件人”(主要收件人)。
     */
    private String[] to;

    /**
     * 抄送(carbon copy)收件人。
     */
    private String[] cc;

    /**
     * 密送(blind carbon copy)收件人。
     */
    private String[] bcc;

    /**
     * 邮件主题（标题）
     */
    private String subject;

    /**
     * 邮件内容（主体）
     */
    private String body;

    /**
     * 邮件格式
     */
    private EmailFormat format;

    /**
     * 附件
     */
    private File[] attachments;

    public enum EmailFormat {
        TEXT, HTML
    }

}
