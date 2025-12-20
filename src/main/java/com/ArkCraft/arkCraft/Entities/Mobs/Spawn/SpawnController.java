package com.ArkCraft.arkCraft.Entities.Mobs.Spawn;

import com.ArkCraft.arkCraft.Entities.Mobs.Drop.DropRegistry;
import com.ArkCraft.arkCraft.Entities.Mobs.MobAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import java.util.List;

public class SpawnController {

    private final MobProfile profile;
    private final Vec3 center;

    public SpawnController(MobProfile profile) {
        this.profile = profile;
        this.center = average(profile.spawnPoints());

        // ✅ 掉落表只注册一次
        DropRegistry.bindOnce(profile.getName(), profile.dropTable());
    }

    public void tick(ServerLevel level) {
        // 1) 玩家是否在区域内
        if (!hasPlayerNear(level, profile.spawnPoints(), profile.playerDetectRange())) return;

        // 2) 区域内存活数量是否到上限（只扫一个AABB，不扫全世界）
        int half = profile.areaBoxHalfSize();
        AABB box = AABB.ofSize(center, half * 2.0, half * 2.0, half * 2.0);
        int alive = level.getEntitiesOfClass(Mob.class, box, m ->
                // 使用 getMobOriginName 代替 key()
                MobAttributes.getMobOriginName(m).equals(profile.getName())
        ).size();
        if (alive >= profile.maxAliveInArea()) return;

        // 3) 尝试生成
        List<Vec3> points = profile.spawnPoints();
        int tries = profile.attemptsPerTick();

        for (int i = 0; i < tries; i++) {
            Vec3 p = points.get(level.random.nextInt(points.size()));

            // 玩家离点太近不刷
            if (isPlayerTooClose(level, p, profile.preventSpawnDistance())) continue;

            // 落点基础检查（你可以更严格）
            if (!isSpawnSpotOk(level, p)) continue;

            Mob mob = profile.create(level);
            mob.moveTo(p.x + 0.5, p.y, p.z + 0.5, level.random.nextFloat() * 360f, 0);
            level.addFreshEntity(mob);
        }
    }

    private static boolean hasPlayerNear(ServerLevel level, List<Vec3> points, int range) {
        double r2 = range * (double) range;
        for (Player player : level.players()) {
            Vec3 pp = player.position();
            for (Vec3 sp : points) {
                if (pp.distanceToSqr(sp) <= r2) return true;
            }
        }
        return false;
    }

    private static boolean isPlayerTooClose(ServerLevel level, Vec3 pos, int dist) {
        double d2 = dist * (double) dist;
        for (Player player : level.players()) {
            if (player.position().distanceToSqr(pos) < d2) return true;
        }
        return false;
    }

    private static boolean isSpawnSpotOk(ServerLevel level, Vec3 pos) {
        BlockPos bp = BlockPos.containing(pos);
        boolean air = level.getBlockState(bp).isAir() || level.getBlockState(bp).is(Blocks.SNOW);
        boolean solidBelow = !level.getBlockState(bp.below()).isAir();
        return air && solidBelow;
    }

    private static Vec3 average(List<Vec3> ps) {
        double x = 0, y = 0, z = 0;
        for (Vec3 p : ps) { x += p.x; y += p.y; z += p.z; }
        int n = Math.max(1, ps.size());
        return new Vec3(x / n, y / n, z / n);
    }

}



    /*
    public final Component mobName;
    public List<Mob> mobList = new ArrayList<>();
    public List<Vec3> canSpawnPos;
    public final int maxMobNum;
    public final int boundaryUpX;
    public final int boundaryUpY;
    public final int boundaryUpZ;
    public final int boundaryDownX;
    public final int boundaryDownY;
    public final int boundaryDownZ;
    public final Level level;
    public final int mobPlayerRate;
    public final int averageLevel;
    public final int detectionRange;
    public final double summonOffset;
    public List<Boundary> multiBoundaryList = new ArrayList<>();
    public int preventRefreshDistance = 8;
    public boolean spawnFlag = false;
    public Vec3 averagePos;
    public Set<Vec3> spawnedPos = new HashSet<>();

    public record Boundary(Vec3 upPos, Vec3 downPos) {
    }

    public MobSpawnController(Component mobName, List<Vec3> canSpawnPos, int maxMobNum,
                              int boundaryUpX, int boundaryUpY, int boundaryUpZ,
                              int boundaryDownX, int boundaryDownY, int boundaryDownZ,
                              double summonOffset, int detectionRange, Level level,
                              int mobPlayerRate, int averageLevel, int preventRefreshDistance) {
        this.canSpawnPos = canSpawnPos;
        this.maxMobNum = maxMobNum;
        this.boundaryUpX = boundaryUpX;
        this.boundaryUpY = boundaryUpY;
        this.boundaryUpZ = boundaryUpZ;
        this.boundaryDownX = boundaryDownX;
        this.boundaryDownY = boundaryDownY;
        this.boundaryDownZ = boundaryDownZ;
        this.summonOffset = summonOffset;
        this.detectionRange = detectionRange;
        this.level = level;
        this.mobPlayerRate = mobPlayerRate;
        this.averageLevel = averageLevel;
        this.mobName = mobName;
        this.preventRefreshDistance = preventRefreshDistance;

        if (!canSpawnPos.isEmpty()) {
            double averageX = 0;
            double averageY = 0;
            double averageZ = 0;
            for (Vec3 canSpawnPo : canSpawnPos) {
                averageX += canSpawnPo.x;
                averageY += canSpawnPo.y;
                averageZ += canSpawnPo.z;
            }
            averageX /= canSpawnPos.size();
            averageY /= canSpawnPos.size();
            averageZ /= canSpawnPos.size();
            averagePos = new Vec3(averageX, averageY, averageZ);
        }
    }

    public MobSpawnController(Component mobName, List<Vec3> canSpawnPos, int maxMobNum,
                              int boundaryUpX, int boundaryUpY, int boundaryUpZ,
                              int boundaryDownX, int boundaryDownY, int boundaryDownZ,
                              double summonOffset, int detectionRange, Level level, int mobPlayerRate, int averageLevel) {
        this(mobName, canSpawnPos, maxMobNum,
                boundaryUpX, boundaryUpY, boundaryUpZ,
                boundaryDownX, boundaryDownY, boundaryDownZ,
                summonOffset, detectionRange, level,
                mobPlayerRate, averageLevel, 8);
    }



    public MobSpawnController(Component mobName, List<Vec3> canSpawnPos,
                              int boundaryUpX, int boundaryUpZ,
                              int boundaryDownX, int boundaryDownZ,
                              Level level, int averageLevel) {
        this(mobName, canSpawnPos, canSpawnPos.size() * 3, boundaryUpX, Integer.MAX_VALUE, boundaryUpZ,
                boundaryDownX, -Integer.MAX_VALUE, boundaryDownZ, 2, 16,
                level, 1, averageLevel);
    }




    public void handleTick() {
        mobList.removeIf(mob -> !mob.isAlive());
        judgeOverBoundary();
        // 若允许生成，则将生成标记清零，生成就绪。
        if (spawnFlag) {
            spawnFlag = false;
            spawnedPos.clear();
        }
        Set<Player> players
                = this.level.getServer().getPlayerList().getPlayers().stream()
                .filter(player -> canSpawnPos.stream().anyMatch(pos -> player.position().distanceTo(pos) < 48))
                .collect(Collectors.toSet());
        // 区域内无玩家，则清理怪物。
        if (players.isEmpty()) {
            mobList.forEach(mob -> {
                mob.remove(Entity.RemovalReason.KILLED);
            });
            mobList.clear();
            return;
        }
        // 区域怪物数量达上限，返回。
        if (averagePos != null && level.getEntitiesOfClass(Mob.class,
                        AABB.ofSize(averagePos, 96, 96, 96)).stream()
                .filter(mob -> MobSpawn.getMobOriginName(mob).equals(mobName.getString()))
                .count() > maxMobNum) {
            return;
        }
        if (this.mobList.size() > maxMobNum) {
            return;
        }
        // 遍历节点，尝试生成怪物。
        canSpawnPos.forEach(pos -> {
            // 循环内再次判断怪物数量。
            if (this.mobList.size() > maxMobNum) {
                return;
            }
            // 如果在一个周期内，这个节点生成过怪物，则返回。
            if (spawnedPos.contains(pos)) {
                return;
            }
            // 若该点附近8格内怪物数量大于8，则不生成
            if (level.getEntitiesOfClass(Mob.class, AABB.ofSize(pos,
                            16, 16, 16))
                    .stream().filter(mob
                            -> MobSpawn.getMobOriginName(mob).equals(mobName.getString())).count() >= 8) {
                return;
            }
            // 该点探测附近玩家列表
            Set<Player> playerList = NumberCompute.getNearPlayer(level, pos, detectionRange);
            // 玩家距离此刷新点距离小于指定格则不生成怪物
            if (playerList.stream()
                    .anyMatch(player -> player.position().distanceTo(pos) < preventRefreshDistance)) {
                return;
            }
            // 若没有玩家距离该点在48格内，则不生成
            if (players.stream().noneMatch(player -> player.position().distanceTo(pos) < 48)) {
                return;
            }
            // 满足条件。开始生成怪物，并将该点标记为已生成。
            spawnedPos.add(pos);
            spawnMob(playerList, pos);
        });
    }

    private void judgeOverBoundary() {
        mobList.forEach(mob -> {
            if (mob != null && mob.isAlive()) {
                if (!(mob instanceof Animal)) {
                    Player player = NumberCompute.getNearestPlayer(mob, 32);
                    if (player != null && !player.isCreative() && !player.isSpectator()) {
                        mob.setTarget(player);
                    }
                }
                eachMobTick(mob);
                // 若怪物越过边界 则将怪物随机传送至可重生地点
                if (!multiBoundaryList.isEmpty()) {
                    boolean mobIsInBoundary = false;
                    for (Boundary boundary : multiBoundaryList) {
                        if (mob.getX() > boundary.downPos.x && mob.getY() > boundary.downPos.y
                                && mob.getZ() > boundary.downPos.z && mob.getX() < boundary.upPos.x
                                && mob.getY() < boundary.upPos.y && mob.getZ() < boundary.upPos.z) {
                            mobIsInBoundary = true;
                            break;
                        }
                    }
                    if (!mobIsInBoundary) {
                        mob.moveTo(canSpawnPos.get(new Random().nextInt(canSpawnPos.size())));
                    }
                } else if (mob.getX() > boundaryUpX || mob.getX() < boundaryDownX
                        || mob.getY() > boundaryUpY || mob.getY() < boundaryDownY
                        || mob.getZ() > boundaryUpZ || mob.getZ() < boundaryDownZ) {
                    mob.moveTo(canSpawnPos.get(new Random().nextInt(canSpawnPos.size())));
                }
            }
        });
    }

    private void spawnMob(Set<Player> playerList, Vec3 pos) {
        int summonTimes = 3;
        if (canSpawnPos.size() == 1) {
            summonTimes = 3 + playerList.size();
        }
        for (int i = 0; i < summonTimes; i++) {
            Mob mob = this.mobItemAndAttributeSet();
            Random r = new Random();
            Vec3 offset = Vec3.ZERO;
            if (summonOffset > 0) {
                offset = new Vec3(
                        r.nextDouble(summonOffset) - summonOffset / 2,
                        r.nextDouble(summonOffset) - summonOffset / 2,
                        r.nextDouble(summonOffset) - summonOffset / 2
                );
            }
            Vec3 targetPos = pos.add(0.5, 0.5, 0.5).add(offset);
            Block block = level.getBlockState(
                    new BlockPos((int) targetPos.x, (int) targetPos.y, (int) targetPos.z)).getBlock();
            if (block instanceof IPlantable
                    || block instanceof SnowLayerBlock
                    || block.equals(Blocks.AIR)) {
                mob.moveTo(targetPos);
            } else {
                mob.moveTo(pos.add(0.5, 0.5, 0.5));
            }
            this.mobList.add(mob);
            this.level.addFreshEntity(mob);
            LivingEntity mounts = getMounts();
            if (mounts != null) {
                mounts.moveTo(mob.position());
                level.addFreshEntity(mounts);
                mob.startRiding(mounts);
                MobSpawn.mountsMap.put(mounts, mob);
            }
        }
    }

    public LivingEntity getMounts() {
        return null;
    }

    // 生成怪物
    public abstract Mob mobItemAndAttributeSet();

    // 每只怪物的tick
    public void eachMobTick(Mob mob) {

    }




    public abstract MobAttributes getMobAttributes();


     */
