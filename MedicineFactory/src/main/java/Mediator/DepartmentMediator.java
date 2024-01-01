package
        Mediator;

import Management.HumanResources.TransportDepartment;
import Manufacturing.CanEntity.Drug;
import Manufacturing.ProductLine.test.ProductDepartment;
import Marketing.OrderCenterEntity.OrderCenter;
import Marketing.OrderEnity.Order;
import Marketing.OrderEnity.OrderCanInformation;
import Marketing.Wrapping.WrappedCan;
import Marketing.Wrapping.WrappingDepartment;
import Presentation.Protocol.IOManager;
import Storage.InventoryDepartment;
import Storage.StockCan;
import Storage.TransportationCan;

import java.util.ArrayList;

public class DepartmentMediator {


    private static final DepartmentMediator departmentMediator = new DepartmentMediator();

    private DepartmentMediator() {

    }

    public static DepartmentMediator getInstance() {
        return departmentMediator;
    }

    //库存管理部门
    InventoryDepartment inventoryDepartment = InventoryDepartment.getInstance();

    //运输管理部门
    TransportDepartment transportDepartment = TransportDepartment.getInstance();
    
    //包装管理部门
    WrappingDepartment wrappingDepartment = WrappingDepartment.getInstance();
    
    //订单中心
    OrderCenter orderCenter = OrderCenter.getInstance();
    
    //生产管理部门
    ProductDepartment productDepartment = ProductDepartment.getInstance();

    
    //通过调用运输管理部门的接口，实现相关操作
    public void transportCans(TransportationCan transportationCan) {
        IOManager.getInstance().print("实现中介者模式: 将运输包裹分配运输部门,通知运输部门运输货物.",
                "實現中介者模式: 將運輸包裹分配運輸部門,通知運輸部門運輸貨物.",
                "Implement the intermediary pattern: Assign the shipping package to the transportation department and notify the transportation department to transport the goods");
        transportDepartment.transportCans(transportationCan);
    }

    
    //通过调用生产管理部门的接口，实现相关操作
    public void productCans(ArrayList<OrderCanInformation> orderCanInformations) {
        IOManager.getInstance().print("实现中介者模式: 将待生产的订单罐头信息分配给生产部门,通知生产部门生产罐头",
                "實現中介者模式: 將待生產的訂單罐頭信息分配給生產部門,通知生產部門生產罐頭",
                "Implement the intermediary pattern: Distribute the order canned food information to be produced to the production department, and notify the production department to produce cans");
        productDepartment.produceCansByOrderList(orderCanInformations);
    }


    /****************** InventoryDepartment Interface **********************/

    
    //生产部门调用实现对生产好的药品的入库.
    public void addCanInventory(ArrayList<StockCan> stockCans) {
        /**
         * 利用中介者来告诉库存管理部门补充货物的数量;
         */
        IOManager.getInstance().print("实现中介者模式: 已经通知库存管理部门对生产好的药品进行存储",
                "實現中介者模式: 已經通知庫存管理部門對生產好药品的進行存儲",
                "Implement the intermediary pattern: The inventory management department has been notified to store the produced cans.");
        inventoryDepartment.addCanInventory(stockCans);
        /**
         * 补充货源之后督促库存管理部门再一次进行订单扫描;
         */
        inventoryDepartment.reviewOrder();
    }

    
    //提供给订单管理部门的接口，调用实现对订单的处理与添加到待办队列;
    public void handleOrder(Order order) {
        /**
         * 利用中介者来告诉库存管理部门这个未处理的订单;
         */
        IOManager.getInstance().print("# 使用中介者模式: 已经通知库存管理部门处理订单,订单编号为 "+order.getOrderId(),
                "# 使用中介者模式: 已經通知庫存管理部門處理訂單,訂單編號為 "+order.getOrderId(),
                "# Using the intermediary pattern: The inventory management department has been notified to process the order, the order number is "+order.getOrderId());
        inventoryDepartment.addOrder(order);

        
        //再添加新的订单后督促库存管理部门再一次进行订单扫描;
        inventoryDepartment.reviewOrder();
    }

    /********************* WrappingDepartment Interface ***********************/

  
    //供生产部门调用的包装药品的接口，输入一个药品可以返回一个包装好的药品;
    public WrappedCan wrapCan(Drug can) {

        WrappedCan wrappedCan = wrappingDepartment.wrapCan(can);
        return wrappedCan;
    }

    /********************* OrderCenter Interface ***********************/
    
    //委托中介者来获取待处理（已下单状态）的订单列表，方便运输中心调用
    public ArrayList<Order> getPendingOrders() {
        return orderCenter.getPendingOrders();
    }

    
    //按照订单id搜索某一订单
    public Order orderExists(Long OrderId) {
        return orderCenter.orderExists(OrderId);
    }


    
    // 完成一个订单的生产
    public boolean completeProductionOfOneOrder(Long orderId) {
        IOManager.getInstance().print(
                "实现中介者模式: 通知订单中心对订单编号为" + orderId + "的订单修改为取货完成状态",
                "實現中介者模式: 通知訂單中心對訂單編號為" + orderId + "的訂單修改為取貨完成狀態",
                "Implement the intermediary pattern: " +
                        "notify the order center to modify the order with the order number "+ orderId +" to pick up status"
        );
        return orderCenter.completeProductionOfOneOrder(orderId);
    }


    
    //开始运输某一订单
    public boolean startToTransportOneOrder(Long orderId) {
        IOManager.getInstance().print(
                "实现中介者模式: 通知订单中心对订单编号为" + orderId + "的订单修改为开始运输状态",
                "實現中介者模式: 通知訂單中心對訂單編號為" + orderId + "的訂單修改為開始運輸狀態",
                "Implement the intermediary pattern: " +
                        "notify the order center to modify the order with the order number "+ orderId +" to start shipping status"
        );
        return orderCenter.startToTransportOneOrder(orderId);
    }

    
    //交付一个订单
    public boolean deliverOneOrder(Long orderId) {
        IOManager.getInstance().print(
                "实现中介者模式: 通知订单中心对订单编号为" + orderId + "的订单修改为交付状态",
                "實現中介者模式: 通知訂單中心對訂單編號為" + orderId + "的訂單修改為交付狀態",
                "Implement the intermediary pattern: " +
                        "notify the order center to modify the order with the order number "+ orderId +" to deliver status"
        );
        return orderCenter.deliverOneOrder(orderId);
    }

}