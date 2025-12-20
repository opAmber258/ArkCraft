package com.ArkCraft.arkCraft.Entities.NPC;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TestNPC extends PathfinderMob {
    // 1. 静态方法：注册实体基础属性（必须！解决supplier=null）
    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                // 基础属性：生命值20（和玩家一致）、移动速度0.3（玩家行走速度）
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 8.0D); // 追踪范围
    }

    // 2. 构造方法：仅保留必要逻辑，依赖父类初始化
    public TestNPC(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.setPersistenceRequired(); // 防止NPC消失
        // 手动初始化生命值（兜底，避免属性未加载）
        this.setHealth(this.getMaxHealth());
        this.setCustomName(Component.literal("绁星灯"));
        // 关键：显示名称（头顶可见），false=隐藏，true=显示
        this.setCustomNameVisible(true);
    }

    // 3. 空AI注册：先确保召唤成功，后续再添加逻辑
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 10.0F));
    }
    // 4. 禁止推动（可选）
    @Override
    public boolean isPushable() {
        return false;
    }
}