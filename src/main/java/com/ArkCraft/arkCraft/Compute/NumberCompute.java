package com.ArkCraft.arkCraft.Compute;



import com.ArkCraft.arkCraft.Entities.Mobs.MobAttributes;
import com.ArkCraft.arkCraft.Entities.Mobs.Spawn.MobSpawn;
import com.ArkCraft.arkCraft.Items.EquipMaterial;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.*;
import java.util.stream.Collectors;


public class NumberCompute {

    public static List<? extends Entity> getNearEntity(Level level, Vec3 center, Class<? extends Entity> type, double distance) {
        List<? extends Entity> list = level.getEntitiesOfClass(type, AABB.ofSize(center, distance * 2, distance * 2, distance * 2));
        return list.stream().filter(e -> e.position().distanceTo(center) <= distance).toList();
    }


    public static Set<Player> getNearPlayer(Level level, Vec3 center, double radius) {
        return getNearEntity(level, center, Player.class, radius).stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .collect(Collectors.toSet());
    }

    public static Player getNearestPlayer(LivingEntity livingEntity, double radius) {
        return getNearPlayer(livingEntity.level(), livingEntity.position(), radius).stream().min(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return (int) (o1.distanceTo(livingEntity) - o2.distanceTo(livingEntity));
            }
        }).orElse(null);
    }

    public static Double ComputePlayerDamage(Player player, Mob mob) {
        double damage = PlayerAttributes.attackDamage(player);
        double defence = MobAttributes.getMobDefence(mob);
        damage = damage - defence;
        if (damage <= 0 && damage <= damage* 0.05){
            damage = damage * 0.05 * 100;
            damage = Math.round(damage);
            damage = damage /100;
            player.displayClientMessage(Component.literal("伤害低于5%,触发保底 造成"
                    + damage +"点伤害!"), true);
            return damage;
        }
        damage = Math.round(damage * 100) / 100.0;
        player.displayClientMessage(Component.literal("你造成了" + damage + "点伤害! "), true);

        return damage;
    }

    public static Double ComputePlayerDamageWithSpeed(
            Player player, Mob mob, double progress // 0~1
    ) {
        double damage = PlayerAttributes.attackDamage(player);
        double defence = MobAttributes.getMobDefence(mob);

        damage *= progress;      // 蓄力比例
        damage *= 0.5;           // 半伤基准

        if (damage <= defence) {
            damage = Math.max(damage * 0.05, 0.1);
        } else {
            damage -= defence;
        }
        damage = Math.round(damage * 100) / 100.0;
        player.displayClientMessage(Component.literal("未蓄满力, 造成" + damage +"伤害"), true);
        return damage;
    }



    /*
    public static Double ComputePlayerDamageWithSpeed(Player player, Mob mob) {
        double damage = PlayerAttributes.attackDamage(player);
        double defence = MobAttributes.getMobDefence(mob);
        double speed =  Utils.playerAttackTick.get(player) / Utils.playerAttackCooldown.get(player);
        damage = damage * speed;
        damage /= 2;
        if (damage < defence | damage-defence < 0.05 * damage){
            damage = damage * 0.05 * 100;
            damage = Math.round(damage);
            damage = damage /100;
            player.sendSystemMessage(Component.literal("伤害低于5%,触发保底 造成"
                    + damage +"点伤害!"));
            return damage;
        }
        damage -= defence;
        damage = damage * 100;
        damage = Math.round(damage);
        damage = damage /100;
        player.sendSystemMessage(Component.literal("未蓄满力,造成了"
                + damage +"点伤害!"));
        return damage;
    }

     */

    public static Double ComputeMobDamage(Player player, Mob mob) {
        double damage = MobAttributes.getMobAttackDamage(mob);
        double defence = PlayerAttributes.defence(player);
        Double finalDamage = damage - defence;
        if (damage <= defence) {
            damage = damage * 0.05 * 100;
            damage = Math.round(damage);
            damage = damage /100;
            return damage;
        }
        return finalDamage;
    }

    public static List<ItemStack> getAllEquipSlotItems(Player player) {
        List<ItemStack> list = new ArrayList<>(List.of(player.getItemBySlot(EquipmentSlot.HEAD),
                player.getItemBySlot(EquipmentSlot.CHEST), player.getItemBySlot(EquipmentSlot.LEGS),
                player.getItemBySlot(EquipmentSlot.FEET)));
        if (Utils.mainHandTag.containsKey(player.getMainHandItem().getItem()))
            list.add(player.getMainHandItem());
        return list;
    }

    public static double computeAllEquipSlotBaseAttributeValue(Player player, Map<Item, Double> map) {
        double totalValue = 0;
        for (ItemStack equip : getAllEquipSlotItems(player)) {
            Item item = equip.getItem();
            double equipValue = map.getOrDefault(equip.getItem(), 0d);
            if ((map.containsKey(item)) && equipValue != 0) {
                totalValue += equipValue;
            }
        }
        return totalValue;
    }


    public static HashMap<String,Integer> getAllEquipMaterialQuantity(Player player,
                                                                      Map<Item, String> map) {
        HashMap<String,Integer> Quantity = new HashMap<>();
        for (ItemStack equip : getAllEquipSlotItems(player)) {
            Item item = equip.getItem();
            String material = Utils.equipMaterial.getOrDefault(item, EquipMaterial.Null);
            if (map.containsKey(item)) {
                Quantity.put(material, Quantity.getOrDefault(material, 0) + 1);
            }
        }
        return Quantity;
    }
}