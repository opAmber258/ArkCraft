package com.ArkCraft.arkCraft.Items.ArkCraftItems;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class HeadhuntingPermit extends Item {


    public HeadhuntingPermit(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components,
                                TooltipFlag tooltipFlag) {
        components
                .add(Component
                        .literal("罗德岛人事部颁发的许可书，")
                        .withStyle(ChatFormatting.YELLOW));

        components
                .add(Component
                        .literal("可从猎头公司招聘一位干员。")
                        .withStyle(ChatFormatting.YELLOW));

        components
                .add(Component
                        .literal("      "));

        components
                .add(Component
                        .literal("相比罗德岛，猎头公司有着更广的人脉与资源。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("他们或许比罗德岛更擅长从各个领域发掘人才，")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));
        components
                .add(Component
                        .literal("而这也是人事部愿意支付高额佣金的唯一原因。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }


    private static final Random RANDOM = new Random();

    private static int getRandom() {
        int num = RANDOM.nextInt(10);
        return num;
    }

    /*public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if (block == DrSBag.get()) {
            var itemstack = context.getItemInHand();
            int p = HeadhuntingPermit.getRandom();
            itemstack.shrink(1);
            Player player = context.getPlayer();
            if (p == 0) {
                player.addItem(new ItemStack(ModItems.Saki.get()));
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                player.addItem(new ItemStack(ModItems.TwelveF.get()));
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return super.useOn(context);
    }*/
}


