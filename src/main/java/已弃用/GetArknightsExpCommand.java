/*package com.MyMod.myMod.ModCommands;

import 已弃用.ArknightsExpProvider;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;


public class GetArknightsExpCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("arknights_exp")
                .executes((context) -> {
                    context.getSource().getPlayer().getCapability(ArknightsExpProvider.ARKNIGHTS_EXP_CAPABILITY)
                            .isPresent((exp) -> {
                                context.getSource().sendSuccess(() ->
                                        Component.literal("exp" + exp.getExp()), false);
                            });
                    return 0;
                }));;
    }



    /*    @Override
    public  GetArknightsExpCommand instance = new GetArknightsExpCommand();

    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player player = context.getSource().getPlayer();
        player.sendSystemMessage(Component.literal((String.valueOf(ArknightsExp.getExp()))));
        return 0;
    }
}
*/
