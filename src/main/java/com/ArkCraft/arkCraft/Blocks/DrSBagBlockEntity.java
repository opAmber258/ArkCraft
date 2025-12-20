package com.ArkCraft.arkCraft.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import static com.ArkCraft.arkCraft.Blocks.ModBlocks.drsbagentity;

public class DrSBagBlockEntity extends BlockEntity {

    public DrSBagBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(drsbagentity.get(), pPos, pBlockState);
    }

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(1){

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    };

    private final LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional
            .of(()-> itemStackHandler);


    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side){
        if (capability == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandlerLazyOptional.cast();
        } else {
            return super.getCapability(capability, side);
        }
    }

    public ItemStackHandler getItems() {
        return itemStackHandler;
    }

    private void saveData(CompoundTag tag) {
        tag.put("test", itemStackHandler.serializeNBT());
    }

    private void loadData(CompoundTag tag) {
        if (tag.contains("test")) {
            itemStackHandler.deserializeNBT(tag.getCompound("test"));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveData(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        loadData(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveData(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if (tag != null) {
            loadData(tag);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet) {
        CompoundTag tag = packet.getTag();
        if (tag != null) {
            handleUpdateTag(tag);
        }
    }

    public void serverTick() {

    }

    public void clientTick() {

    }


}
