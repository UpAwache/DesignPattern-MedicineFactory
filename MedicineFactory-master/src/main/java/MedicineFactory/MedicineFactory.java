package MedicineFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//所有类的驱动类
public class MedicineFactory {
    private static MedicineFactory medicineFactory;

    static {
        medicineFactory = new MedicineFactory();
    }

    public static MedicineFactory getInstance() {
        return medicineFactory;
    }

    /**
     * 单例构造器
     *
     */
    private MedicineFactory() {
    }

    public void run(){

    }
}
