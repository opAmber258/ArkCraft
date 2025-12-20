/*package com.MyMod.myMod.Events.PlayerTick;


import com.MyMod.myMod.Events.LivingHurt.LivingHurt;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerTick {

    @SubscribeEvent

    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        if (event.side.isServer()) {
            Player player = event.player;
            if (event.phase.equals(TickEvent.Phase.START)&& player.tickCount % 2 == 0) {
            }
            if (event.phase.equals(TickEvent.Phase.START) && player.tickCount % 40 == 0) {
                //如果tick阶段为开始且tick除以十余数为0
                LivingHurt.armor += 1;
                player.sendSystemMessage(Component.literal
                        ("过去了2秒，你的护盾回复1点!").withStyle(ChatFormatting.AQUA));
            }
        }
    }
}
*/