package com.ArkCraft.arkCraft.Entities.NPC;


import com.ArkCraft.arkCraft.Utils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Utils.MOD_ID);

    public static final RegistryObject<EntityType<TestNPC>> PLAYER_NPC = ENTITIES.register(
            "player_npc",
            () -> EntityType.Builder.of(
                            TestNPC::new,
                            MobCategory.CREATURE
                    )
                    .sized(0.6F, 1.8F)
                    .setCustomClientFactory((spawnEntity, level) -> new TestNPC(ModEntities.PLAYER_NPC.get(), level))
                    .build(Utils.MOD_ID + ":player_npc")
    );

    public static final RegistryObject<EntityType<SkyMico>> SKY_MICO = ENTITIES.register(
            "sky_mico",
            () -> EntityType.Builder.of(
                            SkyMico::new,
                            MobCategory.CREATURE
                    )
                    .sized(0.6F, 1.8F)
                    .setCustomClientFactory((spawnEntity, level) ->
                            new SkyMico(ModEntities.PLAYER_NPC.get(), level))
                    .build(Utils.MOD_ID + ":sky_mico")
    );

    public static final RegistryObject<EntityType<VeryH>> VERY_H = ENTITIES.register(
            "very_h",
            () -> EntityType.Builder.of(
                            VeryH::new,
                            MobCategory.CREATURE
                    )
                    .sized(0.6F, 1.8F)
                    .setCustomClientFactory((spawnEntity, level) ->
                            new VeryH(ModEntities.PLAYER_NPC.get(), level))
                    .build(Utils.MOD_ID + ":very_h")
    );

    public static final RegistryObject<EntityType<Mot>> MOT = ENTITIES.register(
            "mot",
            () -> EntityType.Builder.of(
                            Mot::new,
                            MobCategory.CREATURE
                    )
                    .sized(0.6F, 1.8F)
                    .setCustomClientFactory((spawnEntity, level) ->
                            new Mot(ModEntities.PLAYER_NPC.get(), level))
                    .build(Utils.MOD_ID + ":mot")
    );
}
