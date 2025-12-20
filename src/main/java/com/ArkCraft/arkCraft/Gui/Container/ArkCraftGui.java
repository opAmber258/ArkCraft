package com.ArkCraft.arkCraft.Gui.Container;

import com.ArkCraft.arkCraft.Utils;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArkCraftGui {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister
            .create(ForgeRegistries.MENU_TYPES, Utils.MOD_ID);

    public static final RegistryObject<MenuType<DrSBagContainerMenu>> dr_s_bag_container = MENU_TYPES
            .register("dr_s_bag", ()-> IForgeMenuType.create(
                    ((windowId, inv, data) -> new DrSBagContainerMenu(inv, windowId,
                            data.readBlockPos()))));
}
