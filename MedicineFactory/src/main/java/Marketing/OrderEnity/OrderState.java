package Marketing.OrderEnity;/**

/**
 * 订单状态抽象类
 * @author 王景岳
 */
public abstract class OrderState {

    //判断是否被生产
    public abstract boolean isProduced();

    //判断是否被运输
    public abstract boolean isTransporting();

    //判断订单是否已支付
    public abstract boolean isDelivered();

   //订单生产完后调用
    public abstract void handleProduction(Order order);

    //订单开始运输时调用
    public abstract void handleTransportation(Order order);

    //订单运输完成时调用
    public abstract void handleDelivery(Order order);

    //修改某个订单的状态
    public void changeOrderState(Order order,OrderState orderState){
        order.changeOrderState(orderState);
    };

    //获取订单状态的中文名称
    public abstract String getCNStateName();

    //获取订单状态的繁体名称
    public abstract String getTWStateName();

    //获取订单状态的英文名称
    public abstract String getENStateName();

}