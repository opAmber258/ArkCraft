package com.ArkCraft.arkCraft.Items.Armors;


import com.ArkCraft.arkCraft.Render.CustomStyle;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ArkCraftArmors extends ArmorItem {

        public ArkCraftArmors(ArmorMaterial armorMaterial, Type type, Properties properties) {
            super(armorMaterial, type, properties);

            Utils.armorTag.put(this, 1d);

            Utils.armorList.add(this);

            if (type.equals(Type.HELMET)) {
                Utils.helmetList.add(this);
            }
            if (type.equals(Type.CHESTPLATE)) {
                Utils.chestList.add(this);
            }
            if (type.equals(Type.LEGGINGS)) {
                Utils.leggingsList.add(this);
            }
            if (type.equals(Type.BOOTS)) {
                Utils.bootsList.add(this);
            }
        }

        public abstract List<Component> getArkArmorDescription(ItemStack stack);
        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level level,
                                    List<Component> components, TooltipFlag flag) {

            Map<Type, String> typeMap = new HashMap<>() {{
                put(Type.HELMET, "头盔");
                put(Type.CHESTPLATE, "胸甲");
                put(Type.LEGGINGS, "护腿");
                put(Type.BOOTS, "靴子");
            }};
            components.add(Component.literal("类型 ")
                    .withStyle(ChatFormatting.AQUA)
                    .append(Component.literal("防具")
                            .withStyle(ChatFormatting.RESET)
                            .withStyle(ChatFormatting.WHITE))
                    .append(Component.literal("丨")
                            .withStyle(ChatFormatting.RESET)
                            .withStyle(ChatFormatting.GRAY))
                    .append(Component.literal(typeMap.get(this.type))
                            .withStyle(ChatFormatting.RESET)
                            .withStyle(ChatFormatting.GRAY)));
            Utils.descriptionDash(components);
            components.add(Component.literal(" 基础属性")
                    .withStyle(ChatFormatting.WHITE));
            if (Utils.maxHealth.get(this) != null) {
                components.add(Component.literal(" 最大生命值 + ")
                        .withStyle(CustomStyle.styleOfHealth)
                        .append(Component.literal(Utils.maxHealth.get(this).toString())));
            }
            if (Utils.defence.get(this) != null) {
                components.add(Component.literal(" 防御 + ")
                                .withStyle(CustomStyle.styleOfDefence)
                        .append(Component.literal(String.valueOf(Utils.defence.get(this)))));
            }
            if (Utils.attackDamage.get(this) != null) {
                components.add(Component.literal(" 攻击伤害 + ")
                        .withStyle(CustomStyle.styleOfDamage)
                        .append(Component.literal(String.valueOf(Utils.attackDamage.get(this)))));
            }
            Utils.descriptionDash(components);
            if (!getArkArmorDescription(stack).isEmpty()) {
                components.addAll(getArkArmorDescription(stack));
                Utils.descriptionDash(components);
            }
            super.appendHoverText(stack, level, components, flag);
        }
}
