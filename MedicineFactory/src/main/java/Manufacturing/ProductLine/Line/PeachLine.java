package Manufacturing.ProductLine.Line;

import Manufacturing.CanEntity.Drug;
import Manufacturing.Ingredient.ConcreteIngredient.Peach;
import Manufacturing.Ingredient.Ingredient;
import Manufacturing.Machine.CanTreatmentMachine.GlassCanProducingMachine;
import Manufacturing.Machine.GeneralMachine.PeachFilterMachine;
import Manufacturing.ProductLine.AbstractCanFactory.GlassCanFactory;
import Manufacturing.ProductLine.FruitLine;
import Manufacturing.ProductLine.Pretreatment.PretreatmentApp;
import Manufacturing.ProductLine.Producer.PeachProducer;
import Presentation.Protocol.IOManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 黄桃罐头生产线类
 *
 * @author 孟繁霖
 * @date 2021-10-12 8:32
 */
public class PeachLine implements FruitLine {

    private PretreatmentApp pretreatmentApp = new PretreatmentApp(new PeachFilterMachine());
    private List<Ingredient> ingredients=new ArrayList<>();
    private final GlassCanProducingMachine glassCanProducingMachine = new GlassCanProducingMachine();

    @Override
    public List<Ingredient> preTreat(List<Ingredient> ingredientList) {

        IOManager.getInstance().print(
                "******正在对桃子进行预处理********",
                "******正在對桃子進行預處理********",
                "***Treating peaches*****");
        ingredientList = pretreatmentApp.filterTreat(ingredientList);
        pretreatmentApp.peel(ingredientList);
        pretreatmentApp.disinfect(ingredientList);
        IOManager.getInstance().print(
                "********桃子预处理完成*********",
                "********桃子預處理完成*********",
                "***Peach pretreatment completed***");

        ingredients=ingredientList;
        return ingredientList;
    }

    @Override
    public List<Drug> produce(int count,String produceManner) {
        IOManager.getInstance().print(
                "*******正在对黄桃进行加工*******",
                "*******正在對黃桃進行加工*******",
                "*******Peaches are being processed*******");
        PeachProducer peachProducer = new PeachProducer(produceManner);
        peachProducer.produce();

        List<Drug> product=new ArrayList<>();
        IOManager.getInstance().print(
                "# 使用享元模式生产玻璃罐头",
                "# 使用享元模式生產玻璃罐頭",
                "# Production of glass cans using Flyweight Pattern"
        );
        for(int i=0;i<count;i++){
            Ingredient ingredient = ingredients.get(i);
            Drug can=GlassCanFactory.getInstance().createBigCan("Peach");
            glassCanProducingMachine.preTreat(can);
            glassCanProducingMachine.fill(can,ingredient);
            glassCanProducingMachine.drug(can);
            product.add(can);
        }
        IOManager.getInstance().print(
                "共生产" + count + "个黄桃罐头",
                "共生產" + count + "個黃桃罐頭",
                "Totally produced" + count + "peach can!");
        return product;
    }

    @Override
    public String getConcreteName() {
        return IOManager.getInstance().selectStringForCurrentLanguage(
                "黄桃罐头生产线",
                "黃桃罐頭生產線",
                "Peach Can Product Line"
        );
    }

    public static Drug produceSample() {
        GlassCanProducingMachine glassCanProducingMachine=new GlassCanProducingMachine();
        Drug can = GlassCanFactory.getInstance().createBigCan("Peach");
        glassCanProducingMachine.preTreat(can);
        Ingredient peach =new Peach();
        glassCanProducingMachine.fill(can, peach);
        glassCanProducingMachine.drug(can);
        return can;
    }
}
