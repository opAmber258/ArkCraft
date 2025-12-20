package com.ArkCraft.arkCraft.ModCommands;


import com.ArkCraft.arkCraft.Utils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommandHandler {
    @SubscribeEvent
    public static void onServerStarting(RegisterCommandsEvent event) {

        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal(Utils.MOD_ID)
                .then(Commands.literal("info").executes(InfoCommand.instance))
                .then(Commands.literal("home").executes(HomeCommand.instance))
                .then(Commands.literal("sethome").executes(SetHomeCommand.instance))
                .then(Commands.literal("menu").executes(MenuCommand.instance))
                .then(Commands.literal("register")
                        .then(Commands.argument("password", StringArgumentType.string())
                                .then(Commands.argument("repeat_password", StringArgumentType.string())
                                        .executes(RegisterCommand.instance))))
                .then(Commands.literal("login")
                        .then(Commands.argument("password", StringArgumentType.string())
                                .executes(LoginCommand.instance)))
                .then(Commands.literal("setpassword")
                        .then(Commands.argument("password", StringArgumentType.string())
                                .then(Commands.argument("repeat_password", StringArgumentType.string())
                                        .executes(SetPasswordCommand.instance))))
                .then(Commands.literal("progress")
                .then(Commands.literal("get")
                        .then(Commands.argument("type", StringArgumentType.string())
                                .executes(ProgressGetCommand.instance)))
                        .then(Commands.literal("set")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("type", StringArgumentType.string())
                                        .then(Commands.argument("progress",
                                                        IntegerArgumentType.integer(0))
                                                .executes(context ->
                                                        ProgressSetCommand.instance.run(context))))))));
    }
}