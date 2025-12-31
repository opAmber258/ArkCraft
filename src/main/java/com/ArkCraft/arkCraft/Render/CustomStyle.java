package com.ArkCraft.arkCraft.Render;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Rarity;

public class CustomStyle {

    public static Style styleOfDamage = Style.EMPTY.applyFormat(ChatFormatting.RED);

    public static Style styleOfMana = Style.EMPTY.applyFormat(ChatFormatting.LIGHT_PURPLE);


    public static Style styleOfSpeed = Style.EMPTY.applyFormat(ChatFormatting.AQUA);

    public static Style styleOfHealth = Style.EMPTY.applyFormat(ChatFormatting.GREEN);

    public static Style styleOfDefence = Style.EMPTY.applyFormat(ChatFormatting.GRAY);

    public static Style styleOfIce = Style.EMPTY.withColor(TextColor.parseColor("#7cfdfc"));

    public static Style test = Style.EMPTY.withColor(TextColor.parseColor("#35cb7f"));

    public static Rarity Rough = Rarity.create("Rough", style -> {
        return style.applyFormat(ChatFormatting.GRAY);
    });
}
