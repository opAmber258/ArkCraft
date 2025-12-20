package com.ArkCraft.arkCraft.Events.LivingHurt;

import com.ArkCraft.arkCraft.Compute.NumberCompute;

import com.ArkCraft.arkCraft.Main;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber
public class LivingHurt {

    @SubscribeEvent
    public static void playerHurt(LivingHurtEvent event) {
        if (!event.getEntity().level().isClientSide) {
            if (event.getEntity() instanceof Player player &&
                    event.getSource().getEntity() instanceof Mob mob ) {

                double finalDamage = NumberCompute.ComputeMobDamage(player, mob);

                if (finalDamage > 0 && player.isAlive()) {

                    player.setHealth((float) (player.getHealth() - finalDamage));


                    player.displayClientMessage(Component.literal("你受到了" + finalDamage +"点伤害!"),
                            true);
                    event.setCanceled(true);
                }
            }
        }
    }




    /*
    public static float armor = 30;
    @SubscribeEvent
    public static void myArmor(LivingHurtEvent event) {
        if (!event.getEntity().level().isClientSide) {
            LivingEntity hurtEntity = event.getEntity();
            if (hurtEntity instanceof Player) {
                Player player = (Player) event.getEntity();
                float hurt = event.getAmount();
                if (armor > 0 | armor > hurt) {
                    event.setAmount(0);
                    armor = armor - hurt;
                    player.sendSystemMessage(Component.literal("受到了" + hurt + "点伤害" +
                            "当前护盾为" + armor + "点").withStyle(ChatFormatting.AQUA));
                    if (armor <= 0) {
                        event.setAmount(-armor);
                        player.sendSystemMessage(Component.literal
                                ("因护盾损坏，你受到了" + (-armor) + "点伤害!")
                                .withStyle(ChatFormatting.AQUA));
                        player.sendSystemMessage(Component.literal
                                        ("护盾重置为0点")
                                .withStyle(ChatFormatting.AQUA));
                        armor = 0;
                    }
                }
            }
        }
    }*/
}
