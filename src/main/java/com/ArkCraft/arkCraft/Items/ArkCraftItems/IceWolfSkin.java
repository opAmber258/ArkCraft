package com.ArkCraft.arkCraft.Items.ArkCraftItems;

import com.ArkCraft.arkCraft.Render.CustomStyle;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class IceWolfSkin extends Item {

    public IceWolfSkin(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel,
                                List<Component> components, TooltipFlag pIsAdvanced) {

        Utils.descriptionDash(components);
        components.add(Component.literal("类型 ")
                .withStyle(ChatFormatting.AQUA)
                .append(Component.literal("物品")
                        .withStyle(ChatFormatting.RESET)
                        .withStyle(ChatFormatting.WHITE))
                .append(Component.literal("丨")
                        .withStyle(ChatFormatting.RESET)
                        .withStyle(ChatFormatting.WHITE))
                .append(Component.literal("材料")
                        .withStyle(ChatFormatting.RESET)
                        .withStyle(ChatFormatting.BLUE)));
        components.add(Component.literal(""));
        components.add(Component.literal("刚从狼身上扒下来的皮,还是热乎的")
                .withStyle(ChatFormatting.GRAY));
        components.add(Component.literal(""));
        components.add(Component.literal("用途 ")
                .withStyle(ChatFormatting.GOLD)
                .withStyle(ChatFormatting.BOLD)
                .append(Component.literal("可在工作台处制成狼皮装备")
                        .withStyle(ChatFormatting.RESET)
                        .withStyle(ChatFormatting.WHITE)
                        .withStyle(ChatFormatting.BOLD)));
        Utils.descriptionDash(components);

    }
}
