package com.ArkCraft.arkCraft.Items;

import com.ArkCraft.arkCraft.Items.ArkCraftItems.*;
import com.ArkCraft.arkCraft.Items.Armors.ModArmorMaterials;
import com.ArkCraft.arkCraft.Items.Armors.WolfSkinArmor;
import com.ArkCraft.arkCraft.Items.Items.*;
import com.ArkCraft.arkCraft.Items.Weapons.Crowbar;
import com.ArkCraft.arkCraft.Items.Tiers.CyanStoneTier;
import com.ArkCraft.arkCraft.Items.Weapons.BranchStaff;
import com.ArkCraft.arkCraft.Render.CustomStyle;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.ArkCraft.arkCraft.Blocks.ModBlocks.DrSBag;

@Mod.EventBusSubscriber(modid = "mymod", bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Utils.MOD_ID);

    public static final RegistryObject<Item> MyFirstItem = ITEMS.register("my_first_item",
            () -> new MyFirstItem(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> OriginitePrime = ITEMS.register("originite_prime",
            () -> new OriginitePrime(new Item.Properties()));

    public static final RegistryObject<Item> Orundum = ITEMS.register("orundum",
            () -> new Orundum(new Item.Properties()));

    public static final RegistryObject<Item> HeadhuntingPermit = ITEMS.register("headhunting_permit",
            () -> new HeadhuntingPermit(new Item.Properties()));

    public static final RegistryObject<Item> TenrollHeadhuntingPermit = ITEMS
            .register("tenroll_headhunting_permit",
            () -> new TenrollHeadhuntingPermit((new Item.Properties())));

    public static final RegistryObject<Item> Saki = ITEMS.register("saki",
            () -> new Saki(new Item.Properties()));

    public static final RegistryObject<Item> TwelveF = ITEMS.register("twelve_f",
            () -> new TwelveF(new Item.Properties()));



    public static final RegistryObject<Item> AmberLarva = ITEMS.register("amber_larva",
            () -> new AmberLarva(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(6) // 设置饱腹值为6
                    .saturationMod(1.2F) // 设置饱和度为1.2F
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 0),
                            10.F)
                    .alwaysEat()
                    .build())));

    public static final RegistryObject<Item> SkyMico = ITEMS.register("sky_mico",
            () -> new SkyMico(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4) // 设置饱腹值为6
                            .saturationMod(1.2F) // 设置饱和度为1.2F
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2145, 1),
                                    10.F)
                            .alwaysEat()
                            .build())));

    public static final RegistryObject<Item> TieTouRen = ITEMS.register("tie_tou_ren",
            () -> new TieTouRen(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4) // 设置饱腹值为6
                            .saturationMod(1.2F) // 设置饱和度为1.2F
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2145, 1),
                                    10.F)
                            .alwaysEat()
                            .build())));


    public static final RegistryObject<Item> RecallIdol = ITEMS.register("recall_idol",
            () -> new RecallIdol(new Item.Properties()));

    public static final RegistryObject<Item> CyanStoneSword = ITEMS.register("cyan_stone_sword",
            () -> new SwordItem(CyanStoneTier.CYANSTONE, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> CyanStone = ITEMS.register("cyan_stone",
            () -> new CyanStone(new Item.Properties()));

    public static final RegistryObject<Item> DrSBagItem = ITEMS.register("dr_s_bag",
            () -> new BlockItem(DrSBag.get(), new Item.Properties()));

    public static final RegistryObject<Item> Crowbar = ITEMS.register("crowbar",
            () -> new Crowbar(new Item.Properties().rarity(CustomStyle.Rough), 5d, -2.8f));

    public static final RegistryObject<Item> PRTS = ITEMS.register("prts",
            () -> new PRTS(new Item.Properties()));

    public static final RegistryObject<Item> IceWolfSkin = ITEMS.register("icewolf_skin",
            () -> new IceWolfSkin(new Item.Properties().rarity(CustomStyle.Rough)));


    public static final RegistryObject<Item> Wolf_Helmet = ITEMS.register("wolf_helmet",
            () -> new WolfSkinArmor(ModArmorMaterials.WolfMaterialHelmet, ArmorItem.Type.HELMET));
    public static final RegistryObject<Item> Wolf_Chest = ITEMS.register("wolf_chest",
            () -> new WolfSkinArmor(ModArmorMaterials.WolfMaterialChest, ArmorItem.Type.CHESTPLATE));
    public static final RegistryObject<Item>  Wolf_Leggings = ITEMS.register("wolf_leggings",
            () -> new WolfSkinArmor(ModArmorMaterials.WolfMaterialLeggings, ArmorItem.Type.LEGGINGS));
    public static final RegistryObject<Item>  Wolf_Boots = ITEMS.register("wolf_boots",
            () -> new WolfSkinArmor(ModArmorMaterials.WolfMaterialBoots, ArmorItem.Type.BOOTS));


    public static final RegistryObject<Item> Branch_Staff = ITEMS.register("branch_staff",
            () -> new BranchStaff(new Item.Properties().rarity(CustomStyle.Rough)));


}
