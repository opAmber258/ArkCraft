package com.ArkCraft.arkCraft.Items.Weapons;

import com.ArkCraft.arkCraft.Items.Tiers.Tiers;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public abstract class ArkCraftSword extends ArkCraftMainHandEquip{

    public ArkCraftSword(Properties properties, float attackSpeed) {
        super(properties, attackSpeed);
        Utils.swordTag.put(this, 1d);
    }

    @Override
    public Component getType(ItemStack itemStack) {
        return Component.literal("近战").withStyle(ChatFormatting.RED)
                .append(Component.literal("-").withStyle(ChatFormatting.GRAY));
    }

}
