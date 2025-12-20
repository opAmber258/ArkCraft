package com.ArkCraft.arkCraft.Items.ArkCraftItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OriginitePrime extends Item {
    public OriginitePrime(Properties properties) {
        super (properties);
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
                                TooltipFlag tooltipFlag) {
        components
                .add(Component
                        .literal("危险而又必不可少的物质。较为珍贵，用途多样。")
                        .withStyle(ChatFormatting.LIGHT_PURPLE));

        components
                .add(Component
                        .literal("      "));

        components
                .add(Component
                        .literal("在工业中用量巨大的源石结晶，")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));



        components
                .add(Component
                        .literal("但有着较高的提取难度。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("是世界的主要能源类精加工产物，")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("同时也是几乎所有源石技艺得以施展的根本。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("如今，即使“它会传播绝症”的传言甚嚣尘上，")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("也依旧没多少人能抵挡住它的诱惑。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
