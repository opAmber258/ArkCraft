package com.ArkCraft.arkCraft.Events.Login;


import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber
public class LockContainer {


    @SubscribeEvent
    public static void lockInventory(ScreenEvent.Opening event) {
        Screen newScreen= event.getNewScreen();
        Player player = Minecraft.getInstance().player;
        if (player != null && newScreen instanceof InventoryScreen && !Password.getLoginStatus(player)) {
            player.sendSystemMessage(Component.literal("干嘛, 你还没登录呢")
                    .withStyle(ChatFormatting.GREEN));
            event.setCanceled(true);
        }
    }

    /*
    @SubscribeEvent
    public static void lockOtherContainer(PlayerContainerEvent.Open event) {
        Player player = event.getEntity();
        if (!Password.getLoginStatus(player)) {
            player.sendSystemMessage(Component.literal("干嘛, 你还没登录呢")
                    .withStyle(ChatFormatting.GREEN));
            player.closeContainer();
        }
    }

     */
}


