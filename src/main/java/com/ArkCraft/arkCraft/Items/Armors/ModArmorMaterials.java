package com.ArkCraft.arkCraft.Items.Armors;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial {

    WolfMaterialBoots("wolf_material", 0, 0, 15, 0.1F, 0.0f,SoundEvents.ARMOR_EQUIP_LEATHER),
    WolfMaterialLeggings("wolf_material", 0, 0, 15, 0.1F, 0.0f, SoundEvents.ARMOR_EQUIP_LEATHER),
    WolfMaterialChest("wolf_material", 0, 0, 15, 0.1F, 0.0f, SoundEvents.ARMOR_EQUIP_LEATHER),
    WolfMaterialHelmet("wolf_material", 0, 0, 15, 0.1F, 0.0f, SoundEvents.ARMOR_EQUIP_LEATHER);

    private final int Durability;
    private final int Defense;
    private final int EnchantmenValue;
    private final float KnockbackResistance;
    private final float Toughness;
    private final String name;
    private final SoundEvent sound;

    ModArmorMaterials(String name, int IMnum1, int IMnum2, int IMnum3, float IMnum4, float IMnum5, SoundEvent sound) {
        this.Durability = IMnum1;
        this.Defense = IMnum2;
        this.EnchantmenValue = IMnum3;
        this.KnockbackResistance = IMnum4;
        this.Toughness = IMnum5;
        this.name = name;
        this.sound = sound;
    }



    @Override
    public int getDurabilityForType(ArmorItem.Type p_266807_) {
        return this.Durability;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type p_267168_) {
        return this.Defense;
    }

    @Override
    public int getEnchantmentValue() {
        return this.EnchantmenValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.Toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.KnockbackResistance;
    }
}
