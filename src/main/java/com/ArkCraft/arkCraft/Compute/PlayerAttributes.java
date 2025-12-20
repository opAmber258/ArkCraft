package com.ArkCraft.arkCraft.Compute;

import com.ArkCraft.arkCraft.Utils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;


public class PlayerAttributes {

    public static Map<String, Map<Map<Item, Double>, Double>> playerAttributeCache = new HashMap<>();
    public static Map<String, Map<Map<Item, Double>, Integer>> computeAttributeTick = new HashMap<>();



    /*public static boolean canGetFromCache(Player player, Map<Item, Double> attribute) {
        if (player.getOffhandItem().getItem() instanceof ManageEquip) return true;
        // 初始化
        int tick = Tick.get();
        String name = Name.get(player);
        if (!playerAttributeCache.containsKey(name)) {
            playerAttributeCache.put(name, new HashMap<>());
        }
        if (!computeAttributeTick.containsKey(name)) {
            computeAttributeTick.put(name, new HashMap<>());
        }
        if (!computeAttributeTick.get(name).containsKey(attribute)) {
            computeAttributeTick.get(name).put(attribute, 0);
        }

        Map<Map<Item, Double>, Integer> tickMap = computeAttributeTick.get(name);
        return tickMap.getOrDefault(attribute, 0) == tick;
    }


    public static double getFromCache(Player player, Map<Item, Double> attribute) {
        if (player.getOffhandItem().getItem() instanceof ManageEquip) {
            return OpsAttributes.getValue(attribute, player);
        }
        Map<Map<Item, Double>, Double> attributeMap = playerAttributeCache.get(Name.get(player));
        return attributeMap.get(attribute);
    }
*/
    public static double maxHealth(Player player) {
        /*if (canGetFromCache(player, Utils.maxHealth)) {
             return getFromCache(player, Utils.maxHealth);
        }*/
        double maxHealth = 20;
        maxHealth += NumberCompute.computeAllEquipSlotBaseAttributeValue(player, Utils.maxHealth);
        return maxHealth;
    }

    public static double attackDamage(Player player) {
        double attackDamage = 0;
        attackDamage += NumberCompute.computeAllEquipSlotBaseAttributeValue(player, Utils.attackDamage);
        return attackDamage;
    }

    public static double defence(Player player) {
        double defence = 0;
        defence += NumberCompute.computeAllEquipSlotBaseAttributeValue(player, Utils.defence);
        return defence;
    }


    public static double movementSpeed(Player player) {
        double movementSpeed = 0.1;
        movementSpeed += NumberCompute.computeAllEquipSlotBaseAttributeValue
                (player, Utils.movementSpeedCommon);
        return movementSpeed;
    }
}
