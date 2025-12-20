/*package 已弃用;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArknightsExpProvider implements ICapabilityProvider, INBTSerializable {

    private ArknightsExp arknightsExp;

    public ArknightsExpProvider() {
        this.arknightsExp = new ArknightsExp(1);
    }

    public static final Capability<ArknightsExp> ARKNIGHTS_EXP_CAPABILITY = CapabilityManager
            .get(new CapabilityToken<ArknightsExp>() {
                @Override
                public String toString() {
                    return super.toString();
                }
            });

    private final LazyOptional<ArknightsExp> lazyOptional = LazyOptional.of(() -> this.arknightsExp);

    @Override
    public Tag serializeNBT() {
        var tag = new CompoundTag();
        arknightsExp.saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(Tag nbt) {
    arknightsExp.loadNBTData((CompoundTag) nbt);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ARKNIGHTS_EXP_CAPABILITY) {
            return lazyOptional.cast();
        } else {
            return lazyOptional.empty();
        }
    }
}
*/