package com.ArkCraft.arkCraft.Events.Attack;


import com.ArkCraft.arkCraft.Compute.NumberCompute;
import com.ArkCraft.arkCraft.Entities.NPC.SkyMico;

import com.ArkCraft.arkCraft.Networking.ModNetworking;
import com.ArkCraft.arkCraft.Networking.ScreenS2CPacket;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static com.ArkCraft.arkCraft.Utils.*;

@Mod.EventBusSubscriber
public class Attack {

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        Entity target = event.getTarget();
        event.setCanceled(true); // 禁用原版
        if (!(target instanceof Mob mob)) return;
        if (!Utils.isArkCraftMobs(mob)) {
            return;
        }

        UUID id = player.getUUID();

        int max = Utils.attackCooldownMax.getOrDefault(id, 0);
        int left = Utils.attackCooldownLeft.getOrDefault(id, 0);

        // 冷却没好：蓄力攻击
        if (left > 0) {
            double progress = 1.0 - (left / (double) max); // 0~1

            float damage = NumberCompute
                    .ComputePlayerDamageWithSpeed(player, mob, progress)
                    .floatValue();

            mob.invulnerableTime = 0;
            mob.hurt(mob.damageSources().playerAttack(player), damage);
            //applyCustomKnockback(player, mob, 0f);
            applyCustomKnockback(player, mob, 0.0000000000000000000001f);
            startAttackCooldown(player);
            return;
        }

        // 冷却完成：完整攻击
        float damage = NumberCompute
                .ComputePlayerDamage(player, mob)
                .floatValue();

        mob.invulnerableTime = 0;
        mob.hurt(mob.damageSources().playerAttack(player), damage);
        // 命中成功后
        startAttackCooldown(player);
    }

    public static void startAttackCooldown(Player player) {
        UUID id = player.getUUID();

        int cd = calcCooldownTicks(player); // 你之前写的函数
        Utils.attackCooldownMax.put(id, cd);
        Utils.attackCooldownLeft.put(id, cd);
    }


    private static int calcCooldownTicks(Player player) {
        Item item = player.getMainHandItem().getItem();
        float atkSpeed = Utils.attackSpeed.getOrDefault(item, 0f);

        if (atkSpeed <= 0) return Integer.MAX_VALUE;

        return Math.max(1, Math.round(20f / atkSpeed));
    }

/*
    @SubscribeEvent
    public static void AttackEvent(AttackEntityEvent event) {
        if(!event.getEntity().level().isClientSide){
            Player player = event.getEntity();
            Entity entity = event.getTarget();
            ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
            if(Utils.isArkCraftMobs((Mob) entity) && arkcraftItems.contains(itemStack.getItem())){

                if (attackSpeed.get(itemStack.getItem()) != null &&
                        attackSpeed.get(itemStack.getItem()) != 0) {
                    if (playerAttackTick.getOrDefault(player, 0d) <= 0) {
                        float damage = NumberCompute.ComputePlayerDamage(player, (Mob) entity).floatValue();
                        entity.invulnerableTime = 0;
                        entity.hurt(entity.damageSources().playerAttack(player), damage);
                        applyCustomKnockback(player, (LivingEntity) entity, 4f);
                        playerAttackTick.put(player, playerAttackCooldown.getOrDefault(player, 0d));
                        return;
                    }
                }
                float damage = NumberCompute.ComputePlayerDamageWithSpeed(player, (Mob) entity).floatValue();
                entity.invulnerableTime = 0;
                entity.hurt(entity.damageSources().playerAttack(player), damage);
                playerAttackTick.put(player, playerAttackCooldown.getOrDefault(player, 0d));

            }else if (entity instanceof SkyMico) {
                ModNetworking.sendToClient(new ScreenS2CPacket(2145), (ServerPlayer) player);

            }else
            event.setCanceled(true);

        }
    }


 */



    private static void applyCustomKnockback(Player attacker, LivingEntity target, float strength) {

        double dx = attacker.getX() - target.getX();
        double dz = attacker.getZ() - target.getZ();

        // 方向向量归一化
        double len = Math.sqrt(dx * dx + dz * dz);
        if (len < 1.0E-4) return;

        dx /= len;
        dz /= len;

        /*
        // 清掉原版残留无敌帧 / 击退
        target.invulnerableTime = 0;
         */
        // 调用原版 knockback（但强度你说了算）
        target.knockback(
                strength, // 击退强度（重点）
                dx,
                dz
        );

        // 可选：限制Y轴，不然会飞
        target.setDeltaMovement(
                target.getDeltaMovement().x,
                Math.min(target.getDeltaMovement().y, 0.35),
                target.getDeltaMovement().z
        );
    }


    @SubscribeEvent
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        Player player = event.getEntity();


        // 只要左键挥空，不管冷却状态，直接刷新冷却
        startAttackCooldown(player);
    }

    @SubscribeEvent
    public static void onEquipChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide()) return;

        // 只关心主手
        if (event.getSlot() != EquipmentSlot.MAINHAND) return;

        // 如果前后是同一个物品，不处理
        if (event.getFrom().getItem() == event.getTo().getItem()) return;

        UUID id = player.getUUID();

        // 切武器 → 直接进入冷却
        startAttackCooldown(player);
    }
}
