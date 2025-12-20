package com.ArkCraft.arkCraft.Items.Armors;

import com.ArkCraft.arkCraft.Items.EquipMaterial;
import com.ArkCraft.arkCraft.Items.ModItems;
import com.ArkCraft.arkCraft.Render.CustomStyle;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.ArrayList;
import java.util.List;

public class WolfSkinArmor extends ArkCraftArmors{

    public WolfSkinArmor(ArmorMaterial armorMaterial, ArmorItem.Type type) {
        super(armorMaterial, type, new Item.Properties().rarity(CustomStyle.Rough));
        Utils.equipMaterial.put(this, EquipMaterial.IceWolfSkin);
        switch (type) {
            case HELMET -> {
                Utils.maxHealth.put(this, 1d);
                Utils.attackDamage.put(this, 0.5d);
            }

            case CHESTPLATE -> {
                Utils.maxHealth.put(this, 2d);
                Utils.defence.put(this, 1d);
            }

            case LEGGINGS -> {
                Utils.maxHealth.put(this, 1d);
                Utils.defence.put(this, 1d);
            }

            case BOOTS -> {
                Utils.movementSpeedCommon.put(this, 0.03);
                Utils.maxHealth.put(this, 1d);
            }
        }
    }

    @Override
    public List<Component> getArkArmorDescription(ItemStack stack) {
        List<Component> components = new ArrayList<>();
        String type = "null";
        if (stack.is(ModItems.Wolf_Helmet.get())) {
            type = "帽子";
        }
        if (stack.is(ModItems.Wolf_Chest.get())) {
            type = "衣服";
        }
        if (stack.is(ModItems.Wolf_Leggings.get())) {
            type = "裤子";
        }
        if (stack.is(ModItems.Wolf_Boots.get())) {
            type = "鞋子";
        }
        components.add(Component.literal("狼皮制成的" + type)
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        components.add(Component.literal("因为是自己做的，穿着很合身")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        return components;
    }
}
