package com.ArkCraft.arkCraft.ModCommands;

import com.ArkCraft.arkCraft.Quest.Quest;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Supplier;


public class ProgressGetCommand implements Command<CommandSourceStack> {


    public static ProgressGetCommand instance = new ProgressGetCommand();


    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        ServerPlayer player = commandContext.getSource().getPlayerOrException();
        String str = StringArgumentType.getString(commandContext, "type");

        player.sendSystemMessage(Component.literal("\n[ArkCraft]" +
                        "你的 " + str + " 进度为: " +
                Quest.getProgress(str, player))
                .withStyle(ChatFormatting.GREEN));


        return Command.SINGLE_SUCCESS;
    }
}

