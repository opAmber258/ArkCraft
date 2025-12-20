package com.ArkCraft.arkCraft.Entities.Mobs.Drop;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DropTable {
    public final List<DropEntry> entries = new ArrayList<>();

    public List<ItemStack> rollAll(RandomSource r) {
        List<ItemStack> out = new ArrayList<>();
        for (DropEntry e : entries) {
            ItemStack s = e.roll(r);
            if (!s.isEmpty()) out.add(s);
        }
        return out;
    }
}
