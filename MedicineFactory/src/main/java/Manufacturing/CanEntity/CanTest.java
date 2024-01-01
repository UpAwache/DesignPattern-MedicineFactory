package Manufacturing.CanEntity;

import Manufacturing.CanEntity.ConcreteCan.PeachCan;
import Manufacturing.CanEntity.Material.IronMaterial;
import Manufacturing.CanEntity.Size.BigSize;
import Manufacturing.Ingredient.ConcreteIngredient.LotusRoot;
import Manufacturing.Ingredient.Ingredient;
import Manufacturing.Ingredient.Procedure.Cook;
import Manufacturing.ProductLine.Line.PeachLine;
import Presentation.Protocol.IOManager;

/**
 * 测试初始化包装并打印
 * 桥接模式
 */
public class CanTest {
    public static void main(String[] args) {
        IOManager.getInstance().print(
                "# 使用桥接模式",
                "# 使用橋接模式",
                "# Using Bridge Pattern"
        );
        Can can = new PeachCan(BigSize.getInstance(),IronMaterial.getInstance());
        IOManager.getInstance().print("已经使用桥接模式生成测试包装",
                "已經使用橋接模式生成測試包装",
                "Test have been generated using bridge pattern");
    }
}
