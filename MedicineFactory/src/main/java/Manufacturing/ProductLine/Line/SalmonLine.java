package Manufacturing.ProductLine.Line;

import Manufacturing.CanEntity.Drug;
import Manufacturing.Ingredient.ConcreteIngredient.Salmon;
import Manufacturing.Ingredient.Ingredient;
import Manufacturing.Machine.DrugMachine;
import Manufacturing.Machine.CanTreatmentMachine.DrugProducingMachine;
import Manufacturing.Machine.GeneralMachine.SalmonFilterMachine;
import Manufacturing.ProductLine.AbstractCanFactory.IronCanFactory;
import Manufacturing.ProductLine.FreshLine;
import Manufacturing.ProductLine.Pretreatment.PretreatmentApp;
import Presentation.Protocol.IOManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 三文鱼罐头生产线类.
 *
 * @author 孟繁霖
 * @date 2021-10-12 8:30
 */
public class SalmonLine implements FreshLine {

    private PretreatmentApp pretreatmentApp = new PretreatmentApp(new SalmonFilterMachine());
    private  List<Ingredient> ingredients=new ArrayList<>();
    private final DrugMachine ironCanMachine = DrugProducingMachine.getInstance();

    @Override
    public List<Ingredient> preTreat(List<Ingredient> ingredientList) {

        IOManager.getInstance().print(
                "*********正在对三文鱼进行预处理*********",
                "*********正在對三文魚進行預處理*********",
                "*********Salmon is being pretreated*********");
        ingredientList = pretreatmentApp.filterTreat(ingredientList);
        pretreatmentApp.disinfect(ingredientList);
        pretreatmentApp.clean(ingredientList);
        IOManager.getInstance().print(
                "*************三文鱼预处理完成***********",
                "*************三文魚預處理完成***********",
                "*************Salmon pretreatment completed***********");
        ingredients=ingredientList;
        return ingredientList;
    }


    public List<Drug> produce(int count,String produceManner) {
        IOManager.getInstance().print(
                "**********正在对三文鱼进行加工**********",
                "**********正在對三文魚進行加工**********",
                "**********Salmon is being processed**********");

        List<Drug> product=new ArrayList<>();
        IOManager.getInstance().print(
                "# 使用享元模式：生产铁制罐头",
                "# 使用享元模式：生產鐵製罐頭",
                "# Using Flyweight Pattern: Production of iron cans"
        );
        for(int i=0;i<count;i++){
            Ingredient ingredient = ingredients.get(i);
            Drug can= IronCanFactory.getInstance().createBigCan("Salmon");
            ironCanMachine.preTreat(can);
            ironCanMachine.fill(can,ingredient);
            ironCanMachine.can(can);
            product.add(can);
        }

        IOManager.getInstance().print(
                "共生产" + count + "个三文鱼罐头",
                "共生產" + count + "個三文魚罐頭",
                "Totally produced" + count + "salmon can!");
        return product;
    }

    @Override
    public String getConcreteName() {
        return IOManager.getInstance().selectStringForCurrentLanguage(
                "三文鱼罐头生产线",
                "三文魚罐頭生產線",
                "Salmon Can Product Line"
        );
    }


    public static Drug produceSample() {
        DrugMachine ironCanProducingMachine = DrugProducingMachine.getInstance();
        Drug can = IronCanFactory.getInstance().createBigCan("Salmon");
        ironCanProducingMachine.preTreat(can);
        Ingredient salmon =new Salmon();
        ironCanProducingMachine.fill(can, salmon);
        ironCanProducingMachine.can(can);
        return can;
    }
}
