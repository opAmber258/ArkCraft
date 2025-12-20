package com.ArkCraft.arkCraft.Events.Block;

import com.ArkCraft.arkCraft.Events.Login.Password;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber
public class BlockBreakAndPlace {

    @SubscribeEvent
    public static void BlockBreakEvent(BlockEvent.BreakEvent event) {
        ServerPlayer player = (ServerPlayer) event.getPlayer();

        if (player.gameMode.getGameModeForPlayer().equals(GameType.CREATIVE)) {
            if (!Password.getLoginStatus(player)){
                event.setCanceled(true);
            }

        } else {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void BlockPlaceEvent(BlockEvent.EntityPlaceEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();

        if (player.gameMode.getGameModeForPlayer().equals(GameType.CREATIVE)) {
            if (!Password.getLoginStatus(player)){
                event.setCanceled(true);
            }

        } else {
            event.setCanceled(true);
        }
    }

    private static final Map<String, Set<String>> COMMAND_WHITELIST = Map.of(
            "arkcraft", Set.of("login", "register")
    );

    @SubscribeEvent
    public static void onCommand(CommandEvent event) {
        if (!(event.getParseResults().getContext().getSource().getEntity() instanceof Player player))
            return;

        if (!Password.getLoginStatus(player)) {
            String full = event.getParseResults()
                    .getReader()
                    .getString()
                    .toLowerCase();

            String[] args = full.split(" ");
            String root = args[0];

            if (COMMAND_WHITELIST.containsKey(root)) {
                if (args.length >= 2 && COMMAND_WHITELIST.get(root).contains(args[1])) {
                    return;
                }
            }

            player.sendSystemMessage(
                    Component.literal("你还没登录，不能使用该命令")
                            .withStyle(ChatFormatting.GREEN)
            );
            event.setCanceled(true);
        }
    }
}
