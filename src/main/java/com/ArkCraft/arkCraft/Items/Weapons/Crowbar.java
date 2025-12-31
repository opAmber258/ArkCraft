package com.ArkCraft.arkCraft.Items.Weapons;

import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;


import java.util.ArrayList;
import java.util.List;



public class Crowbar extends ArkCraftSword {

    public Crowbar(Properties properties,double attackDamage, float attackSpeed) {
        super(properties, attackSpeed);
        Utils.attackDamage.put(this, attackDamage);
        Utils.attackSpeed.put(this,4 + attackSpeed);
        Utils.attackRange.put(this, 3d);
        Utils.arkcraftItems.add(this);
    }



    @Override
    public Component getBranch(ItemStack itemStack) {
        return Component.literal("重剑").withStyle(ChatFormatting.RED);
    }

    @Override
    public List<Component> getArkweaponDescription() {
        List<Component> components = new ArrayList<>();
        components.add(Component.literal("一把老旧的撬棍")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        components.add(Component.literal("随手捡的，用起来挺顺手")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        return components;
    }
}
