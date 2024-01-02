package Marketing.Wrapping.Converter;


import Manufacturing.CanEntity.Drug;
import Marketing.Wrapping.WrappingCanInfo;

import java.util.function.Function;

/**
 * 转换器模式
 *
 * @Author 王立友
 * 读取Can内容转换到WrappingCanInfo.
 */

public class WrappingConverter {

    private Function<Drug, WrappingCanInfo> fromCanEntityFunction;

    public WrappingConverter() {
        fromCanEntityFunction = new FromCanEntityFunction();
    }

    public Function<Drug, WrappingCanInfo> getFromCanEntityFunction() {
        return fromCanEntityFunction;
    }


    static class FromCanEntityFunction implements Function<Drug, WrappingCanInfo> {
        /**
         * 转换器模式,将罐头实例转化为包装罐头信息.
         *
         * @param can :  罐头
         * @return : Marketing.Wrapping.WrappingCanInfo  包装罐头信息
         * @author "王立友"
         * @date 2021-10-24 11:18
         */
        @Override
        public WrappingCanInfo apply(Drug can) {

            WrappingCanInfo wrappingCanInfo = new WrappingCanInfo();
            wrappingCanInfo.setCanName(can.getCanName());
            wrappingCanInfo.setIngredients(can.getIngredients());
            wrappingCanInfo.setSize(can.getSize().getSize());
            wrappingCanInfo.setManufactureTime(can.getManufactureTime());
            wrappingCanInfo.setShelfTime(can.getShelfTime());

            return wrappingCanInfo;
        }
    }
}
