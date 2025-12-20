package com.ArkCraft.arkCraft.Events.AttributesRegistry;



import com.ArkCraft.arkCraft.Entities.NPC.*;
import com.ArkCraft.arkCraft.Utils;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributesRegistry {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PLAYER_NPC.get(), TestNPC.createAttributes().build());
        event.put(ModEntities.SKY_MICO.get(), SkyMico.createAttributes().build());
        event.put(ModEntities.VERY_H.get(), VeryH.createAttributes().build());
        event.put(ModEntities.MOT.get(), Mot.createAttributes().build());
    }
}
