/*
package 已弃用;

import net.minecraft.nbt.CompoundTag;

public class ArknightsExp {

    private int exp;

    public ArknightsExp(int exp) {
        this.exp = exp;
    }

    public  int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void increase(int i) {
        exp +=  i;
    }

    public boolean decrease(int i) {
        if (exp >= i) {
            exp -= i;
            return true;
        } else {
            return false;
        }
    }

    public void saveNBTData(CompoundTag compoundTag) {
        compoundTag.putInt("arknights_exp", exp);
    }

    public void loadNBTData(CompoundTag compoundTag){
        exp = compoundTag.getInt("arknights_exp");
    }
}
*/