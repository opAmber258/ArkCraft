package com.ArkCraft.arkCraft.Items;

import com.ArkCraft.arkCraft.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.
            create(Registries.CREATIVE_MODE_TAB, Utils.MOD_ID);

    public static RegistryObject<CreativeModeTab> FIRST_TAB = CREATIVE_MODE_TAB.register("moditem1",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MyFirstItem.get())).
                    title(Component.literal("我是创造模式物品栏1")).build());
        //每个物品栏的注册都将使用一个这一格式的语句，与物品类似

    public static RegistryObject<CreativeModeTab> FOODS = CREATIVE_MODE_TAB.register("foods",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AmberLarva.get())).
                    title(Component.literal("食物")).build());
        //第二个物品栏的注册
    public static RegistryObject<CreativeModeTab> TOOLS = CREATIVE_MODE_TAB.register("tools",() ->
                CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RecallIdol.get())).
                        title(Component.literal("工具")).build());

    public static RegistryObject<CreativeModeTab> ARKNIGHTS = CREATIVE_MODE_TAB.register("arknights",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.OriginitePrime.get())).
                    title(Component.literal("ArkCraft")).build());
    //每个物品栏的注册都将使用一个这一格式的语句，与物品类似
    //然后去主类把物品注册进去


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
