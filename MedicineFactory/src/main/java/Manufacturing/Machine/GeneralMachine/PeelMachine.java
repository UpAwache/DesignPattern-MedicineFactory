package Manufacturing.Machine.GeneralMachine;

import Manufacturing.Ingredient.Ingredient;
import Manufacturing.Ingredient.Procedure.Pretreat.Peel;
import Manufacturing.Machine.IngredientMachine;

/**
 * 削皮机器
 *
 * @author 卓正一
 * @since 2021/10/26 12:19 AM
 */
public class PeelMachine implements IngredientMachine {
    @Override
    public Ingredient treat(Ingredient ingredient) {
        return new Peel(ingredient);
    }
}
