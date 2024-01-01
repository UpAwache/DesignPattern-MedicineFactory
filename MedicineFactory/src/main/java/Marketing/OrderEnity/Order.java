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
 * 订单类，与迭代器模式和状态模式有关
 * @author 王景岳
 **/
public class Order implements Container {

    private Long orderId; //订单编号
    private OrderState orderState; //订单状态
    private ArrayList<OrderCanInformation> orderCanInformations; //所有药品的信息列表
    private OrderAmount orderAmount; //订单金额
    private Date orderTime; //订单下单时间
    private Date sendTime; //订单发货时间
    private Date completeTime; //订单交付完成时间
    private Date latestTime; //订单最晚交付时间
    private int couponType; //订单使用的优惠券类型
    private String customerAddress; //客户地址

    /**
     * 下面是一组用于获取和设置各类订单信息的方法
     */
    public Date getOrderTime(){
        return this.orderTime;
    }
    public void setSendTime(Date date){
        this.sendTime = date;
    }
    public void setCompleteTime(Date date){
        this.completeTime = date;
    }
    public Date getSendTime(){
        return this.sendTime;
    }
    public Date getCompleteTime(){
        return this.completeTime;
    }
    public int getCouponType(){
        return couponType;
    }
    public OrderState getOrderState(){
        return orderState;
    }

    // 订单类的构造方法
    public Order(ArrayList<OrderCanInformation> orderCanInformations, Coupon coupon, Date latestDeliveryTime, String customerAddress){
        this.orderCanInformations = orderCanInformations;
        this.orderAmount = orderAmount;
        this.latestTime = latestDeliveryTime;
        orderState = new OrderedOrderState();
        orderId = OrderIdGenerator.getGeneratID();
        this.customerAddress = customerAddress;
        this.orderTime = new Date();//当前时间
        this.orderAmount = new OrderAmount(caculateOriginalPrice(), coupon);
        // 若订单原价未达到200元则无法使用优惠券
        if(caculateOriginalPrice()<200)
            this.couponType = -1;
        else if(coupon.getSale() instanceof TwentyPercentOff){
            couponType = 1 ;
        }
        else{
            couponType = 0;
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

    public String getCustomerAddress() {
        return customerAddress;
    }

    public Date getLatestDeliveryTime() {
        return latestTime;
    }

   public OrderAmount getOrderAmount(){
        return orderAmount;
   }

    public Long getOrderId(){
        return orderId;
    }

    public ArrayList<OrderCanInformation> getOrderCanInformations() {
        return orderCanInformations;
    }

    public void changeOrderState(OrderState orderState){
        this.orderState = orderState;
    }

    @Override
    public Iterator getIterator() {
        return new CanTypeIterator();
    }

    /**
     * 订单药品类型迭代器，迭代遍历所有药品类型
     **/
    private class CanTypeIterator implements Iterator {

        int index;

        //判断当前索引是否有下一项
        @Override
        public boolean hasNext() {
            if(index < orderCanInformations.size())
                return true;
            return false;
        }

        //获取迭代器的下一个元素
        @Override
        public Object next() {
            if(this.hasNext()){
                return orderCanInformations.get(index++);
            }
            return null;
        }
    }
}
