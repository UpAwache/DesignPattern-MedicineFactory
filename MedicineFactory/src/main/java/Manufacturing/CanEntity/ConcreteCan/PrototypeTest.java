package Manufacturing.CanEntity.ConcreteCan;

import Manufacturing.CanEntity.Drug;
import Manufacturing.ProductLine.AbstractCanFactory.GlassCanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式测试类
 *
 */
public class PrototypeTest {
    public static void main(String[] args) {
        List<Drug> cans = new ArrayList<>();

        for(int i=0;i<10;++i){
            cans.add(GlassCanFactory.getInstance().createBigCan("Peach"));
        }

    }
}
