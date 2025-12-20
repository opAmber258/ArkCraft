package com.ArkCraft.arkCraft.Networking;

import com.ArkCraft.arkCraft.Events.Login.ClientLoginCache;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class LoginStatusS2CPacket {

    private final boolean status;

    public LoginStatusS2CPacket(boolean status) {
        this.status = status;
    }

    public LoginStatusS2CPacket(FriendlyByteBuf buf) {
        this.status = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(status);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> ClientLoginCache.loginStatus = status);
        ctx.setPacketHandled(true);
    }
}
