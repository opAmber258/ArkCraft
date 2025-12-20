package com.ArkCraft.arkCraft.Events.Client;

import com.ArkCraft.arkCraft.Gui.Screen.Menu;

import com.ArkCraft.arkCraft.Events.Login.Password;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientPlayerTick {

    public static int screenSetFlag = -1;
    @SubscribeEvent
    public static void ClientTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!player.equals(Minecraft.getInstance().player)) return;
        if (event.side.isClient() && event.phase.equals(TickEvent.Phase.START)
                && event.player.equals(Minecraft.getInstance().player)) {
            Minecraft mc = Minecraft.getInstance();
            if (screenSetFlag != -1) {
                switch (screenSetFlag) {
                    case 0 -> {
                        mc.setScreen((Screen) null);
                        mc.mouseHandler.grabMouse();
                    }

                    case 2145 -> {
                        if (Password.getLoginStatus(player)){
                            mc.setScreen(new Menu(true));
                        }else {
                            player.sendSystemMessage(Component.literal("干嘛, 你还没登录呢")
                                    .withStyle(ChatFormatting.GREEN));
                        }
                    }
                }
                screenSetFlag = -1;
            }
        }
    }

}