package com.ArkCraft.arkCraft.ModCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SetHomeCommand implements Command<CommandSourceStack>{


    public static double p_x = 0;
    public static double p_y = 0;
    public static double p_z = 0;

    public static SetHomeCommand instance = new SetHomeCommand();

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player player = context.getSource().getPlayer();
        p_x = player.getX();
        p_y = player.getY();
        p_z = player.getZ();
        Level p_level = player.level();
        player.sendSystemMessage(Component.literal("设置完成！坐标为 x " + p_x
        + " y " + p_y + " z " + p_z));
        return 0;

    }
}