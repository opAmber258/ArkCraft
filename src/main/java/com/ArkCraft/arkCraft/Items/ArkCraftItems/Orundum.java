package com.ArkCraft.arkCraft.Items.ArkCraftItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Orundum extends Item {

    public Orundum(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
                                TooltipFlag tooltipFlag) {
        components
                .add(Component
                        .literal("合成物质。常用于招募干员。")
                        .withStyle(ChatFormatting.RED));

        components
                .add(Component
                        .literal("      "));

        components
                .add(Component
                        .literal("由至纯源石加工而来，")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));


        components
                .add(Component
                        .literal("混合了其他矿物以后的源石产物。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));


        components
                .add(Component
                        .literal("以前仅能当作一些传导元件使用，")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("现在却逐渐成为人事交互关系中令人信服的象征物。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
