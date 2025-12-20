package com.ArkCraft.arkCraft.Gui.Screen;

import com.ArkCraft.arkCraft.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class Store extends Screen {

    public Store(Boolean p_96308_) {
        super(p_96308_ ?  Component.translatable("menu.store0"):Component.translatable("menu.store1"));
        this.showPauseMenu = p_96308_;
    }
    public static final ResourceLocation GUI = new ResourceLocation(Utils.MOD_ID,
            "textures/gui/store.png");

    public static final Minecraft mc = Minecraft.getInstance();
    private static final Font fontRenderer = mc.font;
    private final boolean showPauseMenu;


    public void render(GuiGraphics graphics, int x, int y, float v) {
        GuiGraphics guiGraphics = new GuiGraphics(mc, mc.renderBuffers().bufferSource());
        this.renderBackground(guiGraphics);
        int textureWidth = 320;
        int textureHeight = 200;

        guiGraphics.blit(GUI, this.width / 2 - 160, this.height / 2 - 100,
                0, 0, 320, 200, textureWidth, textureHeight);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

