package com.ArkCraft.arkCraft.Render;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class RayFx {

    public static void spawnEndRodRay(ServerLevel level, Vec3 start, Vec3 end, SimpleParticleType type) {
        Vec3 delta = end.subtract(start);
        double len = delta.length();
        if (len < 0.001) return;

        Vec3 dir = delta.normalize();

        double step = 0.25; // 粒子间距（越小越密，越耗）
        int count = (int) Math.ceil(len / step);

        for (int i = 0; i <= count; i++) {
            Vec3 p = start.add(dir.scale(i * step));

            level.sendParticles(
                    type,   // 粒子类型（可换）
                    p.x, p.y, p.z,
                    1,                       // 每个点生成几个粒子
                    0, 0, 0,                 // x/y/z 扩散（0=不散开）
                    0                        // speed（多数粒子用不到）
            );
        }
    }



    public static void spawnDustRay(ServerLevel level, Vec3 start, Vec3 end, DustParticleOptions dustOptions) {
        Vec3 delta = end.subtract(start);
        double len = delta.length();
        if (len < 0.001) return;
        Vec3 dir = delta.normalize();
        double step = 0.25;
        int count = (int) Math.ceil(len / step);

        for (int i = 0; i <= count; i++) {
            Vec3 p = start.add(dir.scale(i * step));
            // 调用dust粒子（传构造好的DustParticleOptions）
            level.sendParticles(dustOptions, p.x, p.y, p.z, 1,
                    0, 0, 0, 0);
        }
    }
}
