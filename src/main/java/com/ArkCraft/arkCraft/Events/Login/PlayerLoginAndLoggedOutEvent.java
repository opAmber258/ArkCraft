package com.ArkCraft.arkCraft.Events.Login;


import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerLoginAndLoggedOutEvent {

    public static double px;
    public static double py;
    public static double pz;


    @SubscribeEvent
    public static void Login(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        px = player.getX();
        py = player.getY();
        pz = player.getZ();
        Password.setLoginStatus(player, false);
        player.sendSystemMessage(Component.literal(player.getDisplayName().getString() + " 欢迎回来! ")
                .withStyle(ChatFormatting.GREEN));
        if (Password.hasPassword(player)) {
            player.sendSystemMessage(Component.literal("请使用/arkcraft login 命令登录")
                    .withStyle(ChatFormatting.GREEN));
            player.sendSystemMessage(Component.literal("若要更改密码, 请在登陆后使用/arkcraft setpassword 命令")
                    .withStyle(ChatFormatting.GREEN));
        } else {
            player.sendSystemMessage(Component.literal("请使用/arkcraft register 命令注册")
                    .withStyle(ChatFormatting.GREEN));
            player.sendSystemMessage(Component.literal("注册格式为: /arkcraft register <密码> <重复密码>")
                    .withStyle(ChatFormatting.GREEN));
        }
    }


    @SubscribeEvent
    public static void LoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        Password.setLoginStatus(player, false);
    }

    @SubscribeEvent
    public static void dropItem(ItemTossEvent event) {
        Player player = event.getPlayer();
        if (Password.getLoginStatus(player)) {

        } else {
            player.addItem(event.getEntity().getItem());
            event.setCanceled(true);
            player.sendSystemMessage(Component.literal("你还没登录呢! ")
                    .withStyle(ChatFormatting.GREEN));
        }
    }

    @SubscribeEvent
    public static void lockOtherContainer(PlayerContainerEvent.Open event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;
        if (!Password.getLoginStatus(player)) {
            player.sendSystemMessage(Component.literal("干嘛, 你还没登录呢")
                    .withStyle(ChatFormatting.GREEN));
            player.closeContainer();
        }
    }

    @SubscribeEvent
    public static void loginMove(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return; // 用 END 更不容易被覆盖
        if (event.player.level().isClientSide()) return;
        ServerPlayer player = (ServerPlayer) event.player;
        if (!Password.getLoginStatus(player)) {
            player.setDeltaMovement(0, 0, 0);
            player.fallDistance = 0;
            player.connection.teleport(px, py, pz, player.getYRot(), player.getXRot());
        }
    }


    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return; // 只处理死亡重生

        var oldData = event.getOriginal().getPersistentData();
        var newData = event.getEntity().getPersistentData();


        if (oldData.contains(Password.PASSWORD_SALT))
            newData.putString(Password.PASSWORD_SALT, oldData.getString(Password.PASSWORD_SALT));
        if (oldData.contains(Password.PASSWORD_HASH))
            newData.putString(Password.PASSWORD_HASH, oldData.getString(Password.PASSWORD_HASH));


        if (oldData.contains(Password.LOGIN_STATUS))
            newData.putBoolean(Password.LOGIN_STATUS, oldData.getBoolean(Password.LOGIN_STATUS));
    }

}

