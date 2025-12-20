package com.ArkCraft.arkCraft.ModCommands;

import com.ArkCraft.arkCraft.Events.Login.Password;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class RegisterCommand implements Command<CommandSourceStack> {

    public static RegisterCommand instance = new RegisterCommand();

    @Override
    public int run(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        String password = StringArgumentType.getString(ctx, "password");
        String repeat = StringArgumentType.getString(ctx, "repeat_password");

        if (Password.hasPassword(player)) {
            player.sendSystemMessage(Component.literal("你已经注册过了!")
                    .withStyle(ChatFormatting.GREEN));
            return Command.SINGLE_SUCCESS;
        }

        if (password == null || password.isEmpty()) {
            player.sendSystemMessage(Component.literal("密码不能为空!")
                    .withStyle(ChatFormatting.GREEN));
            return Command.SINGLE_SUCCESS;
        }

        if (!password.equals(repeat)) {
            player.sendSystemMessage(Component.literal("前后密码不一致!")
                    .withStyle(ChatFormatting.GREEN));
            return Command.SINGLE_SUCCESS;
        }

        // ✅ 写入 salt+hash
        Password.setPassword(player, password);
        Password.setLoginStatus(player, true);
        player.sendSystemMessage(Component.literal("注册成功!").withStyle(ChatFormatting.GREEN));
        return Command.SINGLE_SUCCESS;
    }
}

    /*
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {

        ServerPlayer player = commandContext.getSource().getPlayerOrException();
        String password = StringArgumentType.getString(commandContext, "password");
        String repeat_password = StringArgumentType.getString(commandContext, "repeat_password");
        if (Password.getPassword(player).isEmpty()) {
            if (password.equals(repeat_password)) {
                player.sendSystemMessage(Component.literal("注册成功! 密码为 [" + password +"]")
                        .withStyle(ChatFormatting.GREEN));
                Password.setPassword(player, password);
                Password.setLoginStatus(player, true);
            }else {
                player.sendSystemMessage(Component.literal("前后密码不一致! 你输入的是 [" +
                                password + "] 和 ["
                                + repeat_password  +"] ! ")
                        .withStyle(ChatFormatting.GREEN));
            }
        }else player.sendSystemMessage(Component.literal("你已经注册过了! ")
                .withStyle(ChatFormatting.GREEN));

        return Command.SINGLE_SUCCESS;
    }

     */

