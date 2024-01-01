package Manufacturing.Machine;

import Manufacturing.CanEntity.Drug;
import Manufacturing.Ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * 罐头机器，处理罐头用。
 */
public abstract class DrugMachine {

    public abstract void preTreat(Drug can);

    public abstract void fill(Drug can, Ingredient... ingredients);

    public abstract void can(Drug can);
}
