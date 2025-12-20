package com.ArkCraft.arkCraft.Compute;

import com.ArkCraft.arkCraft.Items.ArkCraftItems.ManageEquip;
import com.ArkCraft.arkCraft.Utils;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
/*
public class OpsAttributes {
    public static double attackDamage = 0;
    public static double defence = 0;
    public static double movementSpeed = 0.5;
    public static double maxHealth = 20;


    public static double getValue(Map<Item, Double> attribute, Player player) {
        if (player.getOffhandItem().getItem() instanceof ManageEquip) {
            Map<Map<Item, Double>, Double> map1 = ImmutableMap.of(
                    Utils.attackDamage, attackDamage,
                    Utils.defence, defence
            );
            Map<Map<Item, Double>, Double> map2 = ImmutableMap.of(
                    Utils.movementSpeedCommon, movementSpeed,
                    Utils.maxHealth, maxHealth
            );
            Map<Map<Item, Double>, Double> map = new HashMap<>() {{
                putAll(map1);
                putAll(map2);
            }};
            return map.getOrDefault(attribute, 0d);
        }
        return 0;
    }
}*/
