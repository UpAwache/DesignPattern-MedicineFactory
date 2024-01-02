package Management.HumanResources.Staff;

import Management.HumanResources.DepartmentType;
import Management.HumanResources.FinancialSystem.DataAccessObject.SalaryDaoImpl;
import Management.HumanResources.FinancialSystem.Permission;
import Presentation.Protocol.IOManager;

/**
 * 审计部门，归于FinancialDept下
 *
 * @author 陈垲昕
 * @since 2021/10/21 9:52 下午
 */

public class Auditor extends Staff implements Permission {

    SalaryDaoImpl salaryTable = SalaryDaoImpl.getInstance();

    /**
     * 含名字的构造函数
     * @param name : 审计员名字
     * @author 陈垲昕
     * @since 2021-10-21 10:09 下午
     */
    public Auditor(String name){
        super.department= DepartmentType.Finance;
        this.name=name;
    }


    /**
     * 含名字和薪水的构造函数
     * @param name : 审计员名字
     * @param salary :薪水
     * @author 陈垲昕
     * @since 2021-10-17 9:03 下午
     */
    public Auditor(String name,Double salary){
        super.department=DepartmentType.Finance;
        this.name=name;
        this.salary=salary;
    }


    /**
     * 审计员审计Report的操作: 通过脏标记判断是否需要审阅，如果需要审阅则进行审阅
     * @author 陈垲昕
     * @since 2021-10-21 10:14 下午
     */
    public boolean auditSalaryReport(){

        //report的脏标记已访问过的话，则无需继续
        if(salaryTable.getVisited()){

            IOManager.getInstance().printPattern(
                    "# 使用了脏标记模式  员工薪水表已经被审阅过，无需重复审阅",
                    "# 使用了臟標記模式  員工薪水表已經被審閱過，無需重復審閱",
                    "# Adopted Dirty Flag pattern  The employee salary report has been reviewed, extra audition is unnecessary."
            );
            return false;
        }

        IOManager.getInstance().print(
                "审计员"+this.getName()+"对报告进行审阅",
                "審計員"+this.getName()+"對報告進行審閱",
                "The Auditor"+this.getName()+" has reviewed the finance report. "
        );


        // 重设脏标记
        salaryTable.setVisited();

        return true;
    }

    /**
     * 访问财务系统
     * @author 尚丙奇
     * @since 2021-10-16 14:00
     */
    public void accessFinancialSystem() {
        IOManager.getInstance().print(
                "会计" + name + "访问了财务系统。",
                "會計" + name + "訪問了財務系統。",
                "Accountant " + name + "accessed the financial system.");
    }
}