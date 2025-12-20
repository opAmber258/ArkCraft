package com.ArkCraft.arkCraft.Quest;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class Quest {


    public static final String MAIN_QUEST = "main_quest";

    public static final String CHAPTER1  = "chapter1";

    public static final String QUESTITEM  = "quest_item";


    public static void setProgress(String type,Player player, int value) {
        if (player.level().isClientSide() || !(player instanceof ServerPlayer serverPlayer)) return;
        CompoundTag nbt = serverPlayer.getPersistentData();
        nbt.putInt(type, value);
    }

    public static int getProgress(String type, Player player) {
        if (player.level().isClientSide()) return 0;
        return player.getPersistentData().getInt(type);
    }

    public static void addProgress(String type, Player player, int add) {
        if (player.level().isClientSide()) return;
        CompoundTag nbt = player.getPersistentData();
        int current = nbt.getInt(type); // 读当前进度
        nbt.putInt(type, current + add); // 写新进度
    }
}
