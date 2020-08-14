package vip.bigeye.order.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-14 21:33
 */
@Data
public class Orders {
    private String id;//订单ID
    private String ordersDate;//订单创建日期
    private String client;//客户信息
    private String product;//产品
    private String  parcel;//快递公司
    private String  parcelNumber;//快递单号
    private Integer ordersNumber;//产品数量
    private String ordersRequire;//发货要求
    private String clientNote;//交易备注
    private String collect;//是否代收
    private String person;//产品负责人
    private String factoryNote;//厂家备注
    private String returns;//订单状态
    private String factoryUsername;//厂家用户名
    private String businessUsername;//商家用户名
    private String factoryName;//厂家名称
    private String businessName;//商家名称
    private String sendDate;//发货日期

}
