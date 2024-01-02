package Marketing.Promotion.Sale;

/**
 * 策略模式，销售策略的接口
 * @author 王景岳
 */
public interface Sale {
    // 通过原价计算促销价格的方法
    Double discount(Double originalPrice);
}
