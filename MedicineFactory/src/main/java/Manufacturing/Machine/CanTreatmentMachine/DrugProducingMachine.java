package Manufacturing.Machine.CanTreatmentMachine;

import Manufacturing.CanEntity.Drug;
import Manufacturing.Ingredient.Ingredient;
import Manufacturing.Machine.DrugMachine;
import Presentation.Protocol.IOManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理罐头
 */
public class DrugProducingMachine extends DrugMachine {

    @Override
    public void preTreat(Drug can) {
        can.getDisinfection();
    }

    @Override
    public void fill(Drug can, Ingredient... ingredients) {
        for (Ingredient i :
                ingredients) {
            can.addIngredient(i);
        }
    }

    @Override
    public void can(Drug can) {
        can.getCanned();
    }

    private DrugProducingMachine() {
        super();
    }

    static protected List<DrugProducingMachine> drugMachines = new ArrayList<>();

    static protected int current = 0;

    static protected int amount;

    static private int getNextPosition() {
        current++;
        while (current >= drugMachines.size()) {
            current %= drugMachines.size();
        }
        return current;
    }

    static public DrugProducingMachine getInstance() {
        IOManager.getInstance().printPattern(
                "# 使用多例模式：返回第" + (current+1) + "个药品处理机器。",
                "# 使用多例模式：返回第" + (current+1) + "個葯品處理機器。",
                "# Using Multiton Pattern: return No." + (current+1) + " drug Producing Machine."
                );
        return drugMachines.get(getNextPosition());
    }

    static {
        amount = 5;
        for (int i = 0; i < amount; i++) {
            drugMachines.add(new DrugProducingMachine());
        }
    }
}
