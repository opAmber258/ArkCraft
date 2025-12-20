package com.ArkCraft.arkCraft.Items.Weapons;

import com.ArkCraft.arkCraft.Items.Tiers.Tiers;
import com.ArkCraft.arkCraft.Render.CustomStyle;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ArkCraftWeapons extends SwordItem {


    public ArkCraftWeapons(Properties properties,float attackSpeed) {
        super(Tiers.ARKCRAFT,
                0,
                attackSpeed,
                properties);
        Utils.swordTag.put(this, 1d);
        Utils.mainHandTag.put(this, 1d);
        Utils.arkcraftItems.add(this);
    }



    public abstract Component getType(ItemStack itemStack);

    public abstract List<Component> getArkweaponDescription();

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel,
                                List<Component> components, TooltipFlag pIsAdvanced) {
        if (getType(pStack) != null) {
            Utils.descriptionDash(components);
            components.add(Component.literal("类型 ")
                    .withStyle(ChatFormatting.AQUA)
                    .append(Component.literal("主手")
                            .withStyle(ChatFormatting.WHITE))
                    .append(Component.literal("丨")
                            .withStyle(ChatFormatting.RESET)
                            .withStyle(ChatFormatting.GRAY))
                    .append(getType(pStack).getString())
                    .withStyle(ChatFormatting.RESET)
                    .withStyle(ChatFormatting.RED));
        }
        Utils.descriptionDash(components);
        components.add(Component.literal(" 基础属性")
                .withStyle(ChatFormatting.WHITE));
        components.add(Component.literal(" 攻击伤害: ")
                .withStyle(CustomStyle.styleOfDamage)
                .append(Component.literal(String.valueOf(Utils.attackDamage.get(this)))));
        components.add(Component.literal(" 攻击速度: ")
                .withStyle(CustomStyle.styleOfSpeed)
                .append(Component.literal(String.valueOf(Utils.attackSpeed.get(this)))));
        Utils.descriptionDash(components);
        if (getArkweaponDescription() != null) {
            components.addAll(getArkweaponDescription());
            Utils.descriptionDash(components);
        }
        super.appendHoverText(pStack, pLevel, components, pIsAdvanced);
    }
}