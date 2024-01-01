package Main;
import MedicineFactory.MedicineFactory;
import Presentation.Protocol.IOManager;

public class Main {
    public static void main(String[] args) {

        System.out.println("请选择使用系統 / 請選擇使用系统 / Please select the system");
        System.out.println("[1 - Mac]");

        int selectedSystem;
        while ((selectedSystem = MedicineFactory.getInstance().inputInteger()) == -1 || selectedSystem < 1 || selectedSystem > 1) {
            System.out.println("无效输入，请重新输入 / 無效輸入，请重新輸入 / Invalid input, please input again");
        }

        if (IOManager.operatingSystem == IOManager.OperatingSystem.MAC) {
            MedicineFactory factory = MedicineFactory.getInstance();
            factory.run();
        }

    }
}