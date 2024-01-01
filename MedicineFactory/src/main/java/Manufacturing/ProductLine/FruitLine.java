package Manufacturing.ProductLine;

import Manufacturing.CanEntity.Drug;
import Manufacturing.Ingredient.Ingredient;
import Manufacturing.ProductLine.Pretreatment.PretreatmentApp;

import java.util.List;

/**
 * 水果罐头生产线接口,预处理原料、产生罐头产品.
 *
 * @author 孟繁霖
 * @date 2021-10-11 23:47
 */
public interface FruitLine extends ProductLine{

    /**
     * 预处理
     *
     * @param baseIngredientList :  原料列表
     * @return : java.util.List<Manufacturing.ProductLine.Fruit.RawMaterial>
     * @author 孟繁霖
     * @date 2021-10-11 23:47
     */
    List<Ingredient> preTreat(List<Ingredient> baseIngredientList);



}
