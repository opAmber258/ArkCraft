package com.ArkCraft.arkCraft.Events.PlayerInteract;

import com.ArkCraft.arkCraft.Entities.NPC.SkyMico;
import com.ArkCraft.arkCraft.Entities.NPC.TestNPC;
import com.ArkCraft.arkCraft.Entities.NPC.VeryH;
import com.ArkCraft.arkCraft.Items.ModItems;
import com.ArkCraft.arkCraft.Quest.Chapter1;
import com.ArkCraft.arkCraft.Networking.ModNetworking;
import com.ArkCraft.arkCraft.Networking.SoundS2CPacket;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.ArkCraft.arkCraft.Items.Items.RecallIdol.use;

@Mod.EventBusSubscriber
public class PlayerInteract {

    @SubscribeEvent
    public static void Interact(PlayerInteractEvent event) {
        if(!event.getEntity().level().isClientSide) {
            Player player = event.getEntity();
            ItemStack itemStack = event.getItemStack();
            if (itemStack.is(ModItems.RecallIdol.get()) && use) {
                BlockPos blockPos = event.getPos();
                player.sendSystemMessage(Component.literal("当前方块" + blockPos)
                        .withStyle(ChatFormatting.LIGHT_PURPLE));
                use = false;
            }
        }
    }


    @SubscribeEvent
    public static void onRightClickNPC(PlayerInteractEvent.EntityInteractSpecific event) {
        Player player = event.getEntity();
        Entity targetEntity = event.getTarget();
        if(!player.level().isClientSide() &&
                targetEntity != null &&
            event.getHand().equals(InteractionHand.MAIN_HAND)) {
            if (targetEntity instanceof SkyMico) {
                ModNetworking.sendToClient(new SoundS2CPacket
                        (0, SoundSource.MASTER), (ServerPlayer) player);

            }else if (targetEntity instanceof TestNPC) {
                    Chapter1.Mainms(player);
            }else if (targetEntity instanceof VeryH) {
                ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
                player.sendSystemMessage(Component.literal(Utils.equipMaterial.get(stack.getItem())));
            }
        }
    }
}
