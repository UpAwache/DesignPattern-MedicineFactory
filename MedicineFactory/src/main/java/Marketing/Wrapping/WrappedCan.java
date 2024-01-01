package
        Marketing.Wrapping;

import Manufacturing.CanEntity.Drug;
import Marketing.Wrapping.Cover.WrappingCover;

/**
 * 将Can类和Wrapping类合并到一个已经封装好的WrappedCan中
 *
 * @author 王立友
 * @date 2021/10/24 16:55
 */
public class WrappedCan {

    /**
     * 待包装的罐头;
     */
    private Drug can;
    /**
     * 包装信息;
     */
    private WrappingCover wrappingCover;

    public WrappedCan(Drug can, WrappingCover wrappingCover){
        this.can = can;
        this.wrappingCover = wrappingCover;
    }


    public Drug getCan() {
        return can;
    }

    public void setCan(Drug can) {
        this.can = can;
    }

    public WrappingCover getWrappingCover() {
        return wrappingCover;
    }

    public void setWrappingCover(WrappingCover wrappingCover) {
        this.wrappingCover = wrappingCover;
    }


}