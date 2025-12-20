package com.ArkCraft.arkCraft.ModCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static com.ArkCraft.arkCraft.ModCommands.SetHomeCommand.*;


public class HomeCommand implements Command<CommandSourceStack> {


    public static HomeCommand instance = new HomeCommand();

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player player = context.getSource().getPlayer();
        ServerLevel overWorld = player.level().getServer().getLevel(Level.OVERWORLD);
        if (p_x == 0 && p_y == 0 && p_z == 0) {
            player.sendSystemMessage(Component.literal("你还未设置过家！"));
        } else {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.teleportTo(overWorld, p_x, p_y, p_z, 0, 0);
        }
        return 0;
    }
}