package cn.starrys.util.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.mail.Authenticator;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.jetbrains.annotations.NotNull;

import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件工具类
 * creationTime: 2022/12/5 8:35
 *
 * @author XingKong
 */
public class MailUtils {

    public static void main(String[] args) throws NoSuchProviderException, GeneralSecurityException {
        Session session = getSession("null", "null", "null", "null", "测试", 994, true, true, true);
        Properties properties = session.getProperties();
        String property = properties.getProperty("mail.*.auth");
        System.err.println(property);
        // for (Map.Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
        //     System.err.println(objectObjectEntry);
        // }
    }

    public static Session getSession(
            @NotNull String protocol,
            @NotNull String host,
            @NotNull String from,
            @NotNull String password,
            String nickname,
            Integer port,
            boolean ssl,
            boolean auth,
            boolean debug
    ) throws GeneralSecurityException {
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
            MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustedHosts(host);
            props.setProperty("mail." + protocol + ".ssl.enable", "true");
            props.setProperty("mail." + protocol + ".ssl.socketFactory", mailSSLSocketFactory.toString());
        }

        if (auth) props.setProperty("mail." + protocol + ".auth", "true");  // 自动认证
        if (debug) props.setProperty("mail.debug", "true");                 // debug
        return getSession(props);
    }

    public static Session getSession(Properties props) {
        if (Boolean.parseBoolean(props.getProperty("mail." + props.getProperty("protocol") + ".auth")))
            return Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("mail.from"), props.getProperty("mail.password"));
                }
            });
        return Session.getDefaultInstance(props);
    }

}
