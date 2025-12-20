package com.ArkCraft.arkCraft.Networking;

import com.ArkCraft.arkCraft.Events.Client.ClientPlayerTick;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ScreenS2CPacket {
    private final int type;
    public ScreenS2CPacket(int num)
    {
        type = num;
    }
    public ScreenS2CPacket(FriendlyByteBuf buf)
    {
        type = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(type);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork( ()->{
            ClientPlayerTick.screenSetFlag = this.type;
        });
        return true;
    }
}