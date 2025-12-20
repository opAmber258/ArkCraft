package com.ArkCraft.arkCraft.Items.Weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CyanStoneSword extends Item {
    public CyanStoneSword(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
                                TooltipFlag tooltipFlag) {
        components.add(Component.literal("右键使用 获得: ").withStyle(ChatFormatting.AQUA)
                .append(Component.literal("速度II").withStyle(ChatFormatting.OBFUSCATED)));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

}
