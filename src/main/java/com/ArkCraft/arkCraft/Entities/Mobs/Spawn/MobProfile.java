package com.ArkCraft.arkCraft.Entities.Mobs.Spawn;

import com.ArkCraft.arkCraft.Entities.Mobs.Drop.DropTable;
import com.ArkCraft.arkCraft.Entities.Mobs.MobAttributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public interface MobProfile {
    String getName();
    List<Vec3> spawnPoints();     // 刷新点
    int maxAliveInArea();         // 区域最大存活数
    int attemptsPerTick();        // 每次tick尝试生成几只（默认每秒tick一次）
    int playerDetectRange();      // 玩家在多少格内才开始刷
    int preventSpawnDistance();   // 玩家离刷新点太近不刷

    int areaBoxHalfSize();        // 存活统计包围盒半径（如 48）
    Mob create(Level level);// 创建实体 + 写NBT + 设置属性/名字

    DropTable dropTable();        // 掉落表
}