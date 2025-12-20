package com.ArkCraft.arkCraft.Items.Items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyFirstItem extends Item{


    public MyFirstItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
                                TooltipFlag tooltipFlag) {
        components.add(Component.literal("这是什么玩意啊???")
                .withStyle(ChatFormatting.LIGHT_PURPLE)
                .withStyle(ChatFormatting.ITALIC));
        components.add(Component.literal("寒星画的")
                .withStyle(ChatFormatting.AQUA)
                .withStyle(ChatFormatting.BOLD));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player,
                                                  InteractionHand interactionHand) {
            if(!level.isClientSide() && interactionHand.equals(InteractionHand.MAIN_HAND)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,1800,2));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,1200,3  ));
            //前面的值为tick 后面的值为等级+1
            player.sendSystemMessage(Component.literal("你使用了结月锭")  //力量3速度4
                    .withStyle(ChatFormatting.LIGHT_PURPLE));


        }
        return super.use(level, player, interactionHand);
    }
}
