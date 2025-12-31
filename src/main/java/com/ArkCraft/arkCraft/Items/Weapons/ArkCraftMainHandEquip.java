package com.ArkCraft.arkCraft.Items.Weapons;

import com.ArkCraft.arkCraft.Items.Tiers.Tiers;
import com.ArkCraft.arkCraft.Render.CustomStyle;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ArkCraftMainHandEquip extends SwordItem {

    public ArkCraftMainHandEquip(Properties properties) {
        super(Tiers.ARKCRAFT,
                0,
                1,
                properties);
        Utils.mainHandTag.put(this, 1d);
        Utils.arkcraftItems.add(this);
    }

    public ArkCraftMainHandEquip(Properties properties, float attackSpeed) {
        super(Tiers.ARKCRAFT,
                0,
                attackSpeed,
                properties);
        Utils.mainHandTag.put(this, 1d);
        Utils.arkcraftItems.add(this);
    }

    public abstract Component getType(ItemStack itemStack);

    public abstract Component getBranch(ItemStack itemStack);

    public abstract List<Component> getArkweaponDescription();

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel,
                                List<Component> components, TooltipFlag pIsAdvanced) {
            Utils.descriptionDash(components);
            components.add(Component.literal("类型 ")
                    .withStyle(ChatFormatting.AQUA)
                    .append(Component.literal("主手")
                            .withStyle(ChatFormatting.WHITE))
                    .append(Component.literal("丨")
                            .withStyle(ChatFormatting.GRAY))
                    .append(getType(pStack))
                    .append(getBranch(pStack)));
        Utils.descriptionDash(components);
        components.add(Component.literal(" 基础属性")
                .withStyle(ChatFormatting.WHITE));
        if (Utils.attackDamage.getOrDefault(pStack.getItem(), 0d) != 0){
            components.add(Component.literal(" 攻击伤害: ")
                    .withStyle(CustomStyle.styleOfDamage)
                    .append(Component.literal(String.valueOf(Utils.attackDamage.get(this)))));
        }
        if (Utils.manaDamage.getOrDefault(pStack.getItem(), 0d) != 0){
            components.add(Component.literal(" 法术伤害: ")
                    .withStyle(CustomStyle.styleOfMana)
                    .append(Component.literal(String.valueOf(Utils.manaDamage.get(this)))));
        }
        if (Utils.attackRange.getOrDefault(pStack.getItem(), 0d) != 0){
            components.add(Component.literal(" 攻击距离: ")
                    .withStyle(CustomStyle.test)
                    .append(Component.literal(String.valueOf(Utils.attackRange.get(this)))));
        }
        if (Utils.attackSpeed.getOrDefault(pStack.getItem(), 0f) != 0){
            components.add(Component.literal(" 攻击速度: ")
                    .withStyle(CustomStyle.styleOfSpeed)
                    .append(Component.literal(String.valueOf(Utils.attackSpeed.get(this)))));
        }
        Utils.descriptionDash(components);
        if (getArkweaponDescription() != null) {
            components.addAll(getArkweaponDescription());
            Utils.descriptionDash(components);
        }
        super.appendHoverText(pStack, pLevel, components, pIsAdvanced);
    }
}