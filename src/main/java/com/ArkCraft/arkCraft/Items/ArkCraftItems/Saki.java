package com.ArkCraft.arkCraft.Items.ArkCraftItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class Saki extends Item {
    public Saki(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
                                TooltipFlag tooltipFlag) {
        components
                .add(Component
                .literal("Ave_Mujica键盘手和作曲担当丰川祥子，")
                .withStyle(ChatFormatting.AQUA));
        components
                .add(Component
                        .literal("是位温柔体贴又自信要强的大小姐。")
                        .withStyle(ChatFormatting.AQUA));

        components
                .add(Component
                        .literal("      "));


        components
                .add(Component
                .literal("她会一步一步成长，丢掉自己的幼稚和软弱，")
                .withStyle(ChatFormatting.GRAY)
                .withStyle(ChatFormatting.ITALIC));
        components
                .add(Component
                        .literal("成长为一位能肩负起同伴一生的人。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}

