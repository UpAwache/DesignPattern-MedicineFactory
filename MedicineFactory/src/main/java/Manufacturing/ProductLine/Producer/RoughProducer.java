package Manufacturing.ProductLine.Producer;

import Presentation.Protocol.IOManager;

/**
 * 粗加工生产类
 *
 */
public class RoughProducer implements ProduceManner {
    @Override
    public void produce() {

        IOManager.getInstance().print(
                "粗加工:药材处理->药物提取->熬制->反应与处理->包装与储存",
                "粗加工:药材处理->药物提取->熬制->反应与处理->包装与储存",
                "Rough processing: Herbal Material Processing -> pour in the flavoring -> boil -> seal out the pot");
    }
}
