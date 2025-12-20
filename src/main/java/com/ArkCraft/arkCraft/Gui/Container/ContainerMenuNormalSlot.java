package com.ArkCraft.arkCraft.Gui.Container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public abstract class ContainerMenuNormalSlot extends ContainerMenu {

    private final int slotCount, slotNormal, slotNormalCount;

    public static final int PLAYER_INVENTORY_COUNT = 27;

    public static final int PLAYER_HOTBAR = 9;

    protected ContainerMenuNormalSlot(@Nullable MenuType<?> pMenuType, int pContainerId, BlockPos pos, Block targetblock, int slotCount, int slotNormal, int slotNormalCount) {
        super(pMenuType, pContainerId, pos, targetblock);
        this.slotCount = slotCount;
        this.slotNormal = slotNormal;
        this.slotNormalCount = slotNormalCount;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        var itemstack = ItemStack.EMPTY;
        var slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            var stack = slot.getItem();
            itemstack = stack.copy();
        if (pIndex < slotCount) {
            if (!this.moveItemStackTo(stack, slotCount, this.slots.size(), true))return ItemStack.EMPTY;
            else if (!this.moveItemStackTo(stack, slotNormal, slotNormalCount, true))return ItemStack.EMPTY;
        }
        }
        return itemstack;
    }
}
