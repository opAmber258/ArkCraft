package com.ArkCraft.arkCraft.Items.Weapons;

import com.ArkCraft.arkCraft.Render.CustomStyle;
import com.ArkCraft.arkCraft.Render.RayFx;
import com.ArkCraft.arkCraft.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BranchStaff extends ArkCraftStaff {
    public BranchStaff(Properties pProperties) {
        super(pProperties);
        Utils.manaDamage.put(this, 5d);
        Utils.attackRange.put(this, 10d);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            ServerLevel sl = (ServerLevel) level;

            double range = Utils.attackRange.getOrDefault(this, 10d);
            HitResult hit = playFxAndGetHit(sl, player, FxType.RAY2, range, ParticleTypes.END_ROD);
            player.getCooldowns().addCooldown(this, 20);
            if (hit.hitEntity() instanceof Mob mob) {
                float dmg = Utils.manaDamage.getOrDefault(this, 5d).floatValue();
                mob.hurt(mob.damageSources().playerAttack(player), dmg);
            } else {
                player.displayClientMessage(Component.literal("未命中"), true);
            }

            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }



    @Override
    public Component getBranch(ItemStack itemStack) {
        return Component.literal("法杖").withStyle(ChatFormatting.LIGHT_PURPLE);
    }

    @Override
    public List<Component> getArkweaponDescription() {
        List<Component> components = new ArrayList<>();
        components.add(Component.literal("路边捡到的树枝")
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        components.add(Component.literal("折成了法杖(??")
                .withStyle(CustomStyle.test));
        return components;
    }
}
