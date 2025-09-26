package org.liu.mockito.pojo.po;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pay_order")
@Data
public class PayOrder implements Persistable<Long> {

    /**
     * ID
     */
    @Id
    private Long payId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal payAmount;

    /**
     * 优惠金额
     */
    private BigDecimal preferentialAmount;

    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 押金
     */
    private BigDecimal depositAmount;

    /**
     * 支付方式（0-套餐支付，1-微信支付，22-刷卡）
     */
    private Integer payMethod;

    /**
     * 订单类型（0-购买套餐，1-租电池，2-租车，3-电池押金，4-租车押金，5-退还电池押金，6-退还租车押金，7-租电池免押金，8-租车免押金）
     */
    private Integer orderType;

    /**
     * 微信支付订单号
     */
    private String transactionId;

    /**
     * 微信转账单号
     */
    private String transferBillNo;

    /**
     * 微信付款单号
     */
    private String paymentNo;

    /**
     * 微信支付分订单ID
     */
    private String scoreOrderId;

    /**
     * 支付失效时间
     */
    private Date expireTime;

    /**
     * 获取支付结果的时间
     */
    private Date payResultTime;

    /**
     * 支付失败的原因
     */
    private String payFailureReason;

    /**
     * 套餐ID
     */
    private Long packageId;

    /**
     * 套餐下服务项ID
     */
    private Long packageItemId;

    /**
     * 换电站ID
     */
    private Long stationId;

    /**
     * 换电柜ID
     */
    private Long cabinetId;

    /**
     * 电池ID
     */
    private Long batteryId;

    /**
     * 电池型号
     */
    private String modelType;

    /**
     * 车辆ID
     */
    private Long vehicleId;

    /**
     * 代理商ID
     */
    private Long agentId;

    /**
     * 状态（0-待支付/已受理/已创建，1-支付成功/退款成功/已完结，2-支付失败/退款失败/创建支付分订单失败，3-已取消，4-进行中）
     */
    private Integer status;

    /**
     * 支付分订单状态（0-CREATED，1-DOING/USER_CONFIRM，2-DOING/MCH_COMPLETE/USER_PAYING，3-DONE/USER_PAID/NEWTON，4-DONE/USER_PAID/MCH，5-EXPIRED，6-REVOKED，7-DONE）
     */
    private Integer payScoreStatus;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private Integer delFlag;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 创建部门
     */
    private Long createDept;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public Long getId() {
        return payId;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
