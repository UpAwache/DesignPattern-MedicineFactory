package Marketing.OrderEnity;

import Manufacturing.CanEntity.Drug;
import Marketing.Container;
import Marketing.Iterator;
import Marketing.OrderCenterEntity.OrderCenter;
import Marketing.Promotion.Coupon;
import Marketing.Promotion.Sale.TwentyPercentOff;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * 订单类,实现了迭代器模式，状态模式
 * @author 梁乔 2021/10/10
 **/
public class Order implements Container {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单的状态
     */
    private OrderState orderState;

    /**
    * 获取订单状态
     * @return : Marketing.OrderEnity.OrderState
    * @author 梁乔
    * @date 11:10 2021-10-15
    */
    public OrderState getOrderState(){
        return orderState;
    }

    //罐头信息列表
    private ArrayList<OrderCanInformation> orderCanInformations;

    //订单金额
    private OrderAmount orderAmount;

    //订单下单时间
    private Date placingTime;

    //订单发货时间
    private Date sendingTime;

    //订单交付完成时间
    private Date completionTime;

    //订单最晚交付时间
    private Date latestDeliveryTime;

    //订单的礼券类型
    private int couponFlag;

    /**
     * 这边以一个地址暂且代替用户信息，订单中需要保存必要的订单信息;
     * 订单运送的地址
     */
    private String customerAddress;

    public Date getPlacingTime(){
        return this.placingTime;
    }

    public void setSendingTime(Date date){
        this.sendingTime = date;
    }

    public void setCompletionTime(Date date){
        this.completionTime = date;
    }

    public Date getSendingTime(){
        return this.sendingTime;
    }

    public Date getCompletionTime(){
        return this.completionTime;
    }

    public int getCouponFlag(){
        return couponFlag;
    }
    /**
    * 订单构造函数，传入订单罐头信息和订单价格
     * @param orderCanInformations :订单罐头信息
     * @return : null
    * @author 王立友
    * @date 11:24 2021-10-15
    */
    public Order(ArrayList<OrderCanInformation> orderCanInformations, Coupon coupon, Date latestDeliveryTime, String customerAddress){
        this.orderCanInformations = orderCanInformations;
        this.orderAmount = orderAmount;
        this.latestDeliveryTime = latestDeliveryTime;
        orderState = new OrderedOrderState();
        orderId = OrderIdGenerator.getGeneratID();
        this.customerAddress = customerAddress;
        this.placingTime = new Date();//当前时间
        this.orderAmount = new OrderAmount(caculateOriginalPrice(), coupon);
        //生成与订单生成时间相关的随机且唯一ID标识
        if(caculateOriginalPrice()<200)
            this.couponFlag = -1;
        else if(coupon.getSale() instanceof TwentyPercentOff){
            couponFlag =1 ;//表示8折扣优惠
        }
        else{
            couponFlag = 0;//表示满200减20
        }

    }

    public Double caculateOriginalPrice(){
        Double price = 0.;
        //计算该订单的OrderAmount对象
        for(Iterator it = getIterator(); it.hasNext();){
            OrderCanInformation orderCanInformation = (OrderCanInformation) it.next();
            price += (orderCanInformation.getCount()*orderCanInformation.getPrice());
        }
        return price;
    }
    /**
     * 获得运货地址;
     * @return : java.lang.String
     * @author "王立友"
     * @date 2021-10-17 16:55
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * 获得最晚运送时间;
     * @return : java.util.Date
     * @author "王立友"
     * @date 2021-10-17 18:09
     */
    public Date getLatestDeliveryTime() {
        return latestDeliveryTime;
    }

    /**
    *   获取当前订单的金额类
     * @return : Marketing.OrderEnity.OrderAmount
    * @author 梁乔
    * @date 22:02 2021-10-15
    */
   public OrderAmount getOrderAmount(){
        return orderAmount;
   }

    /**
    * 设置发货时间
     * @param sendingTime : 发货时间
     * @return : void
    * @author 王立友
    * @date 11:25 2021-10-15
    */

    /**
    * 设置订单的完成时间
     * @param completionTime :订单完成时间
     * @return : void
    * @author
    * @date 11:26 2021-10-15
    */

    /**
     * 获取当前订单ID
     * @return : java.lang.Long
     * @author 梁乔
     * @date 11:09 2021-10-15
     */
    public Long getOrderId(){
        return orderId;
    }

    /**
     * 订单信息的Ggetter
     * @return : java.util.ArrayList<Marketing.OrderEnity.OrderCanInformation>
     * @author "王立友"
     * @date 2021-10-17 15:20
     */
    public ArrayList<OrderCanInformation> getOrderCanInformations() {
        return orderCanInformations;
    }

    /**
     * 修改当前的订单状态，由订单状态类来调用
     * @param orderState : 目标订单状态
     * @return : void
     * @author 梁乔
     * @date 11:09 2021-10-15
     */
    public void changeOrderState(OrderState orderState){
        this.orderState = orderState;
    }

    @Override
    public Iterator getIterator() {
        return new CanTypeIterator();
    }

    /**
     * Nested class 订单罐头类型迭代器，用于订单内各罐头类型的遍历和查找
     * @author 梁乔
     * @date 2021-10-15 15:17
     */
    private class CanTypeIterator implements Iterator {

        int index;

        /**
         * 当前index是否有下一个元素
         * @return : boolean
         * @author 梁乔
         * @date 15:18 2021-10-15
         */
        @Override
        public boolean hasNext() {
            if(index < orderCanInformations.size())
                return true;
            return false;
        }

        /**
         * 获取迭代器的下一个元素
         * @return : java.lang.Object
         * @author 梁乔
         * @date 15:26 2021-10-15
         */
        @Override
        public Object next() {
            if(this.hasNext()){
                return orderCanInformations.get(index++);
            }
            return null;
        }
    }
}
