package vip.bigeye.order.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 11:24
 */
@Data

public class User {
    private String username;//用户名
    private String password;//密码
    private String type;//类型
    private String name;//昵称
    private long phone;//电话

}
