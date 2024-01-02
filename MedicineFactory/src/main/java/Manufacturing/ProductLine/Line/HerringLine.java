package Manufacturing.ProductLine.Line;

import Manufacturing.CanEntity.Drug;
import Manufacturing.Ingredient.ConcreteIngredient.Herring;
import Manufacturing.Ingredient.Ingredient;
import Manufacturing.Machine.DrugMachine;
import Manufacturing.Machine.CanTreatmentMachine.DrugProducingMachine;
import Manufacturing.Machine.GeneralMachine.HerringFilterMachine;
import Manufacturing.ProductLine.AbstractCanFactory.IronCanFactory;
import Manufacturing.ProductLine.FreshLine;
import Manufacturing.ProductLine.Pretreatment.PretreatmentApp;
import Presentation.Protocol.IOManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 鲱鱼罐头生产线类
 *
 * @author 孟繁霖
 * @date 2021-10-12 8:32
 */
public class HerringLine implements FreshLine {

    private PretreatmentApp pretreatmentApp = new PretreatmentApp(new HerringFilterMachine());

    private List<Ingredient> ingredients=new ArrayList<>();
    private final DrugMachine ironCanProducingMachine = DrugProducingMachine.getInstance();
    @Override
    public List<Ingredient> preTreat(List<Ingredient> ingredientList) {

        IOManager.getInstance().print(
                "******正在对鲱鱼进行预处理********",
                "******正在對鯡魚進行預處理********",
                "******Pretreating herring fish********");
        ingredientList = pretreatmentApp.filterTreat(ingredientList);
        pretreatmentApp.disinfect(ingredientList);
        pretreatmentApp.clean(ingredientList);
        IOManager.getInstance().print(
                "*********鲱鱼预处理完成*********",
                "*********鯡魚預處理完成*********",
                "*********Herring fish pretreatment completed*********");
        ingredients=ingredientList;
        return ingredientList;
    }

    @Override
    public List<Drug> produce(int count,String produceManner) {
        IOManager.getInstance().print(
                "*******正在对鲱鱼进行加工*******",
                "*******正在對鯡魚進行加工*******",
                "*******Herring fish is being processed*******");

        List<Drug> product=new ArrayList<>();

        IOManager.getInstance().print(
                "# 使用享元模式：生产铁制罐头",
                "# 使用享元模式：生產鐵製罐頭",
                "# Using Flyweight Pattern: Production of iron cans"
        );
        for(int i=0;i<count;i++){
            Ingredient ingredient = ingredients.get(i);
            Drug can= IronCanFactory.getInstance().createBigCan("Herring");
            ironCanProducingMachine.preTreat(can);
            ironCanProducingMachine.fill(can,ingredient);
            ironCanProducingMachine.drug(can);
            product.add(can);
        }
        IOManager.getInstance().print(
                "共生产" + count + "个鲱鱼罐头",
                "共生產" + count + "個鯡魚罐頭",
                "Totally produced" + count + "herring can!");
        return product;
    }

    @Override
    public String getConcreteName() {
        return IOManager.getInstance().selectStringForCurrentLanguage(
                "鲱鱼罐头生产线",
                "鯡魚罐頭生產線",
                "Herring Can Product Line"
        );
    }

    public static Drug produceSample() {
        DrugMachine ironCanProducingMachine = DrugProducingMachine.getInstance();
        Drug can = IronCanFactory.getInstance().createBigCan("Herring");
        ironCanProducingMachine.preTreat(can);
        Ingredient herring =new Herring();
        ironCanProducingMachine.fill(can, herring);
        ironCanProducingMachine.drug(can);
        return can;
    }
}
