package Management.Assets.ClothingDecoraor;

import Management.HumanResources.Staff.Staff;
import Presentation.Protocol.IOManager;

public class SecurityClothes extends StaffWithClothes {

    public SecurityClothes(Staff staff) {
        super(staff);
    }

    @Override
    public void putOnClothes() {
        IOManager.getInstance().print(
                this.obj.getName() + "穿上了保安服，开始工作",
                this.obj.getName() + "穿上了警衛服，開始工作",
                this.obj.getName() + " put on the security uniform and starts working"
        );
    }

    @Override
    public void takeOffClothes() {
        IOManager.getInstance().print(
                this.obj.getName() + "脱下了保安服，开始工作",
                this.obj.getName() + "脫下了警衛服，開始工作",
                this.obj.getName() + " take off the security uniform and starts working"
        );
    }
}
