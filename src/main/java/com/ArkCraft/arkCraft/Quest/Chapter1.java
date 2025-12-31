package com.ArkCraft.arkCraft.Quest;

import com.ArkCraft.arkCraft.Compute.NumberCompute;
import com.ArkCraft.arkCraft.Items.EquipMaterial;
import com.ArkCraft.arkCraft.Items.ModItems;
import com.ArkCraft.arkCraft.Networking.ModNetworking;
import com.ArkCraft.arkCraft.Networking.SoundS2CPacket;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Chapter1 {

    public static void Mainms(Player player) {
        switch (Quest.getProgress(Quest.MAIN_QUEST, player)) {
            case 0 -> Ms1run(player);
            case 1 -> Ms2run(player);
            case 2 -> Ms3run(player);
        }
    }

    public static void Ms1run(Player player) {
        switch (Quest.getProgress(Quest.CHAPTER1, player)) {

            case 0 -> player.sendSystemMessage(Component.literal(
                    "\n[结月村村长]" +
                            player.getName().getString() +
                            "，咱们村里缺衣裳了，赶紧去给我搞点布匹"));

            case 1 -> player.sendSystemMessage(Component.literal(
                            "\n[结月村村长]" +
                                    "村南边山脚下有几条狼，你去把他们的皮扒了带回来"));

            case 2 -> player.sendSystemMessage(Component.literal(
                    "\n[结月村村长]" +
                            "对了，俺拾了一根撬棍，你拿上"));

            case 3 -> {
                player.sendSystemMessage(Component.literal((
                            "\n[ArkCraft]"+
                                    "获得物品 老旧的撬棍"))
                    .withStyle(ChatFormatting.GREEN));
                player.addItem(new ItemStack(ModItems.Crowbar.get()));
            }

            case 4 -> player.sendSystemMessage(Component.literal((
                            "\n[ArkCraft]" +
                                    "任务目标：上交 0/10 个狼皮"))
                    .withStyle(ChatFormatting.GREEN));

            case 5 -> {
                ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
                int amount = itemStack.getCount();
                int progress = Quest.getProgress(Quest.QUESTITEM, player);
                if (itemStack.is(ModItems.IceWolfSkin.get())) {
                    if (progress == 0) {
                        if (amount < 10) {
                            Quest.setProgress(Quest.QUESTITEM, player, amount);
                            itemStack.shrink(amount);
                            player.sendSystemMessage(Component.literal((
                                            "\n[ArkCraft]" +
                                                    "任务目标更新：上交 " + Quest.getProgress(Quest.QUESTITEM, player)
                                                    + "/10" + " 个狼皮"))
                                    .withStyle(ChatFormatting.GREEN));
                        } else {
                            itemStack.shrink(10);
                            player.sendSystemMessage(Component.literal((
                                            "\n[ArkCraft]" +
                                                    "任务完成，继续右键npc以进行对话"))
                                    .withStyle(ChatFormatting.GREEN));
                            Quest.setProgress(Quest.QUESTITEM, player, 0);
                            //Quest.addMainProgress(player, 1);
                            Quest.addProgress(Quest.MAIN_QUEST, player, 1);
                            //Quest.setChapter1Progress(player, 0);
                            Quest.setProgress(Quest.CHAPTER1, player, 0);
                        }
                    }
                    else if (amount + progress >= 10){
                        itemStack.shrink(10 - progress);
                        player.sendSystemMessage(Component.literal((
                                        "\n[ArkCraft]" +
                                                "任务完成，继续右键npc以进行对话"))
                                .withStyle(ChatFormatting.GREEN));
                        Quest.setProgress(Quest.QUESTITEM, player, 0);
                        //Quest.addMainProgress(player, 1);
                        Quest.addProgress(Quest.MAIN_QUEST, player, 1);
                        //Quest.setChapter1Progress(player, 0);
                        Quest.setProgress(Quest.CHAPTER1, player, 1);
                    } else {
                        Quest.setProgress(Quest.QUESTITEM, player, amount + progress);
                        itemStack.shrink(amount);
                        player.sendSystemMessage(Component.literal((
                                        "\n[ArkCraft]" +
                                                "任务目标更新：上交 " + Quest.getProgress(Quest.QUESTITEM, player)
                                                + "/10" + " 个狼皮"))
                                .withStyle(ChatFormatting.GREEN));
                    }
                }else {
                    player.sendSystemMessage(Component.literal((
                                    "\n[ArkCraft]" +
                                            "任务目标：上交 " + progress +"/10" + " 个狼皮"))
                            .withStyle(ChatFormatting.GREEN));
                }
            }
        }
        if (Quest.getProgress(Quest.CHAPTER1, player) < 5) {
            //Quest.addChapter1Progress(player, 1);
            Quest.addProgress(Quest.CHAPTER1, player, 1);
        }
        ModNetworking.sendToClient(new SoundS2CPacket
                (1, SoundSource.MASTER), (ServerPlayer) player);
    }
    public static void Ms2run(Player player) {
        switch (Quest.getProgress(Quest.CHAPTER1, player)) {

            case 0 -> player.sendSystemMessage(Component.literal(
                    "\n[结月村村长]" +
                            "做的不错嘛小伙子，剩下的布匹你自己拿回家做衣裳吧"));

            case 1 -> player.sendSystemMessage(Component.literal(
                    "\n[结月村村长]" +
                            "对了，不能让你白干活。这是我在路边捡到的玩意"));

            case 2 -> {
                player.sendSystemMessage(Component.literal((
                                "\n[ArkCraft]" +
                                        "获得物品 PRTS终端"))
                        .withStyle(ChatFormatting.GREEN));
                player.addItem(new ItemStack(ModItems.PRTS.get()));
            }

            case 3 -> player.sendSystemMessage(Component.literal(
                    "\n[结月村村长]" +
                            "这玩意我研究不明白，你拿去看看吧。"));

            case 4 -> player.sendSystemMessage(Component.literal(
                    "\n[结月村村长]" +
                            "对了，剩下的狼皮可以在村里的工作台做成装备，和原版一样"));

            case 5 -> player.sendSystemMessage(Component.literal((
                    "\n[ArkCraft]" +
                            "任务目标：装备至少一件狼皮装备，装备后继续对话"))
                    .withStyle(ChatFormatting.GREEN));
            case 6 -> {
                if (NumberCompute.getAllEquipMaterialQuantity(player, Utils.equipMaterial)
                        .getOrDefault(EquipMaterial.IceWolfSkin, 0) > 0) {
                    player.sendSystemMessage(Component.literal(
                            "\n[结月村村长]" +
                                    "不赖嘛，你去村里最烂那栋房子里面找天漠，看看他有什么需要帮忙的"));
                } else {
                    player.sendSystemMessage(Component.literal((
                                    "\n[ArkCraft]" +
                                            "任务目标：装备至少一件狼皮装备，装备后继续对话"))
                            .withStyle(ChatFormatting.GREEN));
                }
            }
        }
        if (Quest.getProgress(Quest.CHAPTER1, player) < 6) {
            Quest.addProgress(Quest.CHAPTER1, player, 1);
            //Quest.addChapter1Progress(player, 1);
        }

    }

    public static void Ms3run(Player player) {
        player.sendSystemMessage(Component.literal((
                        "\n[ArkCraft]"+
                                "暂未更新"))
                .withStyle(ChatFormatting.GREEN)
                .withStyle(ChatFormatting.UNDERLINE));
    }
}
