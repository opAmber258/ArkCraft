package com.ArkCraft.arkCraft.Entities.Mobs.Spawn;


import com.ArkCraft.arkCraft.Entities.Mobs.Drop.DropTable;

import java.util.*;

public class MobSpawn {
    public static final Map<String, DropTable> DROP_TABLES = new HashMap<>();

    public static void bindDropTableOnce(String key, DropTable table) {
        DROP_TABLES.putIfAbsent(key, table);
    }

    public static DropTable getDropTable(String key) {
        return DROP_TABLES.get(key);
    }
}


    /*
    public static void tick(TickEvent.LevelTickEvent event) {
        if (event.side.isServer() && event.phase.equals(TickEvent.Phase.START)) {
            int tick = Tick.get();
            if (event.level.dimension().equals(Level.OVERWORLD)) {
                if (overWolrdList.isEmpty()) {
                    setOverWorldList(event.level);
                }
                overWolrdList.forEach(mobSpawnController -> {
                    if (tick % Tick.s(36) == (overWolrdList.indexOf(mobSpawnController) % Tick.s(36))) {
                        mobSpawnController.spawnFlag = true;
                    }
                    mobSpawnController.handleTick();
                });
                mountsMap.forEach((k, v) -> {
                    if (v.isRemoved()) {
                        if (k instanceof Animal) {
                            k.setHealth(0);
                        } else if (k instanceof Mob) {
                            k.kill();
                        } else {
                            k.remove(Entity.RemovalReason.KILLED);
                        }
                    }
                });
                mountsMap.entrySet()
                        .removeIf(entry -> entry.getKey().isDeadOrDying() && entry.getValue().isDeadOrDying());
            }
        }
    }

    public static List<MobSpawnController> overWolrdList = new ArrayList<>();

    public static void setOverWorldList(Level overWorld) {
        //overWolrdList.add(PlainZombieSpawnController.getInstance(overWorld));
        overWolrdList.add(IceWolfSpawnController.getInstance(overWorld));
    }




    public static Level clientLevel;

    public static List<MobSpawnController> getAllControllers(boolean isServer) {
        if (overWolrdList.isEmpty()) {
            setOverWorldList(isServer ? Tick.server.getLevel(Level.OVERWORLD) : clientLevel);
        }

        List<MobSpawnController> controllers = new ArrayList<>();
        controllers.addAll(overWolrdList);
        return controllers;
    }

    public static void removeAllMob() {
        overWolrdList.forEach(mobSpawnController -> {
            mobSpawnController.mobList.forEach(mob -> mob.remove(Entity.RemovalReason.KILLED));
            mobSpawnController.mobList.clear();
        });
    }


    public static class MobBaseAttributes {
        public static Map<String, Integer> xpLevel = new HashMap<>();
        public static Map<String, Double> attackDamage = new HashMap<>();
        public static Map<String, Double> movementSpeed = new HashMap<>();
        public static Map<String, Double> defence = new HashMap<>();

        public static Map<String, Map<String, Double>> fromCSVAttributes = new HashMap<>();

        public static double getMobBaseAttribute(Mob mob, Map<String, Double> map) {
            return map.getOrDefault(getMobOriginName(mob), 0d);
        }

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


    public static String getMobOriginName(Mob mob) {
        String s = mob.getName().getString();
        return s.substring(s.indexOf(" ") + 1);
    }


    public static void setMobCustomName(Mob mob, Component mobName, int level) {
        mob.setCustomName(Te.s("Lv." + level + " ", getMobLevelStyle(level), mobName));
        mob.setCustomNameVisible(true);
        mob.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(0);
        mob.getAttribute(Attributes.ARMOR).setBaseValue(0);
    }


    public static List<Style> levelStyleList = new ArrayList<>() {{
        add(CustomStyle.styleOfIce);// < 25
    }};

    public static Style getLevelStyle(int level) {
        return MobSpawn.levelStyleList.get(Math.min(MobSpawn.levelStyleList.size() - 1, level / 25));
    }

    public static Style getMobLevelStyle(int level) {
        return MobSpawn.getLevelStyle(level);
    }

    public static Map<LivingEntity, Mob> mountsMap = new HashMap<>();


    public static final Map<String, DropTable> DROP_TABLES = new HashMap<>();

    public static void bindDropTable(String mobKey, DropTable table) {
        DROP_TABLES.put(mobKey, table);
    }

    public static DropTable getDropTable(String mobKey) {
        return DROP_TABLES.get(mobKey);
    }

     */

