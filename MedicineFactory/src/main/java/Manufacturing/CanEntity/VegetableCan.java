package Manufacturing.CanEntity;

import Manufacturing.CanEntity.Material.Material;
import Manufacturing.CanEntity.Size.Size;

/**
 * 蔬菜罐头
 * 桥接模式
 * @author 汪明杰
 */
public class VegetableCan extends Drug{
    public VegetableCan(Size s, Material c) {
        super.setSize(s);
        super.setMaterial(c);
        setName("蔬菜罐头", "蔬菜罐頭", "Vegetable Can");
    }
}
