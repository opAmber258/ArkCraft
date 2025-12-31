package com.ArkCraft.arkCraft;

import com.ArkCraft.arkCraft.Blocks.ModBlocks;
import com.ArkCraft.arkCraft.Events.AttributesRegistry.AttributesRegistry;
import com.ArkCraft.arkCraft.Entities.NPC.ModEntities;
import com.ArkCraft.arkCraft.Items.ModCreativeTab;
import com.ArkCraft.arkCraft.Items.ModItems;
import com.ArkCraft.arkCraft.Gui.Container.ArkCraftGui;
import com.ArkCraft.arkCraft.Networking.ModNetworking;

import com.ArkCraft.arkCraft.Sounds.ModSounds;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;



@Mod(Utils.MOD_ID)
@Mod.EventBusSubscriber
public class Main {

    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ENTITIES.register(bus);
        ArkCraftGui.MENU_TYPES.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModCreativeTab.register(bus);
        ModSounds.register(bus);
        bus.addListener(this::AddItemToTab);
        bus.addListener(this::SetUp);
        bus.register(AttributesRegistry.class);
    }

    private void SetUp(FMLCommonSetupEvent event) {
        ModNetworking.register();
    }
    /*
    public static boolean isArkCraftMob(Mob mob) {
        return (mob.getDisplayName().getString().contains("Lv."));
    }

     */

    private void AddItemToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(ModCreativeTab.FIRST_TAB.getKey())) {
            event.accept(ModItems.MyFirstItem.get().getDefaultInstance());
            event.accept(ModItems.CyanStoneSword.get().getDefaultInstance());
            event.accept(ModItems.CyanStone.get().getDefaultInstance());
        } else if (event.getTabKey().equals(ModCreativeTab.FOODS.getKey())) {
            event.accept(ModItems.AmberLarva.get().getDefaultInstance());
            event.accept(ModItems.SkyMico.get().getDefaultInstance());
            event.accept(ModItems.TieTouRen.get().getDefaultInstance());
        } else if (event.getTabKey().equals(ModCreativeTab.TOOLS.getKey())) {
            event.accept(ModItems.RecallIdol.get().getDefaultInstance());
        } else if (event.getTabKey().equals(ModCreativeTab.ARKNIGHTS.getKey())) {
            event.accept(ModItems.OriginitePrime.get().getDefaultInstance());
            event.accept(ModItems.Orundum.get().getDefaultInstance());
            event.accept(ModItems.DrSBagItem.get().getDefaultInstance());
            event.accept(ModItems.HeadhuntingPermit.get().getDefaultInstance());
            event.accept(ModItems.TenrollHeadhuntingPermit.get().getDefaultInstance());
            event.accept(ModItems.Saki.get().getDefaultInstance());
            event.accept(ModItems.TwelveF.get().getDefaultInstance());
            event.accept(ModItems.PRTS.get().getDefaultInstance());
            event.accept(ModItems.IceWolfSkin.get().getDefaultInstance());
            event.accept(ModItems.Wolf_Helmet.get().getDefaultInstance());
            event.accept(ModItems.Wolf_Chest.get().getDefaultInstance());
            event.accept(ModItems.Wolf_Leggings.get().getDefaultInstance());
            event.accept(ModItems.Wolf_Boots.get().getDefaultInstance());
            event.accept(ModItems.Crowbar.get().getDefaultInstance());
            event.accept(ModItems.Branch_Staff.get().getDefaultInstance());
        }
    }
}
