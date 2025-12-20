/*package com.ArkCraft.arkCraft.Events.Client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "arkcraft", value = Dist.CLIENT)
public class KeyInput {





  @SubscribeEvent
   public static void onMouseClick(InputEvent.MouseButton.Pre event) {
       Minecraft mc = Minecraft.getInstance();
       if (mc.player == null || mc.screen != null) return;
       if (event.getButton() == 0 && event.getAction() == 1) {
           mc.player.sendSystemMessage(Component.literal("左键按下！"));
       }
   }


}*/