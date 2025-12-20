package com.ArkCraft.arkCraft.ModCommands;

import com.ArkCraft.arkCraft.Quest.Quest;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;



public class ProgressSetCommand implements Command<CommandSourceStack> {



    public static ProgressSetCommand instance = new ProgressSetCommand();
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {

        ServerPlayer player = commandContext.getSource().getPlayerOrException();
        ServerPlayer targetPlayer = EntityArgument.getPlayer(commandContext, "player");
        int newProgress = IntegerArgumentType.getInteger(commandContext, "progress");
        String type = StringArgumentType.getString(commandContext, "type");
        Quest.setProgress(type, targetPlayer, newProgress);

        player.sendSystemMessage(Component.literal("\n[ArkCraft]" +
                        "你已将玩家"+ targetPlayer.getName().getString() +
                        "的 " + type + " 进度设为: " + newProgress)
                .withStyle(ChatFormatting.GREEN));

        targetPlayer.sendSystemMessage(Component.literal("\n[ArkCraft]" +
                "你的 " + type +" 进度已被管理员设为: "+ newProgress)
                .withStyle(ChatFormatting.GREEN));

        return Command.SINGLE_SUCCESS;
    }
}