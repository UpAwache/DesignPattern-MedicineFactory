package Manufacturing.CanEntity.Size;

// TODO 使用享元模式来产生大小信息。
/**
 * 桥接模式（Bridge）
 * 实现不同种类的罐头
 * 罐头的大小属性
 * @author 汪明杰
 */
public interface Size{

    public int getSize();

    public boolean isLarge();

}
