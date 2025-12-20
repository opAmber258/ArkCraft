package com.ArkCraft.arkCraft.Entities.Mobs.Spawn;


import com.ArkCraft.arkCraft.Entities.Mobs.IceWolf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class SpawnManager {

    private static final Map<ServerLevel, List<SpawnController>> CONTROLLERS = new WeakHashMap<>();

    private static List<SpawnController> build(ServerLevel level) {
        // 在这里把所有怪 profile 加进去
        return List.of(
                new SpawnController(new IceWolf())
        );
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (!(event.level instanceof ServerLevel level)) return;
        if (event.phase != TickEvent.Phase.END) return;

        // ✅ 限频：每秒一次（先稳定再优化）
        if (level.getGameTime() % 20 != 0) return;

        // 只在主世界（你需要多维度就删掉这行）
        if (!level.dimension().equals(Level.OVERWORLD)) return;

        List<SpawnController> list = CONTROLLERS.computeIfAbsent(level, SpawnManager::build);
        for (SpawnController c : list) c.tick(level);
    }
}
