package Marketing.OrderEnity;

import Marketing.Promotion.Coupon;

/**
 * 订单金额类，包含原金额、促销金额等信息
 * @Author 王景岳
 */

public class OrderAmount {
    private double originalAmount; //原始金额
    private double promotionAmount; //促销金额
    private Coupon couponType; //促销活动
    private double transportationAmount; //运费

    public OrderAmount(double originalAmount, Coupon coupon){
        this.originalAmount = originalAmount;
        this.couponType = coupon;
        this.promotionAmount = coupon.getPreferentialPrice(originalAmount);
        //运费采用促销金额乘上0.0015来计算
        this.transportationAmount = 0.0015 * promotionAmount;
    }

    public Coupon getCouponType() {
        return couponType;
    }
    public double getOriginalAmount() {
        return originalAmount;
    }
    public double getPromotionAmount() {
        return promotionAmount;
    }
    public double getTransportationAmount() {
        return transportationAmount;
    }
    public void setTransportationAmount(double transportationAmount) {
        this.transportationAmount = transportationAmount;
    }
}
