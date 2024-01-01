package Manufacturing.ProductLine.Producer;

import Presentation.Protocol.IOManager;

/**
 * 细加工生产方式类.
 *
 */
public class FineProducer implements ProduceManner {
    @Override
    public void produce() {
        IOManager.getInstance().print(
                "细加工:药材处理->药物提取->熬制->反应与处理->过滤与纯化->质量检测->包装与储存",
                "细加工:药材处理->药物提取->熬制->反应与处理->过滤与纯化->质量检测->包装与储存",
                "Fine processing: Herbal Material Processing -> rock sugar boiled on low fire -> added additives -> boiled on high fire -> sealed and stored out of the pot"
        );
    }
}
