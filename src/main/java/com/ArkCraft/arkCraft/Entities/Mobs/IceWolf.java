package com.ArkCraft.arkCraft.Entities.Mobs;


import com.ArkCraft.arkCraft.Entities.Mobs.Drop.DropEntry;
import com.ArkCraft.arkCraft.Entities.Mobs.Drop.DropTable;
import com.ArkCraft.arkCraft.Entities.Mobs.Spawn.MobProfile;
import com.ArkCraft.arkCraft.Entities.Mobs.Spawn.MobSpawn;
import com.ArkCraft.arkCraft.Items.ModItems;
import com.ArkCraft.arkCraft.Render.CustomStyle;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
public class IceWolf implements MobProfile {

    // 你想要的平均等级（你原来传进 controller 的那个）
    public static String mobName = "路边的野狼";

    @Override
    public String getName() {
        return mobName;
    }

    @Override
    public List<Vec3> spawnPoints() {
        return List.of(
                new Vec3(-40, 115, -168),
                new Vec3(-44, 111, -158),
                new Vec3(-27, 113, -158),
                new Vec3(-31, 117, -177),
                new Vec3(-19, 118, -170),
                new Vec3(-23, 116, -157)
        );
    }

    @Override
    public int maxAliveInArea() {
        return 10;
    }

    @Override
    public int attemptsPerTick() {
        return 2;
    }

    @Override
    public int playerDetectRange() {
        return 48;
    }

    @Override
    public int preventSpawnDistance() {
        return 8;
    }

    @Override
    public int areaBoxHalfSize() {
        return 48;
    }

    @Override
    public DropTable dropTable() {
        DropTable t = new DropTable();
        t.entries.add(new DropEntry(ModItems.IceWolfSkin.get(), 0.8f, 1, 1));
        return t;
    }

    @Override
    public Mob create(Level level) {
        // 创建冰狼
        Wolf wolf = new Wolf(EntityType.WOLF, level);

        // 设置等级（你可以根据需要调整）
        int lv = 2;

        // 设置怪物属性，写入 NBT 和基准属性
        MobAttributes.setMobCustomName(wolf, Te.s(mobName, CustomStyle.styleOfIce), lv);
        MobAttributes.MobBaseAttributes.setMobBaseAttributes(
                wolf,
                new MobAttributes(3, 9, 0.27, 1));

        // 添加掉落表（只添加一次，避免重复）
        MobSpawn.DROP_TABLES.putIfAbsent(mobName, dropTable());

        return wolf;
    }
}


/*
    public static String mobName = "路边的野狼";
    private static IceWolfSpawnController instance;

    public static IceWolfSpawnController getInstance(Level world) {  //刷新位置
        if (instance == null) {
            List<Vec3> spawnPos = List.of(
                    new Vec3(-40, 115, -168),
                    new Vec3(-44, 111, -158),
                    new Vec3(-27, 113, -158),
                    new Vec3(-31, 117, -177),
                    new Vec3(-19, 118, -170),
                    new Vec3(-23, 116, -157)
            );
            instance = new IceWolfSpawnController(spawnPos, -7, -138, -64, -189, world, 2);
        }
        return instance;
    }

    public IceWolfSpawnController(List<Vec3> canSpawnPos, int boundaryUpX, int boundaryUpZ,
                                      int boundaryDownX, int boundaryDownZ, Level level, int averageLevel) {
        super(Te.s("路边的野狼", CustomStyle.styleOfIce), canSpawnPos, boundaryUpX, boundaryUpZ,
                boundaryDownX, boundaryDownZ, level, averageLevel);
    }

    @Override
    public MobAttributes getMobAttributes() {
        return new MobAttributes(
                3,
                9,
                0.27,
                1);
    }

    @Override
    public Mob mobItemAndAttributeSet() {
        net.minecraft.world.entity.animal.Wolf wolf = new Wolf(EntityType.WOLF, this.level);
        Random random = new Random();
        int xpLevel = Math.max(1, averageLevel + 5 - random.nextInt(11));
        Style style = CustomStyle.styleOfIce;
        MobSpawn.setMobCustomName(wolf, Te.s(mobName, style), xpLevel);
        // 设置属性
        MobSpawn.MobBaseAttributes.xpLevel.put(MobSpawn.getMobOriginName(wolf), xpLevel);
        MobSpawn.MobBaseAttributes.setMobBaseAttributes(wolf, getMobAttributes());

        wolf.getPersistentData().putString("arkcraft_mob_key", ArkCraftMobKeys.ICE_WOLF);
        MobSpawn.bindDropTable( ArkCraftMobKeys.ICE_WOLF, getDropTable());
        return wolf;

    }


    public DropTable getDropTable() {
        DropTable t = new DropTable();
        t.entries.add(new DropEntry(ModItems.IceWolfSkin.get(), 1.0f, 1, 1));
        return t;
    }


 */

