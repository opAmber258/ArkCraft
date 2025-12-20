package com.ArkCraft.arkCraft.Gui.Screen;

import com.ArkCraft.arkCraft.Gui.Container.DrSBagContainerMenu;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DrSBagContainerScreen extends AbstractContainerScreen<DrSBagContainerMenu>{

    public static final ResourceLocation GUI = new ResourceLocation(Utils.MOD_ID,
            "textures/gui/bag.png");

    public DrSBagContainerScreen(DrSBagContainerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 256; // 与纹理宽度一致
        this.imageHeight = 256; // 与纹理高度一致
        this.inventoryLabelY = this.imageHeight - 110;
    }
    

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(GUI, this.leftPos, this.topPos, 0, 0,this.imageWidth,
                this.imageHeight);
    }
}
