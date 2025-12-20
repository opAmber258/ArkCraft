package com.ArkCraft.arkCraft.Events.Login;

import com.ArkCraft.arkCraft.Networking.LoginStatusS2CPacket;
import com.ArkCraft.arkCraft.Networking.ModNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;



public class Password {

    public static final String PASSWORD_SALT = "password_salt";
    public static final String PASSWORD_HASH = "password_hash";
    public static final String LOGIN_STATUS  = "login_status";

    public static final String PASSWORD  = "password";


    public static boolean hasPassword(Player player) {
        if (!(player instanceof ServerPlayer sp)) return false;
        CompoundTag nbt = sp.getPersistentData();
        return nbt.contains(PASSWORD_SALT) && nbt.contains(PASSWORD_HASH)
                && !nbt.getString(PASSWORD_HASH).isEmpty();
    }

    // 注册/设置新密码：写 salt + hash
    public static void setPassword(Player player, String newPassword) {
        if (!(player instanceof ServerPlayer sp)) return;

        CompoundTag nbt = sp.getPersistentData();
        String saltB64 = PasswordHash.newSaltB64();
        String hashB64 = PasswordHash.hashB64(newPassword.toCharArray(),
                java.util.Base64.getDecoder().decode(saltB64));

        nbt.putString(PASSWORD_SALT, saltB64);
        nbt.putString(PASSWORD_HASH, hashB64);
    }

    // 验证密码：hash(input) == storedHash
    public static boolean verifyPassword(Player player, String input) {
        if (!(player instanceof ServerPlayer sp)) return false;
        CompoundTag nbt = sp.getPersistentData();

        String saltB64 = nbt.getString(PASSWORD_SALT);
        String hashB64 = nbt.getString(PASSWORD_HASH);

        if (saltB64.isEmpty() || hashB64.isEmpty()) return false;
        return PasswordHash.verify(input, saltB64, hashB64);
    }

    public static void setLoginStatus(Player player, boolean status) {
        if (!(player instanceof ServerPlayer sp)) return;
        sp.getPersistentData().putBoolean(LOGIN_STATUS, status);
        ModNetworking.sendToClient(new LoginStatusS2CPacket(status), sp);
    }

    public static boolean getLoginStatus(Player player) {
        if (player.level().isClientSide()) return ClientLoginCache.loginStatus;
        return player.getPersistentData().getBoolean(LOGIN_STATUS);
    }


    /*
    public static void setPassword(Player player, String newPassword) {
        if (player.level().isClientSide() || !(player instanceof ServerPlayer serverPlayer)) return;
        CompoundTag nbt = serverPlayer.getPersistentData();
        nbt.putString(PASSWORD, newPassword);
    }

    public static String getPassword(Player player) {
        if (player.level().isClientSide()) return "nope";
        return player.getPersistentData().getString(PASSWORD);
    }



    public static void setLoginStatus(Player player, Boolean status) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        serverPlayer.getPersistentData().putBoolean(LOGIN_STATUS, status);
        // 发包同步给客户端
        ModNetworking.sendToClient(new LoginStatusS2CPacket(status), serverPlayer);
    }

    public static boolean getLoginStatus(Player player) {
        if (player.level().isClientSide()) {
            return ClientLoginCache.loginStatus;
        }
        return player.getPersistentData().getBoolean(LOGIN_STATUS);
    }

     */
}
