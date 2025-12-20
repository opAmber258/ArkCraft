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

public class LoginCommand implements Command<CommandSourceStack> {

    public static LoginCommand instance = new LoginCommand();


    @Override
    public int run(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        String input = StringArgumentType.getString(ctx, "password");

        if (Password.getLoginStatus(player)) {
            player.sendSystemMessage(Component.literal("你已经登录过了!")
                    .withStyle(ChatFormatting.GREEN));
            return SINGLE_SUCCESS;
        }

        if (!Password.hasPassword(player)) {
            player.sendSystemMessage(Component.literal("你还没注册呢! 请使用 /arkcraft register")
                    .withStyle(ChatFormatting.GREEN));
            return SINGLE_SUCCESS;
        }

        if (input == null || input.isEmpty()) {
            player.sendSystemMessage(Component.literal("请输入密码: /arkcraft login <密码>")
                    .withStyle(ChatFormatting.GREEN));
            return SINGLE_SUCCESS;
        }

        if (Password.verifyPassword(player, input)) {
            Password.setLoginStatus(player, true);
            player.sendSystemMessage(Component.literal("登录成功!")
                    .withStyle(ChatFormatting.GREEN));
        } else {
            player.sendSystemMessage(Component.literal("密码错误!")
                    .withStyle(ChatFormatting.GREEN));
        }

        return SINGLE_SUCCESS;
    }
    /*
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {

        ServerPlayer player = commandContext.getSource().getPlayerOrException();
        String password = StringArgumentType.getString(commandContext, "password");
        if (!Password.getLoginStatus(player)) {
            if (!password.isEmpty()){
                if (password.equals(Password.getPassword(player))) {
                    player.sendSystemMessage(Component.literal("登录成功!")
                            .withStyle(ChatFormatting.GREEN));
                    Password.setLoginStatus(player, true);
                }else {
                    player.sendSystemMessage(Component.literal("密码错误! ")
                            .withStyle(ChatFormatting.GREEN));
                }
            }else player.sendSystemMessage(Component.literal("你还没注册呢! ")
                    .withStyle(ChatFormatting.GREEN));
        } else player.sendSystemMessage(Component.literal("你已经登陆过了! ")
                .withStyle(ChatFormatting.GREEN));

        return SINGLE_SUCCESS;
    }

     */
}
