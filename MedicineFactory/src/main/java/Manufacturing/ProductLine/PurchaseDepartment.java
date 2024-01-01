package Manufacturing.ProductLine;

import Management.HumanResources.BaseDepartment;
import Management.HumanResources.DepartmentType;
import Management.HumanResources.Manager.PurchaseManager;
import Presentation.Protocol.IOManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;


/**
 * 采购部门，采用单例模式，继承了BaseDepartment
 *
 * @author 香宁雨
 * @since 2021/10/11 23:45
 */
public class PurchaseDepartment extends BaseDepartment {


    private JSONArray rawMaterial;
    private PurchaseManager purchaseManager = new PurchaseManager();
    static private PurchaseDepartment instance;

    /**
     * PurchaseDepartment的构造函数
     *
     * @return : null
     * @author 香宁雨
     * @since 15:16 2021-10-24
     */
    private PurchaseDepartment() {

        this.type = DepartmentType.Purchase;

        rawMaterial = generateMaterialList();

        IOManager.getInstance().print(
                "新建了一个采购部门",
                "新建了一個采購部門",
                "A new purchasing department has been created"
        );
    }

    /**
     * 用于获取当前type在rawMaterial中的index
     */
    private Integer indexOfRawMaterial(String type) {
        for (Integer i = 0; i < rawMaterial.length(); i++) {
            if (type.equals(rawMaterial.getJSONObject(i).getString("ingredientType"))) {
                return i;
            }
        }
        return null;
    }

    /**
     * 初始化一个原材料的list
     *
     * @return : org.json.JSONArray
     * @author 香宁雨
     * @since 15:17 2021-10-24
     */
    public JSONArray generateMaterialList() {
        JSONArray material = new JSONArray();
        JSONObject candiedApple = new JSONObject();
        candiedApple.put("ingredientType", "candiedapple");
        candiedApple.put("count", 30);
        JSONObject clove = new JSONObject();
        clove.put("ingredientType", "herring");
        clove.put("count", 0);
        JSONObject peach = new JSONObject();
        peach.put("ingredientType", "peach");
        peach.put("count", 0);
        JSONObject salmon = new JSONObject();
        salmon.put("ingredientType", "salmon");
        salmon.put("count", 0);

        material.put(candiedApple);
        material.put(clove);
        material.put(peach);
        material.put(salmon);

        return material;
    }

    /**
     * 单例模式
     * 采购部门
     *
     * @return 采购部门单例
     * @since 2021-10-20 09:04
     */
    public static PurchaseDepartment getInstance() {
        if (instance == null) {
            instance = new PurchaseDepartment();
            IOManager.getInstance().printPattern(
                    "# 使用单例模式：创建采购部门",
                    "# 使用單例模式：創建采購部門",
                    "# Using singleton Pattern: create a purchasing department"
            );
        }
        return instance;
    }


    /**
     * 用于描述对库存的状态，其中IN为装入库存，OUT为取出库存
     */
    public enum Status {
        IN, OUT;
    }

    /**
     * 用于对库存进行更新
     *
     * @param type  :原材料的种类
     * @param count :原材料的个数
     * @param state :原材料需要更新的状态0
     * @return : boolean 是否成功更新
     * @author 香宁雨
     * @since 13:40 2021-10-19
     */
    public boolean update(String type, Integer count, Status state) {
        IOManager.getInstance().print(
                "正在更新库存...",
                "正在更新庫存...",
                "Updating inventory..."
        );
        Integer index = indexOfRawMaterial(type);
        Integer curCount = rawMaterial.getJSONObject(index).getInt("count");
        if (state == Status.IN) {
            curCount += count;
            rawMaterial.getJSONObject(index).put("count", curCount);
            return true;
        } else {
            if (Double.doubleToLongBits(curCount) >= Double.doubleToLongBits(count)) {
                curCount -= count;
                rawMaterial.getJSONObject(index).put("count", curCount);
                return true;
            }
            return false;
        }
    }

    /**
     * 生产部门所调用的购买接口，采用责任链模式
     *
     * @param material : JSONArray 传入JSONArray，其中包括所需要的所有原材料以及其所需要的个数
     * @return : boolean 是否购买成功
     * @author 香宁雨
     * @since 13:43 2021-10-19
     */
    public boolean purchaseIngredient(JSONArray material) {
        IOManager.getInstance().printBrief(
                "采购部门处理需求...",
                "采購部門處理需求...",
                "Purchasing department processing requirements..."
        );
        IOManager.getInstance().print(
                "正在将采购需求交给采购部经理.....",
                "正在將採購需求交給採購部經理....",
                "Transferring purchasing requirements to purchasing manager......."
        );
        return purchaseManager.purchase(material);
    }

    /**
     * 原材料加工时需要从采购部门获得的材料
     *
     * @param material :  所需要的原材料，包括所需要材料的类型和个数
     * @return : org.json.JSONArray 返回一个重量的JSONArray，代表每个原材料的重量，单位为g
     * @author 香宁雨
     * @since 15:19 2021-10-24
     */
    public JSONArray getIngredient(JSONObject material) {
        IOManager.getInstance().print(
                "正在从采购部门获取原料...",
                "正在從采購部門獲取原料..." ,
                "Obtaining raw materials from purchasing department..."
        );

        JSONArray ingredient = new JSONArray();
        String type = material.getString("ingredientType");
        Integer count = material.getInt("count");
        if (update(type, count, Status.OUT)) {
            for (Integer i = 0; i < count; i++) {
                Random r = new Random();
                Double weight = r.nextDouble() * 200 + 100;
                ingredient.put(weight);
            }
            IOManager.getInstance().print(
                    "成功从采购部门获取原材料",
                    "成功從采購部門獲取原材料",
                    "Successfully obtain raw materials from purchasing department"
            );
        }
        else{
            ingredient = null;
        }
        return ingredient;
    }


    /**
     * 采购流程的测试函数
     *
     * @param args main函数参数args
     */
    public static void main(String[] args) {

        while(true){
            IOManager.getInstance().print(
                    "请选择想要进行的操作：\na. 输出当前采购部门中所拥有的原材料;\nb. 从采购部门获取原材料;\nc. 向采购部门提出采购需求;\nexit. 退出",
                    "請選擇想要進行的操作：\na. 輸出當前采購部門中所擁有的原材料;\nb. 從采購部門獲取原材料;\nc. 向采購部門提出采購需求;\nexit. 退出",
                    "Please select the operation you want to perform:\n " +
                            "a. output the raw materials owned by the current purchasing department; \n" +
                            "b. Obtain raw materials from the purchasing department; \n" +
                            "c. Propose procurement requirements to the procurement department\n" +
                            "exit. end program"
            );
            String operationType = IOManager.getInstance().input();
            if(operationType.equals("a")){
                IOManager.getInstance().print(
                        "采购部门所拥有的原材料为:" + PurchaseDepartment.getInstance().rawMaterial,
                        "采購部門所擁有的原材料為:"+ PurchaseDepartment.getInstance().rawMaterial,
                        "The raw materials owned by the purchasing department are:"+ PurchaseDepartment.getInstance().rawMaterial
                );
            }
            else if(operationType.equals("b")){
                JSONObject apple = new JSONObject();
                apple.put("ingredientType", "candiedapple");
                apple.put("count", 15);
                JSONArray weights = PurchaseDepartment.getInstance().getIngredient(apple);
                if(weights!=null){
                    IOManager.getInstance().print(
                            "每个苹果的重量分别为:" + weights,
                            "每個蘋果的重量分別為:" + weights,
                            "The weight of each candiedapple is:" + weights
                    );
                }
            }
            else if(operationType.equals("c")){
                //创建购买需求
                JSONArray demand = new JSONArray();

                JSONObject candiedApple = new JSONObject();
                candiedApple.put("ingredientType", "candiedapple");
                candiedApple.put("count", 10);
                JSONObject clove = new JSONObject();
                clove.put("ingredientType", "herring");
                clove.put("count", 20);
                JSONObject peach = new JSONObject();
                peach.put("ingredientType", "peach");
                peach.put("count", 21);
                JSONObject salmon = new JSONObject();
                salmon.put("ingredientType", "salmon");
                salmon.put("count", 24);

                demand.put(candiedApple);
                demand.put(clove);
                demand.put(peach);
                demand.put(salmon);

                //购买
                if (PurchaseDepartment.getInstance().purchaseIngredient(demand)) {
                    IOManager.getInstance().print(
                            "采购成功!",
                            "採購成功!",
                            "Purchasing Success! "
                    );
                } else {
                    IOManager.getInstance().print(
                            "采购失败!",
                            "採購失敗!",
                            "Purchasing Failed!"
                    );
                }
            }
            else if(operationType.equals("exit")){
                break;
            }
            else{
                continue;
            }
        }
    }

}

