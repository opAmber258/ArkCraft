package com.ArkCraft.arkCraft.Networking;

import com.ArkCraft.arkCraft.Events.Client.SoundsEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;


import java.util.function.Supplier;

public class SoundS2CPacket {
    private final int flag;

    public SoundS2CPacket(int flag,SoundSource source) {
        this.flag = flag;
    }

    public SoundS2CPacket(FriendlyByteBuf buf) {
        this.flag = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.flag);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            SoundsEvent.Sounds = flag;
        });
        return true;
    }
}
