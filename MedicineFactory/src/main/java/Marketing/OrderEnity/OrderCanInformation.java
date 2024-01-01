package Marketing.OrderEnity;

/**
 * @Author 王立友
 * 该类为订单中关于罐头的部分信息内容设计
 * 订单中的罐头信息不能通过CanEntity来直接表示
 * 用户在选择罐头的种类后，应当构建一份OrderCanInformation
 * 包含对应罐头的id,name,count
 */

public class OrderCanInformation {

    //建立orderCanInformation与can之间联系的罐头种类

    //订单中描述罐头的名称信息
    private String canName;

    //订单中该罐头的数量
    private int count;


    //订单中该罐头的单价
    private double price;

    public OrderCanInformation( String canName, int count, double price){
        this.canName = canName;
        this.count = count;
        this.price = price;
    }

    public String getCanName(){
        return canName;
    }
    public int getCount() {
        return count;
    }

    public void setCanName(String canName) {
        this.canName = canName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderCanInformation{" +
                "canName='" + canName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
