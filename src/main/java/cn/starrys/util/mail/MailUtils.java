package cn.starrys.util.mail;

import cn.starrys.util.mail.entity.Mail;
import cn.starrys.util.mail.entity.MailAddress;
import cn.starrys.util.mail.entity.MailProps;
import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件工具类
 * creationTime: 2022/12/5 8:35
 *
 * @author XingKong
 * @since 1.0.0
 */
public class MailUtils {

    /**
     * 获取 Session
     *
     * @param props 配置
     * @return jakarta.mail.Session
     */
    private static Session getSession(Properties props) {
        if (Boolean.parseBoolean(props.getProperty("mail." + props.getProperty("protocol") + ".auth")))
            return Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("mail.from"), props.getProperty("mail.password"));
                }
            });
        return Session.getDefaultInstance(props);
    }

    public static Session getSession(MailProps mailProps) {
        String protocol = mailProps.getProtocol();
        String host = mailProps.getHost();
        String from = mailProps.getFrom();
        String password = mailProps.getPassword();
        String nickname = mailProps.getNickname();
        Integer port = mailProps.getPort();
        boolean ssl = mailProps.isSsl();
        boolean auth = mailProps.isAuth();
        boolean debug = mailProps.isDebug();

        Properties props = new Properties();

        props.setProperty("protocol", protocol);

        switch (protocol) {
            case "imap", "pop3" -> props.setProperty("mail.store.protocol", protocol);  // 接收邮件时分配给协议的名称
            default -> props.setProperty("mail.transport.protocol", protocol);          // 发送邮件时分配给协议的名称
        }

        props.setProperty("mail.host", host);           // 邮箱服务器地址
        props.setProperty("mail.from", from);           // 发件邮箱
        props.setProperty("mail.password", password);   // 发件邮箱授权码
        if (null != nickname) props.setProperty("mail.user", nickname);     // 发信昵称
        if (null != port) props.setProperty("mail." + protocol + ".port", port.toString());    // 端口

        if (ssl) { // 开启 ssl
            try {
                MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
                mailSSLSocketFactory.setTrustedHosts(host);
                props.setProperty("mail." + protocol + ".ssl.enable", "true");
                props.setProperty("mail." + protocol + ".ssl.socketFactory", mailSSLSocketFactory.toString());
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

        if (auth) props.setProperty("mail." + protocol + ".auth", "true");  // 自动认证
        if (debug) props.setProperty("mail.debug", "true");                 // debug

        return getSession(props);
    }

    public static MimeMessage createMessage(Session session, Mail mail) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage = new MimeMessage(session);

        // 发件人
        mimeMessage.setFrom(new InternetAddress(session.getProperty("mail.from"), session.getProperty("mail.user"), "UTF-8"));

        List<MailAddress> mailTo = mail.getTo();
        // 收件人必须要有
        if (mailTo == null) throw new MessagingException("至少需要一位收件人");
        InternetAddress[] to = new InternetAddress[mailTo.size()];
        for (int i = 0; i < to.length; i++) {
            MailAddress addressee = mailTo.get(i);
            to[i] = new InternetAddress(addressee.getAddress(), addressee.getNickname(), "UTF-8");
        }
        // 收件人
        mimeMessage.setRecipients(Message.RecipientType.TO, to);

        List<MailAddress> mailCc = mail.getCc();
        // 抄送人可以没有
        if (mailCc != null) {
            InternetAddress[] cc = new InternetAddress[mailCc.size()];
            for (int i = 0; i < cc.length; i++) {
                MailAddress addressee = mailCc.get(i);
                cc[i] = new InternetAddress(addressee.getAddress(), addressee.getNickname(), "UTF-8");
            }
            // 抄送人
            mimeMessage.setRecipients(Message.RecipientType.CC, cc);
        }

        List<MailAddress> mailBcc = mail.getBcc();
        // 密送人可以没有
        if (mailBcc != null) {
            InternetAddress[] bcc = new InternetAddress[mailBcc.size()];
            for (int i = 0; i < bcc.length; i++) {
                MailAddress addressee = mailBcc.get(i);
                bcc[i] = new InternetAddress(addressee.getAddress(), addressee.getNickname(), "UTF-8");
            }
            // 密送人
            mimeMessage.setRecipients(Message.RecipientType.BCC, bcc);
        }

        // 邮件主题
        mimeMessage.setSubject(mail.getSubject());

        // 邮件体
        Multipart mimeMultipart = new MimeMultipart();

        // 邮件内容
        MimeBodyPart mimeBodyPart = new MimeBodyPart();

        // html格式
        mimeBodyPart.setContent(mail.getBody(), "text/html;charset=utf-8");

        // 添加邮件内容
        mimeMultipart.addBodyPart(mimeBodyPart);

        // File[] attachments = mail.getAttachments();
        List<File> attachments = mail.getAttachments();
        try {
            for (File attachment : attachments) {
                MimeBodyPart fileBodyPart = new MimeBodyPart();
                fileBodyPart.attachFile(attachment);
                mimeMultipart.addBodyPart(fileBodyPart);
            }
        } catch (Exception e) {
            System.err.println("添加附件失败");
            e.printStackTrace();
        }

        // 设置邮件体
        mimeMessage.setContent(mimeMultipart);
        // 发送时间
        mimeMessage.setSentDate(new Date());

        // 保存上面设置的邮件
        mimeMessage.saveChanges();

        return mimeMessage;
    }

}
