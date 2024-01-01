package
        Marketing.Wrapping;

import Manufacturing.CanEntity.Drug;
import Manufacturing.CanEntity.CanInfoController;
import Marketing.Wrapping.Builder.WrappingBuilder;
import Marketing.Wrapping.Builder.WrappingDirector;
import Marketing.Wrapping.Converter.WrappingConverter;
import Marketing.Wrapping.Cover.WrappingCover;
import Presentation.Protocol.IOManager;

import java.lang.reflect.Constructor;
import java.text.DecimalFormat;


/**
 * 负责管理包装业务的包装部门类
 * 实现单例模式.
 *
 * @author 王立友
 * @date 2021/10/24 10:42
 */
public class WrappingDepartment {

    private static final WrappingDepartment instance = new WrappingDepartment();

    private WrappingDepartment() {
    }

    public static WrappingDepartment getInstance() {
        return instance;
    }

    /**
     * 包装部门对外接口,实现对罐头信息转化为封面罐头信息
     *
     * @param can : 等待包装的罐头信息
     * @return : Marketing.Wrapping.Cover.WrappingCover
     * @author "王立友"
     * @date 2021-10-24 10:48
     */
    public WrappingCover printWrappingCover(Drug can) {

        //生成罐头包装实体信息;
        WrappingCanInfo wrappingCanInfo = convertWrappingCanInfo(can);

        //生成罐头工厂包装信息;
        WrappingFactoryInfo wrappingFactoryInfo = WrappingFactoryInfo.getInstance();

        //生成罐头包装纸背景信息;
        String wrappingBackGround = getWrappingBackground(wrappingCanInfo);

        //计算罐头价格信息;
        double canPrice = computeCanPrice(wrappingCanInfo);

        //获得对应罐头的Builder;
        WrappingBuilder wrappingBuilder = getBuilder(wrappingCanInfo.getCanName());
        //生成WrappingDirector;
        WrappingDirector wrappingDirector = new WrappingDirector(wrappingBuilder);
        //开始构建;
        wrappingDirector.construct(wrappingCanInfo, wrappingFactoryInfo, wrappingBackGround, canPrice);
        //得到产品;
        IOManager.getInstance().printBrief(
                "实现建造者设计模式：完成包装封面信息的填充与组装",
                "實現建造者設計模式：完成包裝封面信息的填充與組裝",
                "Realize the builder design mode: complete the filling and assembly of the package cover information"
        );

        return wrappingBuilder.build();
    }

    /**
     * 根据原料的价格计算罐头的价格;
     *
     * @param wrappingCanInfo :  通过解析wrappingCanInfo来获得原料列表;
     * @return : float 罐头价格;
     * @author "王立友"
     * @date 2021-10-25 19:37
     */
    private double computeCanPrice(WrappingCanInfo wrappingCanInfo) {
        //首先设定的是罐头的底价;
        String canName = wrappingCanInfo.getCanName();
        return CanInfoController.getInstance().getCanPriceByName(canName);
    }


    /**
     * 转换器模式的调用;
     *
     * @param can : 待转换的罐头;
     * @return : Marketing.Wrapping.WrappingCanInfo 包装纸信息;
     * @author "王立友"
     * @date 2021-10-24 14:54
     */
    private WrappingCanInfo convertWrappingCanInfo(Drug can) {
        WrappingConverter wrappingConverter = new WrappingConverter();
        //实现转换器过程的输出.
        IOManager.getInstance().printBrief(
                "实现转换器设计模式：将罐头实体信息转化为包装信息",
                "實現轉換器設計模式：將罐頭實體信息轉化為包裝信息",
                "Realize the converter design pattern: convert canned entity information into packaging information"
        );
        return wrappingConverter.getFromCanEntityFunction().apply(can);
    }

    /**
     * 根据wrappingCanInfo中的罐头名称信息生成对应的背景,返回url.
     *
     * @param wrappingCanInfo :  包装信息;
     * @return : java.lang.String
     * @author "王立友"
     * @date 2021-10-24 15:04
     */
    private String getWrappingBackground(WrappingCanInfo wrappingCanInfo) {
        String canName = wrappingCanInfo.getCanName();
        return canName + ".png";
    }

    /**
     * 根据罐头名字获得对应的builder.
     * TODO:待修改为从hashMap中获得罐头信息与Builder类.
     *
     * @param canName :
     * @return : Marketing.Wrapping.Builder.WrappingBuilder
     * @author "王立友"
     * @date 2021-10-24 16:13
     */
    private WrappingBuilder getBuilder(String canName) {
        String canType = CanInfoController.getInstance().getClassByName(canName).getTypeName();
        String[] canTypes = canType.split("\\.");
        canType = canTypes[canTypes.length - 1];
        String basePath = "Marketing.Wrapping.Builder.";
        String builderType = basePath + canType + "WrappingBuilder";
        try {
            Class<?> builder = Class.forName(builderType);
            Constructor<?> constructor = builder.getConstructor();
            return (WrappingBuilder) constructor.newInstance();
        } catch (Exception e) {
            IOManager.getInstance().print("没有相应罐头名称的包装建造器",
                    "沒有相應罐頭名稱的包裝建造器",
                    "There is no packaging builder with the corresponding can name");
            return null;
        }
    }

    /**
     * 对外接口的呈现，包装罐头;
     *
     * @param can :  一个待包装的实体罐头;
     * @return : Marketing.Wrapping.WrappedCan
     * @author "王立友"
     * @date 2021-10-25 1:13
     */
    public WrappedCan wrapCan(Drug can) {

        return new WrappedCan(can, printWrappingCover(can));
    }

    /**
     * 对外接口的呈现,用来输出包装好的罐头的信息;
     *
     * @param wrappedCan :
     * @author "王立友"
     * @date 2021-10-25 1:14
     */
    public void outputWrappedCan(WrappedCan wrappedCan) {

        WrappingCover wrappingCover = wrappedCan.getWrappingCover();

        IOManager.getInstance().print("*************************************", "*************************************", "*************************************");
        IOManager.getInstance().print("输出包装封面的罐头实体信息\n",
                "輸出包裝封面的罐頭實體信息\n", "Output canned entity information on wrapping cover\n");
        IOManager.getInstance().print("罐头名称:" + wrappingCover.getWrappingCanInfo().getCanName(),
                "罐頭名稱:" + wrappingCover.getWrappingCanInfo().getCanName(),
                "canName:" + wrappingCover.getWrappingCanInfo().getCanName());
        IOManager.getInstance().print("罐头尺寸:" + wrappingCover.getWrappingCanInfo().getSize(),
                "罐頭尺寸:" + wrappingCover.getWrappingCanInfo().getSize(),
                "canSize:" + wrappingCover.getWrappingCanInfo().getSize());
        IOManager.getInstance().print("罐头原料列表:" + wrappingCover.getWrappingCanInfo().getIngredientsContent(),
                "罐頭原料列表:" + wrappingCover.getWrappingCanInfo().getIngredientsContent(),
                "canIngredients:" + wrappingCover.getWrappingCanInfo().getIngredientsContent());
        IOManager.getInstance().print("罐头生产日期:" + wrappingCover.getWrappingCanInfo().getManufactureTime(),
                "罐頭生產日期:" + wrappingCover.getWrappingCanInfo().getManufactureTime(),
                "canManufactureTime:" + wrappingCover.getWrappingCanInfo().getManufactureTime());
        IOManager.getInstance().print("罐头保质期:" + wrappingCover.getWrappingCanInfo().getShelfTime(),
                "罐頭保質期:" + wrappingCover.getWrappingCanInfo().getShelfTime(),
                "canShelfTime:" + wrappingCover.getWrappingCanInfo().getShelfTime());
        IOManager.getInstance().print("*************************************", "*************************************", "*************************************");
        IOManager.getInstance().print("输出包装封面的罐头工厂信息\n",
                "輸出包裝封面的罐頭工廠信息\n", "Output cannery information on wrapping cover\n");
        IOManager.getInstance().print("工厂名称:" + wrappingCover.getWrappingFactoryInfo().getFactoryName(),
                "工廠名稱:" + wrappingCover.getWrappingFactoryInfo().getFactoryName(),
                "factoryName:" + wrappingCover.getWrappingFactoryInfo().getFactoryName());
        IOManager.getInstance().print("工厂地址:" + wrappingCover.getWrappingFactoryInfo().getFactoryAddress(),
                "工廠地址:" + wrappingCover.getWrappingFactoryInfo().getFactoryAddress(),
                "factoryAddress:" + wrappingCover.getWrappingFactoryInfo().getFactoryAddress());
        IOManager.getInstance().print("工厂联系方式:" + wrappingCover.getWrappingFactoryInfo().getFactoryPhone(),
                "工廠聯繫方式:" + wrappingCover.getWrappingFactoryInfo().getFactoryPhone(),
                "factoryPhone:" + wrappingCover.getWrappingFactoryInfo().getFactoryPhone());
        IOManager.getInstance().print("工厂网址:" + wrappingCover.getWrappingFactoryInfo().getFactoryWebsite(),
                "工廠網址:" + wrappingCover.getWrappingFactoryInfo().getFactoryWebsite(),
                "factoryWebsite:" + wrappingCover.getWrappingFactoryInfo().getFactoryWebsite());
        IOManager.getInstance().print("食用提示:" + wrappingCover.getWrappingFactoryInfo().getFactoryTip(),
                "食用提示:" + wrappingCover.getWrappingFactoryInfo().getFactoryTip(),
                "factoryTips:" + wrappingCover.getWrappingFactoryInfo().getFactoryTip());
        IOManager.getInstance().print("*************************************", "*************************************", "*************************************");
        IOManager.getInstance().print("输出罐头价格信息\n",
                "輸出罐頭價格信息\n", "Output canned price information\n");
        IOManager.getInstance().print("罐头价格:" + new DecimalFormat("0.00").format(wrappingCover.getCanPrice()),
                "罐頭價格:" + new DecimalFormat("0.00").format(wrappingCover.getCanPrice()),
                "canPrice:" + new DecimalFormat("0.00").format(wrappingCover.getCanPrice()));
        IOManager.getInstance().print("*************************************", "*************************************", "*************************************");
    }
}