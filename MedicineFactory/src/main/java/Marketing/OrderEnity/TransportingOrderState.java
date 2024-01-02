package Marketing.OrderEnity;

import Presentation.Protocol.IOManager;

/**
 * 订单状态类：正在运输
 * @author 王景岳
 */
public class TransportingOrderState extends OrderState{
    @Override
    public boolean isProduced() {
        return true;
    }

    @Override
    public boolean isTransporting() {
        return true;
    }

    @Override
    public boolean isDelivered() {
        return false;
    }

    @Override
    public void handleProduction(Order order) {
        IOManager.getInstance().errorMassage(
                "订单号为"+order.getOrderId()+"的订单已经开始运输,无法再次生产！",
                "訂單號為"+order.getOrderId()+"的訂單已經開始運輸,無法再次生產！",
                "The order with order ID"+order.getOrderId()+"has began to be transported,can not be produced again!"
        );
    }

    @Override
    public void handleTransportation(Order order) {
        IOManager.getInstance().errorMassage(
                "订单号为"+order.getOrderId()+"的订单已经开始运输,无法再次运输！",
                "訂單號為"+order.getOrderId()+"的訂單已經開始運輸,無法再次運輸！",
                "The order with order ID"+order.getOrderId()+"has began to be transported,can not be transported again!"
        );
    }

    @Override
    public void handleDelivery(Order order) {
        IOManager.getInstance().errorMassage(
                "订单号为"+order.getOrderId()+"的订单完成交付！",
                "訂單號為"+order.getOrderId()+"的訂單完成交付！",
                "The order with order ID"+order.getOrderId()+"has completed the delivery!"
        );
        changeOrderState(order,new DeliveredOrderState());//修改相应的订单状态
    }

    //获取订单状态的中文名称
    @Override
    public String getCNStateName(){
        return "运输中";
    }

    //获取订单状态的繁体名称
    @Override
    public String getTWStateName(){
        return "運輸中";
    }

    //获取订单状态的英文名称
    @Override
    public String getENStateName(){
        return "Transporting";
    }
}