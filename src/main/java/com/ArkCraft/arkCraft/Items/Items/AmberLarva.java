package com.ArkCraft.arkCraft.Items.Items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;



public class AmberLarva extends Item {



    public AmberLarva(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (!level.isClientSide() && entity instanceof Player player) {
            // 自定义逻辑：如给玩家添加特殊效果
            player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getMaxHealth() + 4);
        }
        return result;
    }

}