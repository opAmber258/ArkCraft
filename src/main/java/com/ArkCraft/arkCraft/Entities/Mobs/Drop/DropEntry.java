package com.ArkCraft.arkCraft.Entities.Mobs.Drop;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public record DropEntry(Item item, float chance, int min, int max) {

    public ItemStack roll(RandomSource r) {
        if (chance <= 0f) return ItemStack.EMPTY;
        if (chance < 1f && r.nextFloat() > chance) return ItemStack.EMPTY;

        int amount;
        if (min >= max) amount = min;
        else amount = min + r.nextInt(max - min + 1);

        if (amount <= 0) return ItemStack.EMPTY;
        return new ItemStack(item, amount);
    }
}