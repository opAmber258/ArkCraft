package com.ArkCraft.arkCraft.Events.Client;

import com.ArkCraft.arkCraft.Gui.Container.ArkCraftGui;
import com.ArkCraft.arkCraft.Gui.Screen.DrSBagContainerScreen;
import com.ArkCraft.arkCraft.Items.ModItems;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Client {

    @SubscribeEvent
    public static void registerScreen(FMLClientSetupEvent event) {
        event.enqueueWork(()-> MenuScreens.register(ArkCraftGui.dr_s_bag_container.get(),
                DrSBagContainerScreen::new));
    }

}
