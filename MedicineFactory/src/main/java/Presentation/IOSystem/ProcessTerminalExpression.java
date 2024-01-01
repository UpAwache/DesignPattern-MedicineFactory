package Presentation.IOSystem;

import MedicineFactory.MedicineFactory;
import Presentation.Protocol.IOManager;
//过程终结表达式
public class ProcessTerminalExpression implements AbstractExpression
{
    //运行相应的流程
    @Override
    public void interpret(Instruction instruction) {
        switch (instruction.getString()) {
            case "order_manage":
                MedicineFactory.getInstance().orderManage();
                break;
            case "company_manage":
                MedicineFactory.getInstance().companyManage();
                break;
            default:
                IOManager.getInstance().print("输入 'help process' 以获取帮助信息\n",
                        "輸入 'help process' 獲取幫助\n",
                        "input 'help process' gain help\n");
        }
    }
}
