package com.ArkCraft.arkCraft.Gui.Container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public abstract class ContainerMenu extends AbstractContainerMenu {

    private final BlockPos pos;

    private final Block targetblock;

    protected ContainerMenu(@Nullable MenuType<?> pMenuType, int pContainerId, BlockPos pos, Block targetblock) {
        super(pMenuType, pContainerId);
        this.pos = pos;
        this.targetblock = targetblock;
    }

    public int addSlotLine(Container container, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(container, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }


    public int addSlotBox(Container container, int index, int x, int y, int xAmount,
                          int dx, int yAmount, int dy) {
        for (int p = 0; p < yAmount; p++) {
            index = addSlotLine(container, index, x, y, xAmount, dx);
            y += dy;
        }
        return index;
    }

    public void addPlayerInventory (Container playerInventory, int x, int y) {
        addSlotBox(playerInventory, 9, x, y, 9, 18, 3, 18);
        y+=58;
        addSlotLine(playerInventory,0, x, y, 9, 18);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(pPlayer.level(), pos), pPlayer, targetblock);
    }



}
