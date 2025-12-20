package com.ArkCraft.arkCraft.Gui.Container;

import com.ArkCraft.arkCraft.Blocks.DrSBagBlockEntity;
import com.ArkCraft.arkCraft.Blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.SlotItemHandler;

public class DrSBagContainerMenu extends ContainerMenuNormalSlot {

    public DrSBagContainerMenu(Inventory inventory, int pContainerId, BlockPos pos) {
        super(ArkCraftGui.dr_s_bag_container.get(), pContainerId, pos, ModBlocks.DrSBag.get(), 1, 0, 1);
        if (inventory.player.level().getBlockEntity(pos) instanceof DrSBagBlockEntity drSBagBlockEntity) {
            addSlot(new SlotItemHandler(drSBagBlockEntity.getItems(),0, 64, 24));
        }
        addPlayerInventory(inventory, 10, 70);

    }
}
