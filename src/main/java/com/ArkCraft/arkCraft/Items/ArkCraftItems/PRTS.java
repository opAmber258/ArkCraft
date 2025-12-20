package com.ArkCraft.arkCraft.Items.ArkCraftItems;

import com.ArkCraft.arkCraft.Items.ModItems;
import com.ArkCraft.arkCraft.Networking.ModNetworking;
import com.ArkCraft.arkCraft.Networking.ScreenS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PRTS extends Item {


    public PRTS(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
                                TooltipFlag tooltipFlag) {
        components
                .add(Component
                        .literal("一个神秘的终端，")
                        .withStyle(ChatFormatting.GRAY));
        components
                .add(Component
                        .literal("你忘记它何时出现在你手中。")
                        .withStyle(ChatFormatting.GRAY));

        components
                .add(Component
                        .literal(""));
        components
                .add(Component
                        .literal(""));


        components
                .add(Component
                        .literal("这玩意到底是哪来的")
                        .withStyle(ChatFormatting.DARK_GRAY)
                        .withStyle(ChatFormatting.ITALIC)
                        .withStyle(ChatFormatting.STRIKETHROUGH));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player,
                                                  InteractionHand interactionHand) {
        if(!level.isClientSide() && interactionHand.equals(InteractionHand.MAIN_HAND)) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            ModNetworking.sendToClient(new ScreenS2CPacket(2145), serverPlayer);
        }
        return super.use(level, player, interactionHand);
    }
}




