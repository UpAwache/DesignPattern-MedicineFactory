package Management.HumanResources.test;

import MedicineFactory.MedicineFactory;
import Presentation.Protocol.IOManager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 故事线2的测试
 *
 */

public class CompanyManagementTest {
    public static int inputInteger() {
        String inputStr = IOManager.getInstance().input();
        if (inputStr == "") {
            return -1;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(inputStr);
        if (!isNum.matches()) {
            return -1;
        } else {
            return Integer.parseInt(inputStr);
        }
    }
    public static void main(String[] args) throws IOException {
        MedicineFactory factory = MedicineFactory.getInstance();
        factory.companyManage();
    }
}
