package com.ArkCraft.arkCraft.Blocks;

import com.ArkCraft.arkCraft.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Utils.MOD_ID);

    public static final RegistryObject<Block> DrSBag = BLOCKS.register("dr_s_bag",
            () -> new DrSBag(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, Utils.MOD_ID);

    public static final RegistryObject<BlockEntityType<DrSBagBlockEntity>> drsbagentity = BLOCK_ENTITIES
            .register("dr_s_bag", () -> BlockEntityType.Builder
                    .of(DrSBagBlockEntity::new, DrSBag.get()) // 此处 DrSBag.get() 必须是已注册的方块实例
                    .build(null));
}
