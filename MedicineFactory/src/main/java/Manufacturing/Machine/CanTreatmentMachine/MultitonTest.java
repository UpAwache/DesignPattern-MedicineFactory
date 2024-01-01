package Manufacturing.Machine.CanTreatmentMachine;

import Manufacturing.Machine.DrugMachine;
import Presentation.Protocol.IOManager;

/**
 * 多例模式测试类
 *
 * @author 郑皓予
 * @since 2023/12/31 3:14 PM
 */
public class MultitonTest {

    /**
     * 多例测试
     *
     * @author 郑皓予
     * @since 2023-12-31 8:49 PM
     */
    public static void main(String[] args) {
        IOManager.getInstance().printBrief(
                "---多例模式测试---",
                "---多例模式測試---",
                "---Multiton Pattern Test---"
        );
        IOManager.getInstance().printBrief(
                "尝试获取两次药品处理机器。",
                "嘗試獲取兩次葯品處理機器。",
                "Trying to fetch drug processing machine twice."
        );
        DrugMachine machine = DrugProducingMachine.getInstance();
        DrugMachine machine1 = DrugProducingMachine.getInstance();
        IOManager.getInstance().printBrief(
                "判断两个机器是否是同一个：",
                "判斷兩個機器是否是同一個：",
                "Determine whether the two machines are the same: "
        );
        IOManager.getInstance().printLanguageIrrelevantContent(Boolean.toString(machine == machine1));
        IOManager.getInstance().printBrief(
                "---多例模式测试结束---",
                "---多例模式測試結束---",
                "---Multiton Pattern Test End---"
        );
    }
}
