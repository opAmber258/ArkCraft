package com.ArkCraft.arkCraft.Entities.Mobs;


import com.ArkCraft.arkCraft.Entities.Mobs.Spawn.MobSpawn;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class MobAttributes {

    public final double attackDamage;
    public final double maxHealth;
    public final double movementSpeed;
    public final double defence;

    public static String getMobOriginName(Mob mob) {
        String s = mob.getName().getString();
        return s.substring(s.indexOf(" ") + 1);
    }


    public MobAttributes(double attackDamage, double maxHealth, double movementSpeed, double defence) {
        this.attackDamage = attackDamage;
        this.maxHealth = maxHealth;
        this.movementSpeed = movementSpeed;
        this.defence = defence;
    }

    public static class MobBaseAttributes {
        public static final Map<String, Integer> xpLevel = new HashMap<>();
        public static final Map<String, Double> attackDamage = new HashMap<>();
        public static final Map<String, Double> movementSpeed = new HashMap<>();
        public static final Map<String, Double> defence = new HashMap<>();
        public static final Map<String, Double> maxHealth = new HashMap<>();

        public static void setMobBaseAttributes(Mob mob, MobAttributes attributes) {
            String mobOriginName = getMobOriginName(mob);
            MobBaseAttributes.attackDamage.put(mobOriginName, attributes.attackDamage);
            MobBaseAttributes.defence.put(mobOriginName, attributes.defence);
            mob.getAttribute(Attributes.MAX_HEALTH).setBaseValue(attributes.maxHealth);
            mob.setHealth(mob.getMaxHealth());
            MobBaseAttributes.movementSpeed.put(mobOriginName, attributes.movementSpeed);
            mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(attributes.movementSpeed);
            mob.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(0);
        }
    }

    public static double getMobAttackDamage(Mob mob) {
        String mobId = getMobOriginName(mob);
        return MobBaseAttributes.attackDamage.getOrDefault(mobId, 0d);
    }

    public static double getMobDefence(Mob mob) {
        String mobId = getMobOriginName(mob);
        return MobBaseAttributes.defence.getOrDefault(mobId, 0d);
    }


    public static void setMobCustomName(Mob mob, Component mobName, int level) {
        mob.setCustomName(Te.s(Component.literal("Lv." + level + " ")
                .withStyle(ChatFormatting.AQUA), mobName));
        mob.setCustomNameVisible(true);
    }
}
