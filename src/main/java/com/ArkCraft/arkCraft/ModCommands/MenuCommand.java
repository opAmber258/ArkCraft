package com.ArkCraft.arkCraft.ModCommands;

import com.ArkCraft.arkCraft.Networking.ModNetworking;

import com.ArkCraft.arkCraft.Networking.ScreenS2CPacket;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;


public class MenuCommand implements Command<CommandSourceStack> {

    public static MenuCommand instance = new MenuCommand();

    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        ServerPlayer player = commandContext.getSource().getPlayer();
        ModNetworking.sendToClient(new ScreenS2CPacket(2145), player);
        return 0;
    }
}
