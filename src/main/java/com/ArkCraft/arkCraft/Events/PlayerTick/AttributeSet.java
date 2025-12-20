package com.ArkCraft.arkCraft.Events.PlayerTick;

import com.ArkCraft.arkCraft.Compute.NumberCompute;
import com.ArkCraft.arkCraft.Compute.PlayerAttributes;
import com.ArkCraft.arkCraft.Events.Login.Password;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static com.ArkCraft.arkCraft.Utils.attackSpeed;


@Mod.EventBusSubscriber
public class AttributeSet {


    @SubscribeEvent
    public static void setAttribute(TickEvent.PlayerTickEvent event) {
        if (event.side.isServer() && event.phase.equals(TickEvent.Phase.START)) {
            Player player = event.player;
            if (Password.getLoginStatus(player)){
                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue
                        (PlayerAttributes.movementSpeed(player));

                player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(
                        PlayerAttributes.maxHealth(player));
                /*
                double speed = attackSpeed.getOrDefault(player.getItemInHand(
                        InteractionHand.MAIN_HAND).getItem(), 0f) * 13;
                playerAttackCooldown.put(player, speed);
                //player.sendSystemMessage(Component.literal("" +Utils.playerAttackTick.get(player)));
                if (speed == 0){
                    return;
                }
                if ((Utils.playerAttackTick.get(player) != null &&
                        Utils.playerAttackTick.getOrDefault(player, -1d) >= 0)) {
                    double tick = Utils.playerAttackTick.getOrDefault(player, 0d) -1;
                    Utils.playerAttackTick.put(player, tick);
                }

                 */
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        UUID id = event.player.getUUID();

        int left = Utils.attackCooldownLeft.getOrDefault(id, 0);
        if (left > 0) {
            Utils.attackCooldownLeft.put(id, left - 1);
        }
    }




    /*
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;

        UUID id = event.player.getUUID();

        int left = Utils.attackCooldownLeft.getOrDefault(id, 0);
        if (left > 0) {
            Utils.attackCooldownLeft.put(id, left - 1);
        }
    }

     */

}