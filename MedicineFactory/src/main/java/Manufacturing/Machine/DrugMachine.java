package Manufacturing.Machine;

import Manufacturing.CanEntity.Can;
import Manufacturing.Ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * 罐头机器，处理罐头用。
 */
public abstract class DrugMachine {

    public abstract void preTreat(Can can);

    public abstract void fill(Can can, Ingredient... ingredients);

    public abstract void can(Can can);
}
