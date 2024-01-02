package Marketing.Promotion;

import Marketing.Promotion.Sale.Sale;

/**
 * 优惠类，与策略模式有关
 * @author 王景岳
 */

public class Coupon {
    private Sale strategy;

    public Coupon(Sale sale) {
        strategy = sale;
    }

    // 通过原价计算优惠价格
    public Double getPreferentialPrice(Double originalPrice) {
        return strategy.discount(originalPrice);
    }

    // 修改促销策略
    public void changeStrategy(Sale sale) {
        strategy = sale;
    }

    public Sale getSale(){return strategy;}
}
