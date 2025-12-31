package com.ArkCraft.arkCraft.Events.Client;

import com.ArkCraft.arkCraft.Gui.Screen.Menu;

import com.ArkCraft.arkCraft.Events.Login.Password;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;


@Mod.EventBusSubscriber(Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientPlayerTick {

    public static int screenSetFlag = -1;
    @SubscribeEvent
    public static void ClientTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!player.equals(Minecraft.getInstance().player)) return;
        if (event.side.isClient() && event.phase.equals(TickEvent.Phase.START)
                && event.player.equals(Minecraft.getInstance().player)) {
            Minecraft mc = Minecraft.getInstance();
            if (screenSetFlag != -1) {
                switch (screenSetFlag) {
                    case 0 -> {
                        mc.setScreen((Screen) null);
                        mc.mouseHandler.grabMouse();
                    }

                    case 2145 -> {
                        if (Password.getLoginStatus(player)){
                            mc.setScreen(new Menu(true));
                        }else {
                            player.sendSystemMessage(Component.literal("干嘛, 你还没登录呢")
                                    .withStyle(ChatFormatting.GREEN));
                        }
                    }
                }
                screenSetFlag = -1;
            }
        }
    }

    @Nullable
    public static LivingEntity getCrosshairLiving(Player player, double maxDist) {
        Vec3 eye = player.getEyePosition();
        Vec3 end = eye.add(player.getLookAngle().scale(maxDist));

        AABB aabb = player.getBoundingBox()
                .expandTowards(player.getLookAngle().scale(maxDist))
                .inflate(0.8); // 容錯：准星附近也算

        EntityHitResult hit = ProjectileUtil.getEntityHitResult(
                player.level(),
                player,
                eye,
                end,
                aabb,
                e -> e instanceof LivingEntity le && le.isAlive() && e != player
        );

        return hit != null ? (LivingEntity) hit.getEntity() : null;
    }

    public static void spawnHeadGlowParticles(LivingEntity target) {
        if (!target.level().isClientSide()) return;

        Level level = target.level();

        // 实体头顶高度
        double y = target.getBoundingBox().maxY + 0.35;

        // 轻微左右抖动，让粒子“活”一点
        double offsetX = (level.random.nextDouble() - 0.5) * 0.3;
        double offsetZ = (level.random.nextDouble() - 0.5) * 0.3;

        level.addParticle(
                ParticleTypes.GLOW,   // 发光感强
                target.getX() + offsetX,
                y,
                target.getZ() + offsetZ,
                0.0, 0.02, 0.0           // 微微向上飘
        );
    }



    private static LivingEntity lastTarget = null;
    private static int lostTicks = 0;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        LivingEntity current = getCrosshairLiving(mc.player, Utils.attackRange.getOrDefault(
                mc.player.getItemInHand(InteractionHand.MAIN_HAND).getItem(), 3d));
        if (current != null) {
            // 准星当前指到实体
            lastTarget = current;
            lostTicks = 0;
            spawnHeadGlowParticles(current);
            return;
        }
        // 当前没指到
        if (lastTarget != null) {
            lostTicks++;
            if (lostTicks < 1 && lastTarget.isAlive()) {
                spawnHeadGlowParticles(lastTarget);
            } else {
                lastTarget = null;
            }
        }
    }
}