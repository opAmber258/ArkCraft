package com.ArkCraft.arkCraft.Gui.Screen;

import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Menu extends Screen {

    public static final ResourceLocation GUI = new ResourceLocation(Utils.MOD_ID,
            "textures/gui/menu.png");

    public static final Minecraft mc = Minecraft.getInstance();
    private static final Font fontRenderer = mc.font;
    private final boolean showPauseMenu;

    public Menu(Boolean p_96308_) {
        super(p_96308_ ? Component.translatable("menu.menu0") :Component.translatable("menu.menu1"));
        this.showPauseMenu = p_96308_;
    }

    protected void init() {
        if (this.showPauseMenu) {
            this.buildGui();
        }
    }

    private void buildGui() {
        this.addRenderableWidget(Button.builder(Component.literal("采购中心").withStyle(ChatFormatting.WHITE), (p_280814_) -> {
            this.minecraft.setScreen(new Store(true));
        }).pos(340, 184).size(43, 32).build());

    }

    public void render(GuiGraphics graphics, int x, int y, float v) {
        GuiGraphics guiGraphics = new GuiGraphics(mc, mc.renderBuffers().bufferSource());
        this.renderBackground(guiGraphics);
        int textureWidth = 320;
        int textureHeight = 200;

        guiGraphics.blit(GUI, this.width / 2 - 160, this.height / 2 - 100,
                0, 0, 320, 200, textureWidth, textureHeight);
        this.renderables.forEach(renderable -> {
            if (renderable instanceof Button button) {
                if (button.getMessage().getString().contains("采购中心")){
                    if (x > 338 && y > 182 && x < 385 && y < 218) {
                        renderable.render(graphics, x, y, v);
                    }
                } else renderable.render(graphics, x, y, v);
            } else renderable.render(graphics, x, y, v);
        });
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
