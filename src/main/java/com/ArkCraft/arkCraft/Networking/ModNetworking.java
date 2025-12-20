package com.ArkCraft.arkCraft.Networking;


import com.ArkCraft.arkCraft.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;



public class ModNetworking {
    private static SimpleChannel INSTANCE;
    private static int packetID = 0;
    private static int id()
    {
        return packetID++;//完整流程：客户端触发发包→服务器接收后更新 Utils.Num→服务器回发新数值→客户端接收并使用新数值
    }
    public static void register()
    {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Utils.MOD_ID,"messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(UtilsC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UtilsC2SPacket::new)
                .encoder(UtilsC2SPacket::toBytes)
                .consumerMainThread(UtilsC2SPacket::handle)
                .add();

        net.messageBuilder(ScreenS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ScreenS2CPacket::new)
                .encoder(ScreenS2CPacket::toBytes)
                .consumerMainThread(ScreenS2CPacket::handle)
                .add();

        net.messageBuilder(SoundS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SoundS2CPacket::new)
                .encoder(SoundS2CPacket::toBytes)
                .consumerMainThread(SoundS2CPacket::handle)
                .add();

        net.messageBuilder(LoginStatusS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(LoginStatusS2CPacket::new)
                .encoder(LoginStatusS2CPacket::toBytes)
                .consumerMainThread(LoginStatusS2CPacket::handle)
                .add();
    }
    public static <MSG> void sendToServer(MSG message)
    {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToClient(MSG message , ServerPlayer player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}