package cn.starrys.util.mail.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件
 * <p>
 * creationTime: 2022/12/5 9:43
 *
 * @author XingKong
 * @since 1.0.0
 */
@NoArgsConstructor
@ToString
public class Mail {

    /**
     * 收件人(主要收件人)。
     */
    private final List<MailAddress> to = new ArrayList<>(1);

    /**
     * 抄送(carbon copy)收件人。
     */
    private final List<MailAddress> cc = new ArrayList<>(0);

    /**
     * 密送(blind carbon copy)收件人。
     */
    private final List<MailAddress> bcc = new ArrayList<>(0);

    /**
     * 邮件主题（标题）
     */
    private String subject;

    /**
     * 邮件内容（主体）
     */
    private String body;

    /**
     * 附件
     */
    private final List<File> attachments = new ArrayList<>();

    public List<MailAddress> getTo() {
        return to;
    }

    public void addTo(MailAddress to) {
        this.to.add(to);
    }

    public List<MailAddress> getCc() {
        return cc;
    }

    public void addCc(MailAddress cc) {
        this.cc.add(cc);
    }

    public List<MailAddress> getBcc() {
        return bcc;
    }

    public void addBcc(MailAddress bcc) {
        this.bcc.add(bcc);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void addAttachments(File attachments) {
        this.attachments.add(attachments);
    }

}
