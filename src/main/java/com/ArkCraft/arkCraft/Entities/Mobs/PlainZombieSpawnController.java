/*package com.ArkCraft.arkCraft.Mobs;


import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import java.util.List;
import java.util.Random;

public class PlainZombieSpawnController extends MobSpawnController {

    public static Style styleOfPlain = Style.EMPTY.withColor(TextColor.parseColor("#02ff00"));
    public static String mobName = "平原僵尸";
    private static PlainZombieSpawnController instance;

    public static PlainZombieSpawnController getInstance(Level world) {  //刷新位置
        if (instance == null) {
            List<Vec3> spawnPos = List.of(
                    new Vec3(0, 118, -150),
                    new Vec3(2, 117, -160),
                    new Vec3(-8, 120, -158)
            );
            instance = new PlainZombieSpawnController(spawnPos, 20, -130, -20, -170, world, 4);
        }
        return instance;
    }

    public PlainZombieSpawnController(List<Vec3> canSpawnPos, int boundaryUpX, int boundaryUpZ,
                                      int boundaryDownX, int boundaryDownZ, Level level, int averageLevel) {
        super(Te.s("平原僵尸", PlainZombieSpawnController.styleOfPlain), canSpawnPos, boundaryUpX, boundaryUpZ,
                boundaryDownX, boundaryDownZ, level, averageLevel);
    }

    @Override
    public MobAttributes getMobAttributes() {
        return new MobAttributes(20, 0, 0, 0.2, 1, 0, 100, 0.2);
    }

    @Override
    public Mob mobItemAndAttributeSet() {
        Zombie zombie = new Zombie(EntityType.ZOMBIE, this.level);
        Random random = new Random();
        int xpLevel = Math.max(1, averageLevel + 5 - random.nextInt(11));
        Style style = PlainZombieSpawnController.styleOfPlain;
        MobSpawn.setMobCustomName(zombie, Te.s(mobName, style), xpLevel);
        // 设置属性
        MobSpawn.MobBaseAttributes.xpLevel.put(MobSpawn.getMobOriginName(zombie), xpLevel);
        MobSpawn.MobBaseAttributes.setMobBaseAttributes(zombie, getMobAttributes());
        // 设置物品
        MobSpawn.setStainArmorOnMob(zombie, style);
        zombie.setItemInHand(InteractionHand.MAIN_HAND, Items.WOODEN_HOE.getDefaultInstance());
        // 设置掉落
        return zombie;
    }
}*/