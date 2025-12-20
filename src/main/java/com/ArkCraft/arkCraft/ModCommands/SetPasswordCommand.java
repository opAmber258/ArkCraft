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

public class SetPasswordCommand implements Command<CommandSourceStack> {

    public static SetPasswordCommand instance = new SetPasswordCommand();


    @Override
    public int run(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();


        String newPass = StringArgumentType.getString(ctx, "password");
        String repeat  = StringArgumentType.getString(ctx, "repeat_password");

        if (!Password.getLoginStatus(player)) {
            player.sendSystemMessage(Component.literal("你还没登录呢!")
                    .withStyle(ChatFormatting.GREEN));
            return SINGLE_SUCCESS;
        }

        if (!Password.hasPassword(player)) {
            player.sendSystemMessage(Component.literal("你还没注册呢!")
                    .withStyle(ChatFormatting.GREEN));
            return SINGLE_SUCCESS;
        }

        if (newPass == null || newPass.isEmpty()) {
            player.sendSystemMessage(Component.literal("新密码不能为空!")
                    .withStyle(ChatFormatting.GREEN));
            return SINGLE_SUCCESS;
        }

        if (!newPass.equals(repeat)) {
            player.sendSystemMessage(Component.literal("前后密码不一致!")
                    .withStyle(ChatFormatting.GREEN));
            return SINGLE_SUCCESS;
        }

        Password.setPassword(player, newPass);
        player.sendSystemMessage(Component.literal("修改成功!")
                .withStyle(ChatFormatting.GREEN));
        return SINGLE_SUCCESS;
    }

    /*
    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        ServerPlayer player = context.getSource().getPlayerOrException();
        String password = StringArgumentType.getString(context, "password");
        String repeat_password = StringArgumentType.getString(context, "repeat_password");
        if (Password.getLoginStatus(player)) {
            if (password.equals(repeat_password)) {
                player.sendSystemMessage(Component.literal("修改成功! 密码为 [" + password + "]")
                        .withStyle(ChatFormatting.GREEN));
                Password.setPassword(player, password);
            }else {
                player.sendSystemMessage(Component.literal("前后密码不一致! 你输入的是 [" +
                                password + "] 和 ["
                                + repeat_password + "] ! ")
                        .withStyle(ChatFormatting.GREEN));
            }
        }else {
            player.sendSystemMessage(Component.literal("你还没登录呢!")
                    .withStyle(ChatFormatting.GREEN));
        }

        return SINGLE_SUCCESS;
    }

     */
}
