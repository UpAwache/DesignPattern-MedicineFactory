package Management.HumanResources;

import Management.Assets.Announcement.AnnouncementManager;
import Management.HumanResources.Staff.Announcer;
import Presentation.Protocol.IOManager;

/**
 * 雇员的基类
 * <b>实现了 Chain of Responsibility 模式</b>
 * <b>实现了 Marker 模式</b>
 * @author 尚丙奇
 * @since 2021-10-16 14:00
 * */
public abstract class BaseEmployee implements AnnouncementManager {

    /**
     * 雇员的名字
     */
    protected String name;

    /**
     * 雇员的时薪
     * 默认为20.8人民币
     */
    protected Double salary = 20.8;

    /**
     * 雇员的直接领导
     * Staff为其Group的Leader
     * GroupLeader为其部门的Manager
     */
    protected BaseEmployee leader;

    protected DepartmentType department = DepartmentType.None;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDepartment(DepartmentType department){
        this.department = department;
    }

    public DepartmentType getDepartment(){
        return this.department;
    }

    public void setLeader(BaseEmployee leader){
        this.leader = leader;
    }

    public Double getSalary(){
        return this.salary;
    }

    public void setSalary(Double salary){
        this.salary = salary;
    }

    public BaseEmployee getLeader(){
        return leader;
    }

    /**
     * 处理请假请求的函数
     * @param request
     * @author 尚丙奇
     * @since 2021-10-16 14:00
     */
    public abstract void handleRequest(LeaveRequest request);


    @Override
    public void subscribe(){
        Announcer.getInstance().addSubscriber(this);
    }

    /**
     * 员工接受消息的函数
     * @param message
     */
    @Override
    public void getMessage(String message){
        IOManager.getInstance().print(
                "员工["+this.name+"]接受消息："+message,
                "員工["+this.name+"]接受消息："+message,
                "Staff["+this.name+"] receives the message:"+message
        );
    }

}
