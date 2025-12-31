package com.ArkCraft.arkCraft;

import com.ArkCraft.arkCraft.Render.CustomStyle;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.*;

public class Utils {
    public static final String MOD_ID = "arkcraft";

    public static Map<Item, Double>attackDamage = new HashMap<>();
    public static Map<Item, Double>manaDamage = new HashMap<>();
    public static Map<Item, Float>attackSpeed = new HashMap<>();
/*
    public static Map<Player, Double> playerAttackCooldown = new HashMap<>();

    public static Map<Player, Double> playerAttackTick = new HashMap<>();

 */


    public static Map<UUID, Integer> attackCooldownMax = new HashMap<>();
    public static Map<UUID, Integer> attackCooldownLeft = new HashMap<>();


    public static Map<Item, Double> movementSpeedCommon = new HashMap<>();

    public static Map<Item, Double> maxHealth = new HashMap<>();
    public static Map<Item, Double> attackRange = new HashMap<>();

    public static Map<Item, Double> defence = new HashMap<>();

    public static HashMap<Item,Double> swordTag = new HashMap<>();

    public static HashMap<Item,Double> staffTag = new HashMap<>();

    public static HashMap<Item, Double> mainHandTag = new HashMap<>();

    public static HashMap<Item,Double> armorTag = new HashMap<>();

    public static HashMap<Item, String> equipMaterial = new HashMap<>();


    public static List<Item> armorList = new ArrayList<>();

    public static List<Item> helmetList = new ArrayList<>();
    public static List<Item> chestList = new ArrayList<>();
    public static List<Item> leggingsList = new ArrayList<>();
    public static List<Item> bootsList = new ArrayList<>();

    public static List<Item> arkcraftItems = new ArrayList<>();


    public static int Num = 0;

    public static void descriptionDash(List<Component> components) {
        components.add((Component.literal("────────────────").withStyle(ChatFormatting.GRAY)));
    }

    public static boolean isArkCraftMobs(Mob mob) {
        if (mob.getDisplayName().getString().contains("Lv.")) {
            return true;
        }return false;
    }
}
