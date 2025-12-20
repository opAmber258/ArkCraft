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

public class TenrollHeadhuntingPermit extends Item {
    public TenrollHeadhuntingPermit(Properties p_41383_) {
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
                        .literal("用于从猎头公司批量招聘干员。")
                        .withStyle(ChatFormatting.YELLOW));

        components
                .add(Component
                        .literal("一次可以招聘十位干员。")
                        .withStyle(ChatFormatting.YELLOW));

        components
                .add(Component
                        .literal("      "));

        components
                .add(Component
                        .literal("在罗德岛需求大量人才的时候，")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("一般的许可书将会降低猎头公司工作的效率。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("为了推进罗德岛的发展，")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));

        components
                .add(Component
                        .literal("人事部颁发了此类批量寻访人才的证书。")
                        .withStyle(ChatFormatting.GRAY)
                        .withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }



    private static final Random TRRANDOM = new Random();

    private static int getRandom() {
        int num = TRRANDOM.nextInt(10);
        return num;
    }

    /*public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if (block == DrSBag.get()) {
            var itemstack = context.getItemInHand();
            itemstack.shrink(1);
            for (int i = 0; i < 10; i++) {
                int p = TenrollHeadhuntingPermit.getRandom();
                Player player = context.getPlayer();
                if (p == 0) {
                    player.addItem(new ItemStack(ModItems.Saki.get()));
                } if (p != 0){
                    player.addItem(new ItemStack(ModItems.TwelveF.get()));
                }
            } return InteractionResult.sidedSuccess(level.isClientSide);
        } return super.useOn(context);
    }*/
}
