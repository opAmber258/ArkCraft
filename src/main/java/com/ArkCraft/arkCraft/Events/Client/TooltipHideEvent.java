package com.ArkCraft.arkCraft.Events.Client;

import com.ArkCraft.arkCraft.Items.Armors.ArkCraftArmors;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.ArkCraft.arkCraft.Items.Weapons.ArkCraftWeapons;
import java.util.Iterator;
import net.minecraft.network.chat.Component;

@Mod.EventBusSubscriber(modid = "arkcraft", value = net.minecraftforge.api.distmarker.Dist.CLIENT)
public class TooltipHideEvent {

    @SubscribeEvent
    public static void onTooltipRender(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ArkCraftWeapons | stack.getItem() instanceof ArkCraftArmors) {
            Iterator<Component> iterator = event.getToolTip().iterator();
            int removeLinesLeft = 0;
            while (iterator.hasNext()) {
                Component component = iterator.next();
                String text = component.getString().trim();
                if (text.equals("在主手时：") |
                        text.equals("戴在头上时：") |
                        text.equals("穿在身上时：") |
                        text.equals("穿在腿上时：") |
                        text.equals("穿在脚上时：") ) {
                    iterator.remove();
                    removeLinesLeft = 2;
                }
                else if (removeLinesLeft > 0) {
                    iterator.remove();
                    removeLinesLeft--;
                }
            }
        }
    }
}