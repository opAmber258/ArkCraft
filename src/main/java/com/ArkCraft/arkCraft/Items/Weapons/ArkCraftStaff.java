package com.ArkCraft.arkCraft.Items.Weapons;

import com.ArkCraft.arkCraft.Render.RayFx;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class ArkCraftStaff extends ArkCraftMainHandEquip{
    public ArkCraftStaff(Properties properties) {
        super(properties);
        Utils.staffTag.put(this, 1d);
    }
    @Override
    public Component getType(ItemStack itemStack) {
        return Component.literal("远程").withStyle(ChatFormatting.GREEN)
                .append(Component.literal("-").withStyle(ChatFormatting.GRAY));
    }

    public enum FxType {
        RAY1,
        RAY2
    }

    public record HitResult(Vec3 hitPos, @Nullable Entity hitEntity) {}

    protected HitResult getHitResultNoWall(Player player, double range) {


        Level level = player.level();

        Vec3 eye = player.getEyePosition();
        Vec3 look = player.getLookAngle();
        Vec3 end = eye.add(look.scale(range));

        BlockHitResult blockHit = level.clip(new ClipContext(
                eye, end,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        Vec3 blockPos = blockHit.getLocation();
        double maxDist = eye.distanceTo(blockPos);

        AABB box = player.getBoundingBox()
                .expandTowards(look.scale(maxDist))
                .inflate(0.6);

        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                level, player,
                eye,
                eye.add(look.scale(maxDist)),
                box,
                e -> e instanceof LivingEntity && e.isAlive() && e != player);
        if (entityHit != null) {
            Vec3 origin = player.getEyePosition();
            Vec3 direction = player.getLookAngle();
            Entity entity = entityHit.getEntity();
            AABB entityAabb = entity.getBoundingBox();
            Optional<Vec3> hitOptional = entityAabb.clip(origin, origin.add(direction.scale(range)));
            Vec3 hitPos;
            hitPos = hitOptional.orElseGet(() -> getClosestPointTo(entityAabb, origin));
            return new HitResult(hitPos, entity);
        }
        return new HitResult(blockPos, null);
    }


    private Vec3 getClosestPointTo(AABB aabb, Vec3 origin) {
        // X轴最近点
        double closestX = Math.max(aabb.minX, Math.min(origin.x, aabb.maxX));
        // Y轴最近点
        double closestY = Math.max(aabb.minY, Math.min(origin.y, aabb.maxY));
        // Z轴最近点
        double closestZ = Math.max(aabb.minZ, Math.min(origin.z, aabb.maxZ));
        return new Vec3(closestX, closestY, closestZ);
    }

    protected Vec3 getFxStart(Player player) {
        Vec3 look = player.getLookAngle();
        Vec3 right = look.cross(new Vec3(0, 1, 0)).normalize();
        return player.getEyePosition()
                .add(look.scale(0.4))
                .add(right.scale(0.3))
                .add(0, -0.2, 0);
    }

    protected HitResult playFxAndGetHit(ServerLevel level, Player player, FxType fx, double range,
                                        SimpleParticleType type) {
        Vec3 start = getFxStart(player);
        HitResult hit = getHitResultNoWall(player, range);
        switch (fx) {
            case RAY1 -> RayFx.spawnEndRodRay(level, start, hit.hitPos(), type);
            case RAY2 -> {
                DustParticleOptions redDust = new DustParticleOptions(new Vector3f(1, 0.5f, 0), 1.0f);
                RayFx.spawnDustRay(level, start, hit.hitPos(), redDust);
            }
        }
        return hit;
    }
}
