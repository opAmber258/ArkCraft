package com.ArkCraft.arkCraft.Items.Items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


import java.util.List;

public class RecallIdol extends Item{

    public static boolean use = false;

    public RecallIdol(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level,
                                List<Component> components, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, components, flag);
        components.add(Component.literal("右键使用").withStyle(ChatFormatting.AQUA));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player,
                                                  InteractionHand interactionHand) {
        if (!level.isClientSide() && interactionHand.equals(InteractionHand.MAIN_HAND)) {
            use = true;
        }
        return super.use(level, player, interactionHand);

    }
}
