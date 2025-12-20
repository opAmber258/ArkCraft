package com.ArkCraft.arkCraft.Sounds;


import com.ArkCraft.arkCraft.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    private static final DeferredRegister<SoundEvent> SOUND =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Utils.MOD_ID);

    public static final RegistryObject<SoundEvent> EP16 = registryObject("ep16");

    public static void register(IEventBus eventBus) {
        SOUND.register(eventBus);
    }

    public static RegistryObject<SoundEvent> registryObject(String name) {
        return SOUND.register(name, () ->
                SoundEvent.createVariableRangeEvent(new ResourceLocation(Utils.MOD_ID, name)));
    }
}
