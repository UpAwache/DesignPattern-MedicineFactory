package Marketing.OrderEnity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* 订单编号生成器类
* @author 王景岳
*/
public class OrderIdGenerator {
    //获取当前时间的字符串
    public static String getDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

   //获取一个随机数
    public static String getRandomNum(int num){
        String numStr = "";
        for(int i = 0; i < num; i++){
            numStr += (int)(10*(Math.random()));
        }
        return numStr;
    }

    //生成订单的id编号
    public static Long getGeneratID(){
        String sformat = "MMddhhss";
        int num = 3;
        String idStr = getDate(sformat) + getRandomNum(num);
        return Long.valueOf(idStr);
    }
}