package com.ArkCraft.arkCraft.Events.Client;



import com.ArkCraft.arkCraft.Sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class SoundsEvent {

    public static int Sounds = -1;
    @SubscribeEvent
    public static void Sounds(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();
        if (level.isClientSide && Sounds != -1 && event.player.equals(Minecraft.getInstance().player)) {
            switch (Sounds) {
                case 0 -> {
                    player.playSound(ModSounds.EP16.get());
                    player.sendSystemMessage(Component.literal("正在播放: 反常光谱-塞壬唱片-MSR,Camilo Forero"));
                }
                case 1 -> player.playSound(SoundEvents.CAT_AMBIENT);
            }
            Sounds = -1;
        }
    }
}
