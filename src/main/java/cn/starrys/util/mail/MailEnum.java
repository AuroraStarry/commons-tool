package cn.starrys.util.mail;

/**
 * creationTime: 2022/12/5 8:44
 *
 * @author XingKong
 */
public enum MailEnum {
    MAIL_GMAIL("imap.gmail.com", "993"),
    MAIL_QQ("imap.qq.com", "993"),
    MAIL_163("imap.163.com", "993"),
    MAIL_126("", "");

    private final String imap;
    private final String port;

    MailEnum(String imap, String port) {
        this.imap = imap;
        this.port = port;
    }

    @Override
    public String toString() {
        return "MailEnum{" +
                "host='" + imap + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
