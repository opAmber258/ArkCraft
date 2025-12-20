package com.ArkCraft.arkCraft.Blocks;


import com.ArkCraft.arkCraft.Gui.MenuProvider.DrSBagContainerMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class DrSBag extends Block implements EntityBlock {

    public DrSBag(Properties p_49795_) {
        super(p_49795_);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DrSBagBlockEntity(pos, state); // 创建实体实例
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            var blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof DrSBagBlockEntity drSBagBlockEntity) {
                System.out.println("找到方块实体，尝试打开界面");
                NetworkHooks.openScreen((ServerPlayer) player,
                        new DrSBagContainerMenuProvider(blockPos), drSBagBlockEntity.getBlockPos());
            } else {
                System.out.println("未找到 DrSBagBlockEntity，blockEntity: " + blockEntity);
            }
        } return InteractionResult.SUCCESS;
    }
}
