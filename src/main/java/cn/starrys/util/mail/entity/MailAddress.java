package cn.starrys.util.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * creationTime: 2022/12/12 16:28
 *
 * @author XingKong
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailAddress {
    private String address;
    private String nickname;
}
