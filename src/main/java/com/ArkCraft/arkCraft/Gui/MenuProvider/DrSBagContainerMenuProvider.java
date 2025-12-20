package com.ArkCraft.arkCraft.Gui.MenuProvider;

import com.ArkCraft.arkCraft.Gui.Container.DrSBagContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Nullable;

public class DrSBagContainerMenuProvider implements MenuProvider {

    private final BlockPos blockPos;

    public DrSBagContainerMenuProvider(BlockPos pos) {
        blockPos = pos;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.dr_s_bag");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new DrSBagContainerMenu(pPlayerInventory, pContainerId, blockPos);
    }
}
