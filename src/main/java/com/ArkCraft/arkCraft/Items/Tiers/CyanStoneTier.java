package com.ArkCraft.arkCraft.Items.Tiers;

import com.ArkCraft.arkCraft.Items.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public enum CyanStoneTier implements Tier{
    // 定义一个"琥珀"材质的工具属性
    CYANSTONE(
            2000, // 耐久度（木材59，铁250，钻石1561）
            6, // 挖掘速度（仅对工具有效，剑主要看攻击）
            2.0F, // 击退阻力
            3.0F, // 基础攻击伤害（剑的总伤害 = 这个值 + 攻击伤害修正值）
            15, // 附魔价值（越高越容易出高级附魔）
            BlockTags.create(new ResourceLocation("mymod", "needs_cyanstone_tool")), // 可挖掘方块标签（剑用不上，填默认）
            () -> Ingredient.of(ModItems.CyanStone.get()) // 修复材料
    );

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final TagKey<Block> inverseTag;
    private final Supplier<Ingredient> repairIngredient;

    CyanStoneTier(int level, int uses, float speed, float damage, int enchantmentValue, TagKey<Block> inverseTag,
                  Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.inverseTag = inverseTag;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() { return uses; } // 耐久度
    @Override
    public float getSpeed() { return speed; } // 挖掘速度
    @Override
    public float getAttackDamageBonus() { return damage; } // 基础攻击伤害
    @Override
    public @Nullable TagKey<Block> getTag() { return inverseTag; } // 可挖掘方块标签
    @Override
    public int getEnchantmentValue() { return enchantmentValue; } // 附魔价值
    @Override
    public Ingredient getRepairIngredient() { return repairIngredient.get(); } // 修复材料
    @Override
    public int getLevel() { return level; } // 工具等级（木材1，铁2，钻石3等）
}