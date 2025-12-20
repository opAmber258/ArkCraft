package com.ArkCraft.arkCraft.Items.ArkCraftItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TwelveF extends Item {
    public TwelveF(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
                                TooltipFlag tooltipFlag) {
        components
                .add(Component
                        .literal("罗德岛术师干员12F，")
                        .withStyle(ChatFormatting.GRAY));
        components
                .add(Component
                        .literal("将用源石技艺为小队提供战术援助。")
                        .withStyle(ChatFormatting.GRAY));

        components
                .add(Component
                        .literal(""));


        components
                .add(Component
                        .literal("至少能帮上些忙。")
                        .withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
